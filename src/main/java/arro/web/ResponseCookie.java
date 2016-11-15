package arro.web;

public interface ResponseCookie extends RequestCookie {

    void setComment(String purpose);

    void setDomain(String domain);

    void setMaxAge(int expiry);

    void setPath(String uri);

    void setSecure(boolean flag);

    void setValue(String newValue);

    void setVersion(int v);

    void setHttpOnly(boolean isHttpOnly);

    /**
     * Mark Cookie as deleted
     */
    void delete();
}
