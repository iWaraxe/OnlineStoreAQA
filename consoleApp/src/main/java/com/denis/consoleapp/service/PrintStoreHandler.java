package com.denis.consoleapp.service;

import com.denis.store.Store;

public class PrintStoreHandler extends Handler {

    @Override
    public boolean isAccessibleHandler(String handler) {
        return "print".equalsIgnoreCase(handler);
    }

    @Override
    public void execute(Store store) {
        System.out.println(
                printStore(store)
        );
    }
}