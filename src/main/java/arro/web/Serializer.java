package arro.web;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A serializer is responsible for sending the response body to the client.
 */
@FunctionalInterface
public interface Serializer {
    void serialize(OutputStream output, Response response) throws IOException;
}
