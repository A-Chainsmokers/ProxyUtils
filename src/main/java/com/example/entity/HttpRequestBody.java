package com.example.entity;



/**
 * @author Range
 * @version 1.0
 * @date 2021/3/17 16:40
 */

public class HttpRequestBody {

    public static final String GET = "GET";

    public static final String POST = "POST";

    public static final String FORM = "application/x-www-form-urlencoded; charset=utf-8";

    public static final String JSON = "application/json; charset=utf-8";


    private String auth = null;

    private boolean credentials = true;

    private String data = null;

    private HttpHeader headers = new HttpHeader();

    private String method = GET;

    private String url;

    private boolean wantsBinary = true;


    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public boolean isCredentials() {
        return credentials;
    }

    public void setCredentials(boolean credentials) {
        this.credentials = credentials;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public HttpHeader getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeader headers) {
        this.headers = headers;
    }

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

    public boolean isWantsBinary() {
        return wantsBinary;
    }

    public void setWantsBinary(boolean wantsBinary) {
        this.wantsBinary = wantsBinary;
    }


    @Override
    public String toString() {
        return "{" +
                "    auth:'" + auth + '\'' +
                ",     credentials:" + credentials +
                ",     data:'" + data + '\'' +
                ",     headers:" + headers +
                ",     method:'" + method + '\'' +
                ",     url:'" + url + '\'' +
                ",     wantsBinary:" + wantsBinary +
                '}';
    }
}
