package arro.web;

import java.io.InputStream;
import java.util.*;

public interface Request {

    public int getPort();

    public String getServer();

    public String getRemoteAddress();

    public String getUri();

    public String getQueryString();

    public String getScheme();

    public String getMethod();

    public String getProtocol();

    public Map<String, List<String>> getHeaders();

    default Optional<? extends RequestCookie> getCookie(String name) {
        RequestCookie rc = null;
        int rcLength = 0;
        for(RequestCookie candidate: getCookies()) {
            if(candidate != null && Objects.equals(name, candidate.getName())) {
                if(rc == null) {
                    rc = candidate;
                    rcLength = candidate.getPath() == null ? 0 : candidate.getPath().length();
                }
                else {
                    int candidateLength = candidate.getPath() == null ? 0 : candidate.getPath().length();
                    if(candidateLength > rcLength) {
                        rc = candidate;
                        rcLength = candidateLength;
                    }
                }
            }
        }
        return Optional.ofNullable(rc);
    }

    public List<? extends RequestCookie> getCookies();

    public HashMap<String, Object> getAttributes();

    public InputStream getBody();

    public Response createResponse(int status);
}
