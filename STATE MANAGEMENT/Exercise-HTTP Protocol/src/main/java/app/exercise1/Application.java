package main.java.app.exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        List<String> validUrls = getValidUrl();
        List<String> request = getRequest();

        String method = getMethod(request.get(0));

        Map<String, String> headers = getHeaders(request);
        Map<String, String> bodyParameters = getBodyParameters(request);

        StringBuilder response = new StringBuilder();
        if(headers.size()>0){
            for (Map.Entry<String, String> kvp : headers.entrySet()) {
                if (kvp.getKey().equals("Cookie")) {
                    String[] cookieParameters = kvp.getValue().split("; ");
                    for (int i = 0; i < cookieParameters.length; i++) {
                        response.append(String.format("%s <-> %s",cookieParameters[i].split("=")[0],cookieParameters[i].split("=")[1]))
                                .append(System.lineSeparator());
                    }
                }

            }
        }else {
            response.append("Header is empty");
        }

        System.out.println(response.toString());
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

    private static String getMethod(String line) {
        if(line.equals("\r\n")){
            return line;
        }
        return line.split("\\s+")[0];
    }


    private static Map<String, String> getHeaders(List<String> request) {
        Map<String, String> headers = new LinkedHashMap<>();

        request.stream()
                .filter(h -> h.contains(": "))
                .map(h -> h.split(": "))
                .forEach(headerKvp -> {
                    headers.put(headerKvp[0], headerKvp[1]);
                });
        return headers;
    }

    private static Map<String, String> getBodyParameters(List<String> request) {
        Map<String, String> bodyParameters = new LinkedHashMap<>();

        if (request.get(request.size() - 1).equals("\r\n")) {
            return bodyParameters;
        }

        Arrays.stream(request.get(request.size() - 1)
                .split("&"))
                .map(bp -> bp.split("="))
                .forEach(bpKvp -> {
                    bodyParameters.put(bpKvp[0], bpKvp[1]);
                });


        return bodyParameters;
    }
}
