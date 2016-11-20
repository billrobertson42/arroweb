package arro.adapter;

import arro.web.ResponseCookie;

import javax.servlet.http.Cookie;

public class ResponseCookieAdapter implements ResponseCookie {

    private final Cookie wrapped;
    private final boolean existing;

    private boolean httpOnly;
    private int version;
    private String value;
    private boolean secure;
    private String path;
    private int maxAge;
    private String domain;
    private String comment;

    private boolean modified;
    private boolean deleted;

    public ResponseCookieAdapter(Cookie wrapped) {
        this.wrapped = wrapped;
        assign(wrapped);
        existing = true;
    }

    public ResponseCookieAdapter(String name, String value) {
        this.wrapped = new Cookie(name, value);
        assign(wrapped);
        existing = false;
    }

    @Override
    public void setComment(String comment) {
        modified = true;
        this.comment = comment;
    }

    @Override
    public void setDomain(String domain) {
        modified = true;
        this.domain = domain;
    }

    @Override
    public void setMaxAge(int maxAge) {
        modified = true;
        this.maxAge = maxAge;
    }

    @Override
    public void setPath(String path) {
        modified = true;
        this.path = path;
    }

    @Override
    public void setSecure(boolean secure) {
        modified = true;
        this.secure = secure;
    }

    @Override
    public void setValue(String value) {
        modified = true;
        this.value = value;
    }

    @Override
    public void setVersion(int version) {
        modified = true;
        this.version = version;
    }

    @Override
    public void setHttpOnly(boolean httpOnly) {
        modified = true;
        this.httpOnly = httpOnly;
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
    public String getName() {
        return wrapped.getName();
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
    public void delete() {
        this.deleted = true;
    }

    boolean isDeleted() {
        return deleted;
    }

    boolean isExisting() {
        return existing;
    }

    boolean isModified() {
        return modified;
    }

    Cookie getWrapped() {
        return wrapped;
    }

    private void assign(Cookie wrapped) {
        this.httpOnly = wrapped.isHttpOnly();
        this.version = wrapped.getVersion();
        this.value = wrapped.getValue();
        this.secure = wrapped.getSecure();
        this.path = wrapped.getPath();
        this.maxAge = wrapped.getMaxAge();
        this.domain = wrapped.getDomain();
        this.comment = wrapped.getComment();
    }

}
