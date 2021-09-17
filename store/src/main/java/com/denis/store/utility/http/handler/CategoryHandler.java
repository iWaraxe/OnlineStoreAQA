package com.denis.store.utility.http.handler;

import com.denis.domain.Category;
import com.denis.store.utility.dao.CategoryDao;
import com.denis.store.utility.populator.DBPopulator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<Category> categories;
        try {
            categories = new DBPopulator().getAllCategories();
        } catch (Exception e) {
            e.printStackTrace();
            categories = new ArrayList<>();
        }

        ObjectMapper mapper = new ObjectMapper();
        OutputStream out = null;

        try {
            byte[] jsonInBytes = mapper.writeValueAsBytes(categories);
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonInBytes.length);
            out = exchange.getResponseBody();
            out.write(jsonInBytes);
        } finally {
            if(Objects.nonNull(out)) {
                out.close();
            }
        }
    }
}
