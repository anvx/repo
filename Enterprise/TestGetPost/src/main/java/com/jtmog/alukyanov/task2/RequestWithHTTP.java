package com.jtmog.alukyanov.task2;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class RequestWithHTTP {

    public static void doPost(String id) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://jsonplaceholder.typicode.com/posts");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("userId", "1"));
        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("title", "An Old Man Lived in the Village"));
        params.add(new BasicNameValuePair("body", "Nothing special. Eighty years I have been\n"
                + "chasing happiness, and it was useless. And then I decided\n"
                + " to live without happiness and just enjoy life.\n"
                + " That is why I am happy now."));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            printRequest(entity);
        }
        httpclient.close();
    }

    public static void doGet(String id) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpUriRequest httpGet = new HttpGet("http://jsonplaceholder.typicode.com/posts/" + id);
        try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            printRequest(entity);
        }
        httpclient.close();
    }

    private static void printRequest(HttpEntity entity) throws IOException {
        String[] split = EntityUtils.toString(entity).split("\\\\n");
        for (String str : split) {
            System.out.println(str);
        }
    }
}
