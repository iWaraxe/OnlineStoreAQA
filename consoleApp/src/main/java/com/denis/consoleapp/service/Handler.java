package com.denis.consoleapp.service;

import com.denis.store.Store;

public abstract class Handler {
    protected Store store;

    public Handler(Store store) {
        this.store = store;
    }

    public abstract boolean handler(String command);

    public abstract void execute(String command);
}
