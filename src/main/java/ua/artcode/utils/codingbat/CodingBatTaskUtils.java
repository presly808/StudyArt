package ua.artcode.utils.codingbat;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import ua.artcode.exception.AppValidationException;
import ua.artcode.model.codingbat.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Maxim on 04.12.2015.
 */
public class CodingBatTaskUtils {

    private static final Logger LOG = Logger.getLogger(CodingBatTaskUtils.class);

    private static HttpResponse getResponseFromCodingBat(String codeForRequest,String Id) {
        HttpResponse response = null;

        String url = "http://codingbat.com/run";
        String codingBatId = Id;

        HttpClient client = HttpClientBuilder.create().build(); // create client
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("id", codingBatId)); // set params
        urlParameters.add(new BasicNameValuePair("code", codeForRequest));// set params

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
            // Execute HTTP Post Request
            response = client.execute(post);// get page

            LOG.info("STATUS got the response from CodingBat - " + response.getStatusLine());

        } catch (UnsupportedEncodingException e) {
            LOG.error(e);
        } catch (ClientProtocolException e) {
            LOG.error(e);
        } catch (IOException e) {
            LOG.error(e);
        }

        return response;
    }

    private static String getCodeForRequestToCodingBat(CodingBatTask task) {
        StringBuilder code = new StringBuilder(task.getTemplate().split("}")[0]);

        if (task.getMethodSignature().getReturnType().equals("boolean")) {
            code.append("return false; }");
        } else if (task.getMethodSignature().getReturnType().equals("int[]")) {
            code.append("return null; }");
        } else if (task.getMethodSignature().getReturnType().equals("int")) {
            code.append("return 0; }");
        } else if (task.getMethodSignature().getReturnType().equals("String[]")) {
            code.append("return null; }");
        } else if (task.getMethodSignature().getReturnType().equals("String")) {
            code.append("return null; }");
        } else if (task.getMethodSignature().getReturnType().equals("List")) {
            code.append("return null; }");
        }
        return code.toString();
    }

    public static void initTaskTestDataContainer(CodingBatTask task,String codingBatId) {
        String codeForRequest = getCodeForRequestToCodingBat(task);
        HttpResponse response = getResponseFromCodingBat(codeForRequest,codingBatId);
        HttpEntity entity = response.getEntity(); // incoming data

        if (entity != null) {

            try (InputStream inStream = entity.getContent();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inStream))) {

                String dataHtml;

                while ((dataHtml = reader.readLine()) != null) {
                    TaskTestData taskTestData = new TaskTestData();
                    taskTestData.setExpectedValue(getExpectedValueFromHtml(dataHtml));
                    taskTestData.setInData(getInDataFromHtml(dataHtml));

                    if (taskTestData.getExpectedValue() != null && taskTestData.getInData() != null) {
                        task.getTaskTestDataContainer().addTaskTestData(taskTestData);
                        LOG.trace("added new taskTestData to taskTestDataContainer");
                    }
                }

            } catch (IOException e) {
                LOG.error(e);
            }

        }
    }

    private static String getExpectedValueFromHtml(String dataHtml) {
        return StringUtils.substringBetween(dataHtml, "&rarr; ", "<");
    }

    private static List<String> getInDataFromHtml(String dataHtml) {
        String params = StringUtils.substringBetween(dataHtml, "(", ")");
        return params != null ? CodingBatHtmlDataParser.parseTestData(params) : new LinkedList<>();
    }

    public static TaskTestDataContainer getTestDataContainer(String testData) throws AppValidationException {
        TaskTestDataContainer testDataContainer = new TaskTestDataContainer();

        List<String> dataPoints = Arrays.asList(testData.split("\r\n"));
        try {
            for (String dataPoint : dataPoints) {
                String[] dataParts = dataPoint.split("-");
                String expectedValue = dataParts[0];
                List<String> inParams = Arrays.asList(dataParts[1].split(","));

                TaskTestData taskTestData = new TaskTestData(expectedValue, inParams);
                testDataContainer.addTaskTestData(taskTestData);
            }
        } catch (Exception e) {
            throw new AppValidationException();
        }

        return testDataContainer;
    }

    public TaskTestResult checkCodingBatTask(final String id, final String code) {
        TaskTestResult result = new TaskTestResult();
        //result.setCodingBatTask(task);

        HttpResponse response = getResponseFromCodingBat(code,id);
        HttpEntity entity = response.getEntity(); // incoming data

        if (entity != null) {

            try (InputStream inStream = entity.getContent();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inStream))) {

                String dataHtml;

                while ((dataHtml = reader.readLine()) != null) {
                    String[] actualValue = StringUtils.substringsBetween(dataHtml, "<td>", "</td>");
                    if (actualValue != null) {
                        result.getActualValues().add(actualValue[1]);
                        result.getResults().add(actualValue[2]);
                    }
                }

            } catch (IOException e) {
                LOG.error(e);
            }

        }
        return result;
    }

    public static MethodSignature getMethodSignature(String template){ //throws AppValidationException {

        //new CodingBatTaskValidator().validateTemplate(template);
        MethodSignature methodSignature = new MethodSignature();

        String[] parts = template.split(" ");
        methodSignature.setReturnType(template.contains("public") ? parts[1] : parts[0]);

        String inParams = StringUtils.substringBetween(template, "(", ")");
        if (!inParams.equals("")) {
            for (String param : inParams.split(",")) {
                String[] typeName = param.trim().split(" ");
                methodSignature.addInArg(typeName[0], typeName[1]);
            }
        }
        return methodSignature;
    }

    public static String getMethodName(String template) {
        String[] parts = template.split("\\(");
        String[] words = parts[0].split(" ");
        return words[words.length - 1].trim();
    }

    public static String checkResult(String actualValue, Object expectedValue) {
        if (expectedValue.equals(actualValue)) {
            return "OK";
        } else {
            return "X";
        }
    }

    public static String statusGenerator(List<String> results) {
        String result = "All tests failed";
        int counter = 0;
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).equals("OK")) {
                counter++;
            }
        }
        if (counter == results.size()) {
            result = "All correct";
        } else if (counter > 0) {
            result = counter + " passed from " + results.size() + " tests";
        }
        return result;
    }
}

