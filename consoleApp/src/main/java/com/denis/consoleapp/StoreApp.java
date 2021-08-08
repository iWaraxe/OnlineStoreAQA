package com.denis.consoleapp;

import com.denis.consoleapp.service.*;
import com.denis.store.Store;

import java.util.Scanner;

public class StoreApp {
    public static final String print = "print";
    public static final String sort = "sort";
    public static final String top = "top";
    public static final String quit = "quit";

    public static void main(String[] args) {
        initStore();
    }

    private static void initStore() {
        Scanner scanner = new Scanner(System.in);
        Store store = Store.getInstance();
        CommandService commandService = new CommandService(store);
        commandService.printStore();

        Switch aSwitch = new Switch(new PrintTopPriceCommand(commandService),
                new ExitCommand(commandService),
                new PrintSortCommand(commandService),
                new PrintStoreCommand(commandService));

        executeStore(scanner, aSwitch);
    }

    private static void executeStore(Scanner scanner, Switch s) {
        System.out.println("Available list of commands: print, sort, top, quit");

        String storeCommand = scanner.nextLine();

        switch (storeCommand) {
            case print:
                // sort and top commands not modifying product list
                System.out.println("Store returned to standard state");
                s.printStore();
                break;
            case sort:
                s.printSort();
                break;
            case top:
                s.printTopPrice();
                break;
            case quit:
                s.exit();
                break;
            default:
                System.out.println("Invalid command. Please enter the correct one");
                break;
        }
        executeStore(scanner, s);
    }
}

