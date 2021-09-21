package com.denis.store.utility.http.handler;

import com.denis.domain.Product;
import com.denis.store.Store;
import com.denis.store.utility.dao.ProductDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CartHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream requestBody = exchange.getRequestBody();
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(requestBody, Product.class);
        Store store = Store.getInstance();
        store.getPurchasedItems().add(product);
        String response = "Product is added to the cart";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
