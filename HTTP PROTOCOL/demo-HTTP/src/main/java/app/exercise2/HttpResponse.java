package main.java.app.exercise2;

import java.util.HashMap;
import java.util.Map;

public interface HttpResponse {
    Map<String, String> getHeaders();

    int getStatusCode();

    byte[] getContent();

    byte[] getBytes();

    void setStatusCode(int statusCode);

    void setContent(String content);

    void addHeader(String header, String value);
}
