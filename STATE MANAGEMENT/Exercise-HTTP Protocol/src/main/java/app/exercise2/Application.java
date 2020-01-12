package main.java.app.exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Application {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> request = getRequest();

        HttpRequestImpl httpRequest = new HttpRequestImpl(request.toString());
        HttpResponseImpl httpResponse = new HttpResponseImpl();

        for (Map.Entry<String, String> getHeaders : httpRequest.getHeaders().entrySet()) {
            if(getHeaders.getKey().equals("Cookie")){
                httpResponse.addCookie(getHeaders.getKey(),getHeaders.getValue());
            }
        }

        System.out.println(httpResponse);

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

