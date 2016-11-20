package arro.adapter;

import arro.web.RequestCookie;

import javax.servlet.http.Cookie;

/**
 * Read only record of the cookie that will not change if original cookie data is mutated
 */
public class RequestCookieAdapter implements RequestCookie {

    private final Cookie cookie;

    RequestCookieAdapter(Cookie cookie) {
        this.cookie = cookie;
    }

    @Override
    public String getComment() {
        return cookie.getComment();
    }

    @Override
    public String getDomain() {
        return cookie.getDomain();
    }

    @Override
    public int getMaxAge() {
        return cookie.getMaxAge();
    }

    @Override
    public String getPath() {
        return cookie.getPath();
    }

    @Override
    public boolean getSecure() {
        return cookie.getSecure();
    }

    @Override
    public String getValue() {
        return cookie.getValue();
    }

    @Override
    public int getVersion() {
        return cookie.getVersion();
    }

    @Override
    public boolean isHttpOnly() {
        return cookie.isHttpOnly();
    }

    @Override
    public String getName() {
        return cookie.getName();
    }
}
