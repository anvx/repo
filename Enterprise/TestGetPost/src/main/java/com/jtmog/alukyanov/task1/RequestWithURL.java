package com.jtmog.alukyanov.task1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestWithURL {
    private static final int CONNECTION_TIMEOUT = 5000;

    public static void doPost(String id) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setReadTimeout(CONNECTION_TIMEOUT);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);

        Map<String, String> parameters = new LinkedHashMap<>();
        parameters.put("userId", "1");
        parameters.put("id", id);
        parameters.put("title", "An Old Man Lived in the Village");
        parameters.put("body", "Nothing special. Eighty years I have been\n"
                + "chasing happiness, and it was useless. And then I decided\n"
                + " to live without happiness and just enjoy life.\n"
                + " That is why I am happy now.");

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : parameters.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
        }
        connection.setDoOutput(true);

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(postData.toString());
        out.flush();
        out.close();

        readRequest(connection);

        connection.disconnect();
    }

    public static void doGet(String id) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setReadTimeout(CONNECTION_TIMEOUT);
        connection.setConnectTimeout(CONNECTION_TIMEOUT);

        readRequest(connection);

        connection.disconnect();
    }

    private static String readRequest(HttpURLConnection connection) throws IOException {
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
                String[] split = inputLine.split("\\\\n");
                for (String str : split) {
                    System.out.println(str);
                }
            }
            return content.toString();
        }
    }
}
