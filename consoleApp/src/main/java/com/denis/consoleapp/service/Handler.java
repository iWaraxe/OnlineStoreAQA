package com.denis.consoleapp.service;

import com.denis.store.Store;
import com.denis.store.utility.BaseString;

public abstract class Handler extends BaseString {

    public abstract boolean isAccessibleHandler(String handler);

    public abstract void execute(Store store);
}
