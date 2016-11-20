package arro.adapter;

import arro.web.Request;
import arro.web.RequestCookie;
import arro.web.Response;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.*;

public class RequestAdapter implements Request {

    private final HashMap<String, Object> attributes = new HashMap<>();
    private final HttpServletRequest request;
    private List<? extends RequestCookie> cookies;
    private Map<String, List<String>> headers = null;

    public RequestAdapter(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public int getPort() {
        return request.getServerPort();
    }

    @Override
    public String getServer() {
        return request.getServerName();
    }

    @Override
    public String getRemoteAddress() {
        return request.getRemoteAddr();
    }

    @Override
    public String getUri() {
        return request.getRequestURI();
    }

    @Override
    public String getQueryString() {
        return request.getQueryString();
    }

    @Override
    public String getScheme() {
        return request.getScheme();
    }

    @Override
    public String getMethod() {
        return request.getMethod();
    }

    @Override
    public String getProtocol() {
        return request.getProtocol();
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        if (headers == null) {
            headers = extractHeaders(request);
        }
        return headers;
    }

    @Override
    public List<? extends RequestCookie> getCookies() {
        if (cookies == null) {
            cookies = getCookie(request);
        }
        return cookies;
    }

    @Override
    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public InputStream getBody() {
        try {
            return request.getInputStream();
        } catch (IOException ioEx) {
            throw new UncheckedIOException(ioEx);
        }
    }

    @Override
    public Response createResponse(int status) {
        return new ResponseAdapter(request, attributes, status);
    }

    private List<RequestCookieAdapter> getCookie(HttpServletRequest baseRequest) {
        Cookie[] cookies = baseRequest.getCookies();
        List<RequestCookieAdapter> list = new ArrayList<>();
        if(cookies != null) {
            for(int c = 0; c<cookies.length; ++c) {
                list.add(new RequestCookieAdapter(cookies[c]));
            }
        }
        return Collections.unmodifiableList(list);
    }

    private Map<String, List<String>> extractHeaders(HttpServletRequest request) {
        Map<String, List<String>> h = new HashMap<>();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            Enumeration<String> values = request.getHeaders(headerName);
            if (values.hasMoreElements()) {
                String first = values.nextElement();
                if (values.hasMoreElements()) {
                    ArrayList<String> multiple = new ArrayList<>();
                    multiple.add(first);
                    do {
                        multiple.add(values.nextElement());
                    } while (values.hasMoreElements());
                    h.put(headerName, multiple);
                } else {
                    h.put(headerName, new SingleList(first));
                }
            } else {
                h.put(headerName, Collections.EMPTY_LIST);
            }
        }
        return Collections.unmodifiableMap(h);
    }

}
