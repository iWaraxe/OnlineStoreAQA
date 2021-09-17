package com.denis.store.utility.http;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static com.denis.store.utility.http.AppHttpServer.PASSWORD;
import static com.denis.store.utility.http.AppHttpServer.USERNAME;
import static java.lang.String.format;

public class HttpClient {

    public static final String BASIC_AUTH_CREDENTIALS_FORMAT = "%s:%s";


    public List<Category> getAllCategories() {
        HttpURLConnection connection = new HttpClient().getConnection("/categories", HttpMethod.GET);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(connection.getInputStream(), new TypeReference<List<Category>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            connection.disconnect();
        }
    }

    public void addProductToCart(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        HttpURLConnection connection = new HttpClient().getConnection("/cart", HttpMethod.POST);
        connection.setDoOutput(true);
        try {
            OutputStream os = connection.getOutputStream();
            byte[] data = mapper.writeValueAsBytes(product);
            os.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            connection.disconnect();
        }
    }

    private HttpURLConnection getConnection(String endpoint, HttpMethod method) {
        try {
            URL address = new URL("http", "localhost", 8080, endpoint);
            String credentials = Base64.getEncoder()
                    .encodeToString((format(BASIC_AUTH_CREDENTIALS_FORMAT, USERNAME, PASSWORD))
                            .getBytes(StandardCharsets.UTF_8));
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod(method.name());
            connection.setRequestProperty("Authorization", "Basic " + credentials);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
