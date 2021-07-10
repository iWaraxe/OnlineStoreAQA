package com.denis.consoleapp;

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
        store.print();
        executeStore(scanner, store);
    }

    private static void executeStore(Scanner scanner, Store store) {
        System.out.println("Available list of commands: print, sort, top, quit");
        String storeCommand = scanner.nextLine();
        switch (storeCommand) {
            case print:
                System.out.println("Store returned to standard state"); // sort and top commands not modifying product list
                store.print();
                executeStore(scanner, store);
                break;
            case sort:
                store.printSort();
                executeStore(scanner, store);
                break;
            case top:
                store.printTopPrice();
                executeStore(scanner, store);
                break;
            case quit:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command. Please enter the correct one");
                break;
        }
    }
}

