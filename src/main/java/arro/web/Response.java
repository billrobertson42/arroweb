package arro.web;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public interface Response {

    int getStatus();

    void setStatus(int status);

    Map<String, List<String>> getHeaders();

    void addHeader(String name, String value);

    void setHeader(String name, String value);

    default void setContentType(String value) {
        setHeader("Content-Type", value);
    }

    default void setContentType(String value, String encoding) {
        setContentType(value + "; " + encoding);
    }

    default Optional<? extends ResponseCookie> getCookie(String name) {
        ResponseCookie rc = null;
        int rcLength = 0;
        for(ResponseCookie candidate: getCookies()) {
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

    public List<? extends ResponseCookie> getCookies();

    public ResponseCookie setCookie(String name, String domain);

    Map<String, Object> getAttributes();

    void setBody(Object body);

    void setBody(Object body, Serializer customSerializer);

    Object getBody();

    Optional<Serializer> getCustomSerializer();

}
