package com.denis.consoleapp;

import com.denis.consoleapp.service.CommandService;
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
        Store store = new Store();
        CommandService commandService = new CommandService(store);
        commandService.printStore();
        executeStore(scanner, commandService);
    }

    private static void executeStore(Scanner scanner, CommandService commandService) {
        System.out.println("Available list of commands: print, sort, top, quit");
        String storeCommand = scanner.nextLine();
        switch (storeCommand) {
            case print:
                System.out.println("Store returned to standard state"); // sort and top commands not modifying product list
                commandService.printStore();
                break;
            case sort:
                commandService.printSort();
                break;
            case top:
                commandService.printTopPrice();
                break;
            case quit:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command. Please enter the correct one");
                break;
        }
        executeStore(scanner, commandService);
    }
}

