package ua.artcode.utils;

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
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.MethodSignature;
import ua.artcode.model.codingbat.TaskTestData;
import ua.artcode.model.codingbat.TaskTestResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 04.12.2015.
 */
public class CodingBatTaskUtils {

    private static final Logger LOG = Logger.getLogger(CodingBatTaskUtils.class);

    private HttpResponse getResponseFromCodingBat(CodingBatTask task, String codeForRequest) {
        HttpResponse response = null;

        String url = "http://codingbat.com/run";
        String codingBatId = task.getCodingBatId();

        HttpClient client = HttpClientBuilder.create().build(); // create client
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
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

    private String getCodeForRequestToCodingBat(CodingBatTask task){
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

    public void initTaskTestDataContainer(CodingBatTask task) {
        String codeForRequest = getCodeForRequestToCodingBat(task);
        HttpResponse response = getResponseFromCodingBat(task, codeForRequest);
        HttpEntity entity = response.getEntity(); // incoming data

        if (entity != null) {
            InputStream inStream = null;
            try {
                inStream = entity.getContent();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
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
                } catch (RuntimeException e) {
                    LOG.error(e);
                } finally {
                    inStream.close();
                }
            } catch (IOException e) {
                LOG.error(e);
            }

        }
    }

    private String getExpectedValueFromHtml(String dataHtml) {
        return StringUtils.substringBetween(dataHtml, "&rarr;", "<");
    }

    private List<String> getInDataFromHtml(String dataHtml) {
        String params = StringUtils.substringBetween(dataHtml, "(", ")");
        return CodingBatHtmlDataParser.parseTestData(params);
    }

    public TaskTestResult checkCodingBatTask(CodingBatTask task, String code) {
        TaskTestResult result = new TaskTestResult();
        result.setCodingBatTask(task);

        HttpResponse response = getResponseFromCodingBat(task, code);
        HttpEntity entity = response.getEntity(); // incoming data

        if (entity != null) {
            InputStream inStream = null;
            try {
                inStream = entity.getContent();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                    String dataHtml;

                    while ((dataHtml = reader.readLine()) != null) {
                        String[] actualValue = StringUtils.substringsBetween(dataHtml, "<td>", "</td>");
                        if (actualValue != null) {
                            result.getActualValues().add(actualValue[1]);
                            result.getStatus().add(actualValue[2]);
                        }
                    }
                } catch (RuntimeException e) {
                    LOG.error(e);
                } finally {
                    inStream.close();
                }
            } catch (IOException e) {
                LOG.error(e);
            }

        }
        return result;
    }

    public MethodSignature getMethodSignature(String template) {
        MethodSignature methodSignature = new MethodSignature();
        if (template.contains("public")) {
            methodSignature.setReturnType(template.split(" ")[1]);
        } else {
            methodSignature.setReturnType(template.split(" ")[0]);
        }

        String inParams = StringUtils.substringBetween(template, "(", ")");
        if (!inParams.equals("")) {
            for (String param : inParams.split(",")) {
                String[] typeName = param.trim().split(" ");
                methodSignature.addInArg(typeName[0], typeName[1]);
            }
        }
        return methodSignature;
    }
}
