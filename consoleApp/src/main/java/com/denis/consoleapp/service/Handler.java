package com.denis.consoleapp.service;

import com.denis.store.Store;
import com.denis.store.utility.PrintHelper;

public abstract class Handler extends PrintHelper {

    public abstract boolean isAccessibleHandler(String handler);

    public abstract void execute(Store store);
}
