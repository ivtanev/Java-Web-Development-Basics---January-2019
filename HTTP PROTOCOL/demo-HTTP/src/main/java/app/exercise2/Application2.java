package main.java.app.exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Application2 {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        List<String> validUrls = getValidUrl();
        List<String> request = getRequest();

        Request httpRequest = new Request(request.toString());
        Response httpResponse = new Response();

        if (!validUrls.contains(httpRequest.getRequestUrl())) {
            httpResponse.setStatusCode(404);
            httpResponse.setContent("The requested functionality was not found.");
        } else if (!httpRequest.getHeaders().entrySet().contains("Authorization")) {
            httpResponse.setStatusCode(401);
            httpResponse.setContent("You are not authorized to access the requested functionality.");
        } else if (httpRequest.getMethod().equals("POST") && httpRequest.getBodyParameters().size() == 0) {
            httpResponse.setStatusCode(400);
            httpResponse.setContent("There was an error with the requested functionality due to malformed request.");
        }

        for (Map.Entry<String, String> kvp : httpRequest.getHeaders().entrySet()) {
            httpResponse.addHeader(kvp.getKey(),kvp.getValue());
        }


        System.out.println(httpResponse);
        System.out.println(httpRequest.getHeaders());


    }

    public static List<String> getValidUrl() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    public static List<String> getRequest() throws IOException {
        List<String> request = new ArrayList<>();

        String line = null;
        while ((line = reader.readLine()) != null && line.length() > 0) {
            request.add(line);
        }
        request.add(System.lineSeparator());
        if ((line = reader.readLine()) != null && line.length() > 0) {
            request.add(line);
        }
        return request;
    }
}
