package arro.adapter;

import arro.web.ResponseCookie;

import javax.servlet.http.Cookie;

public class WrappedCookie implements ResponseCookie {

    private final Cookie wrapped;
    private final boolean existing;
    private Cookie deleted = null;

    public WrappedCookie(Cookie wrapped) {
        this.wrapped = wrapped;
        existing = true;
    }

    public WrappedCookie(String name, String value) {
        this.wrapped = new Cookie(name, value);
        existing = false;
    }

    @Override
    public void setComment(String purpose) {
        wrapped.setComment(purpose);
    }

    @Override
    public void setDomain(String domain) {
        wrapped.setDomain(domain);
    }

    @Override
    public void setMaxAge(int expiry) {
        wrapped.setMaxAge(expiry);
    }

    @Override
    public void setPath(String uri) {
        wrapped.setPath(uri);
    }

    @Override
    public void setSecure(boolean flag) {
        wrapped.setSecure(flag);
    }

    @Override
    public void setValue(String newValue) {
        wrapped.setValue(newValue);
    }

    @Override
    public void setVersion(int v) {
        wrapped.setVersion(v);
    }

    @Override
    public void setHttpOnly(boolean isHttpOnly) {
        wrapped.setHttpOnly(isHttpOnly);
    }

    @Override
    public String getComment() {
        return wrapped.getComment();
    }

    @Override
    public String getDomain() {
        return wrapped.getDomain();
    }

    @Override
    public int getMaxAge() {
        return wrapped.getMaxAge();
    }

    @Override
    public String getPath() {
        return wrapped.getPath();
    }

    @Override
    public boolean getSecure() {
        return wrapped.getSecure();
    }

    @Override
    public String getName() {
        return wrapped.getName();
    }

    @Override
    public String getValue() {
        return wrapped.getValue();
    }

    @Override
    public int getVersion() {
        return wrapped.getVersion();
    }

    @Override
    public boolean isHttpOnly() {
        return wrapped.isHttpOnly();
    }

    @Override
    public void delete() {
        deleted = new Cookie(getName(), getValue());
        deleted.setMaxAge(0);
        deleted.setHttpOnly(isHttpOnly());
        deleted.setPath(getPath());
    }

    public Cookie isDeleted() {
        return deleted;
    }

    public boolean isExisting() {
        return existing;
    }

}
