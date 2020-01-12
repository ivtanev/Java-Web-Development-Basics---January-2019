package main.java.app.exercise2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {
    private Map<String, String> headers;
    private Map<String, String> cookies;
    private int statusCode;
    private String content;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();
        this.cookies = new LinkedHashMap<>();
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

    @Override
    public Map<String, String> getCookies() {
        return new HashMap<>(this.cookies);
    }

    @Override
    public void addCookie(String key, String value) {
        this.cookies.put(key,value);
    }

    public String toString(){
        StringBuilder httpResponse = new StringBuilder();

        if(cookies.size()>0) {
            for (Map.Entry<String, String> cookie : cookies.entrySet()) {
                String[] cookieParameters = cookie.getValue().split("; ");
                for (int i = 0; i < cookieParameters.length; i++) {
                    httpResponse.append(String.format("%s <-> %s",cookieParameters[i].split("=")[0],cookieParameters[i].split("=")[1]))
                            .append(System.lineSeparator());
                }
            }
        }else {
            httpResponse.append("Header is empty");
        }

        return httpResponse.toString();
    }
}
