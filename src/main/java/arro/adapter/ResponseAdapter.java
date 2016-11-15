package arro.adapter;

import arro.web.Response;
import arro.web.ResponseCookie;
import arro.web.Serializer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class ResponseAdapter implements Response {

    private final HashMap<String, List<String>> headers = new HashMap<>();
    private final HashMap<String, Object> attributes;
    private final HttpServletRequest request;

    private List<WrappedCookie> cookies;
    private Object body;
    private Serializer customSerializer;
    private int status;

    public ResponseAdapter(HttpServletRequest request, HashMap<String, Object> attributes, int status) {
        this.request = request;
        this.attributes = attributes;
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public List<? extends ResponseCookie> getCookies() {
        if(cookies == null) {
            cookies = getCookies(request);
        }
        return cookies;
    }

    @Override
    public ResponseCookie setCookie(String name, String value, String path, int maxAge) {
        WrappedCookie wc = new WrappedCookie(name, value);
        wc.setPath(path);
        wc.setMaxAge(maxAge);
        getCookies();
        cookies.add(wc);
        return wc;
    }

    @Override
    public void addHeader(String name, String value) {
        if(name != null && value != null) {
            List<String> values = headers.get(name);
            if (values == null) {
                values = new ArrayList<>();
                headers.put(name, values);
            }
            values.add(value);
        }
    }

    @Override
    public void setHeader(String name, String value) {
        if(value == null) {
            headers.remove(name);
        }
        else {
            List<String> values = new ArrayList<>();
            values.add(value);
            headers.put(name, values);
        }
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public void setBody(Object body, Serializer customSerializer) {
        this.body = body;
        this.customSerializer = customSerializer;
    }

    @Override
    public Object getBody() {
        return body;
    }

    @Override
    public Optional<Serializer> getCustomSerializer() {
        return Optional.ofNullable(customSerializer);
    }

    private List<WrappedCookie> getCookies(HttpServletRequest baseRequest) {
        Cookie[] cookies = baseRequest.getCookies();
        List<WrappedCookie> ck = new ArrayList<>();
        if (cookies != null) {
            for (int c = 0; c < cookies.length; ++c) {
                ck.add(new WrappedCookie(cookies[c]));
            }
        }
        return ck;
    }
}
