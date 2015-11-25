package ua.artcode.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Maxim on 18.11.2015.
 */
public class CodingBatTaskChecker {

    /* This method takes id and code in format like example
       id = "p187868"
       code = "public boolean sleepIn(boolean weekday, boolean vacation) {
    return true;
        }"

      return map with key = expected, value = current   */
    public void checkTask(String id, String code) {
        String url = "http://codingbat.com/run";
        HttpClient client = HttpClientBuilder.create().build(); // create client
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("id", id)); // set params
        urlParameters.add(new BasicNameValuePair("code", code));// set params

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters, "UTF-8"));
            // Execute HTTP Post Request
            HttpResponse response = client.execute(post);// get page

            System.out.println("STATUS " + response.getStatusLine());// TODO make log message about got the page

            HttpEntity entity = response.getEntity(); // incoming data
            if (entity != null) {
                InputStream inStream = entity.getContent();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                    String data;
                    while ((data = reader.readLine()) != null) {
                        System.out.println(data + "\n");
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                } finally {
                    inStream.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
