package arro.jetty;

import arro.web.Response;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;

public class Handler extends AbstractHandler {

    private final Function<arro.web.Request, Response> handler;

    public Handler(Server server, Function<arro.web.Request, Response> handler) {
        this.handler = handler;
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        Response resp = handler.apply(Adapter.getRequest(baseRequest));
//
//        if (resp == null) {
//            response.setStatus(404);
//        } else {
////            response.setStatus(resp.getStatus());
//            throw new UnsupportedOperationException("to do!");
//        }

    }

}
