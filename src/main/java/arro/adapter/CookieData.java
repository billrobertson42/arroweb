package arro.adapter;

import arro.web.RequestCookie;

import javax.servlet.http.Cookie;

/**
 * Read only record of the cookie that will not change if original cookie data is mutated
 */
public class CookieData implements RequestCookie {

    private final String name;
    private final boolean httpOnly;
    private final int version;
    private final String value;
    private final boolean secure;
    private final String path;
    private final int maxAge;
    private final String domain;
    private final String comment;

    CookieData(Cookie cookie) {
        name = cookie.getName();
        httpOnly = cookie.isHttpOnly();
        version = cookie.getVersion();
        value = cookie.getValue();
        secure = cookie.getSecure();
        path = cookie.getPath();
        maxAge = cookie.getMaxAge();
        domain = cookie.getDomain();
        comment = cookie.getComment();
    }


    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean getSecure() {
        return secure;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public boolean isHttpOnly() {
        return httpOnly;
    }

    @Override
    public String getName() {
        return name;
    }
}
