package arro.web;

public interface RequestCookie {

    String getComment();

    String getDomain();

    int getMaxAge();

    String getPath();

    boolean getSecure();

    String getValue();

    int getVersion();

    boolean isHttpOnly();

    String getName();
}
