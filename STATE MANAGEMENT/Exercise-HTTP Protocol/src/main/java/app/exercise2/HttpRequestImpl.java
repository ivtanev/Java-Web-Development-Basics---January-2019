package main.java.app.exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {
    private String method;
    private String requestUrl;
    private Map<String, String> headers = new LinkedHashMap<>();
    private Map<String, String> bodyParameters = new LinkedHashMap<>();


    public HttpRequestImpl(String input){
        input = input.substring(1,input.length()-1);
        String method = input.split("\\s+")[0];
        String requestUrl = input.split("\\s+")[1];
        this.setMethod(method);
        this.setRequestUrl(requestUrl);
        String[] header = input.split(", ");
        String[] headerValue = null;
        for (int i = 1; i < header.length; i++) {
            if (header[i].contains("\r\n"))
                break;
            else {
                headerValue = header[i].split(": ");
                addHeader(headerValue[0], headerValue[1]);
            }
        }

        String[] bodyParameters = header[header.length - 1].split("&");
        if (!bodyParameters[0].equals("\r\n")) {
            for (int i = 0; i < bodyParameters.length; i++) {
                String[] bodyParam = bodyParameters[i].split("=");
                addBodyParameters(bodyParam[0], bodyParam[1]);
            }
        }
    }


    @Override
    public Map<String, String> getHeaders() {
        return new LinkedHashMap<>(this.headers);
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return new LinkedHashMap<>(this.bodyParameters);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameters(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource(String requestUrl) {
        if (this.requestUrl.contains(requestUrl))
            return true;
        else return false;
    }
}
