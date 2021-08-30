package com.denis.consoleapp.service;

import com.denis.store.Store;

import java.util.Arrays;
import java.util.List;

public class HandlerManager {
    List<Handler> handlers;

    public HandlerManager() {
        handlers = Arrays.asList(
                new ExitHandler(),
                new PrintTopPriceHandler(),
                new PrintStoreHandler(),
                new PrintSortHandler(),
                new PrintOrderHandler());
    }

    public void execute(String str, Store store) {
        Handler commandHandler = handlers.stream().filter(c -> c.isAccessibleHandler(str)).findFirst()
                .orElse(new PrintStoreHandler());
        commandHandler.execute(store);
    }
}