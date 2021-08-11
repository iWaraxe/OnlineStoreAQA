package com.denis.consoleapp.service;

import com.denis.store.Store;

public class PrintStoreCommand implements Command {
    private Store store;

    public PrintStoreCommand(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        System.out.println(store);
    }
}