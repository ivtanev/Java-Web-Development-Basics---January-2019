package main.java.app.exercise2;

import java.io.IOException;
import java.util.Map;

public interface HttpRequest {
    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl() throws IOException;

    void setRequestUrl(String requestUrl);

    void addHeader(String header, String value);

    void addBodyParameters(String parameter, String value);

    public boolean isResource(String requestUrl);
}
