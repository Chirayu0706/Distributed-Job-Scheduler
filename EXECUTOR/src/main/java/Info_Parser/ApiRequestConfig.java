package Info_Parser;

import java.util.Map;

public class ApiRequestConfig {
    private String method;
    private String url;
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private Object body; // or String if you want raw JSON

    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    public Map<String, String> getQueryParams() {
        return queryParams;
    }
    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }
    public Object getBody() {
        return body;
    }
    public void setBody(Object body) {
        this.body = body;
    }


}

