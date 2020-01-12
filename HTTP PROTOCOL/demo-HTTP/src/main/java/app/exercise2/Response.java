package main.java.app.exercise2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Response implements HttpResponse {

    private Map<String, String> headers;
    private int statusCode;
    private String content;

    public Response() {
        this.headers = new LinkedHashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        return new HashMap<>(this.headers);
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return new byte[0];
    }

    @Override
    public byte[] getBytes() {
        return new byte[0];
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header,value);
    }

    public String toString(){
        StringBuilder httpResponse = new StringBuilder();
        String message = "OK";

        switch (statusCode){
            case 404:
                message = "Not Found";
                break;
            case 401:
                message = "Unauthorized";
                break;
            case 400:
                message = "Bad request";
                break;
        }
        httpResponse.append("HTTP/1.1 " + statusCode + " " + message + System.lineSeparator());
        for (String key : headers.keySet()) {
            httpResponse.append(key + ": " + headers.get(key) + System.lineSeparator());
        }
        httpResponse.append(System.lineSeparator());
        httpResponse.append(content).append(System.lineSeparator());
        return httpResponse.toString();
    }
}
