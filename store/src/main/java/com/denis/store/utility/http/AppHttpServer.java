package com.denis.store.utility.http;

import com.denis.store.utility.http.handler.CartHandler;
import com.denis.store.utility.http.handler.CategoryHandler;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class AppHttpServer {

    public static final String USERNAME = "admin";
    public static final String PASSWORD = "password";

    public void startServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            createContext(server,"/categories", new CategoryHandler());
            createContext(server,"/cart", new CartHandler());
            server.setExecutor(null);
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void createContext(HttpServer server, String path, HttpHandler handler) {
        server.createContext(path, handler).setAuthenticator(new BasicAuthenticator("test") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals(USERNAME) && pwd.equals(PASSWORD);
            }
        });
    }
}
