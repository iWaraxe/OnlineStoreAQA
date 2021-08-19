package com.denis.consoleapp.service;

import com.denis.store.Store;

import java.util.Arrays;
import java.util.List;

public class CommandManager {
    Store store;
    List<Handler> commands;

    public CommandManager(Store store) {
        this.store = store;
        commands = Arrays.asList(
                new ExitCommand(store),
                new PrintTopPriceCommand(store),
                new PrintStoreCommand(store),
                new PrintSortCommand(store),
                new PrintOrderCommand(store));
    }

    public void execute(String str) {
        Handler commandHandler = commands.stream().filter(c -> c.handler(str)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid command. Please enter the correct one!"));
        commandHandler.execute(str);
    }
}
