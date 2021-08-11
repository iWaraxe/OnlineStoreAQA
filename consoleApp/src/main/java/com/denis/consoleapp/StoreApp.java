package com.denis.consoleapp;

import com.denis.consoleapp.service.*;
import com.denis.domain.Product;
import com.denis.store.Store;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StoreApp {
    public static final String print = "print";
    public static final String sort = "sort";
    public static final String top = "top";
    public static final String order = "order";
    public static final String quit = "quit";

    public static void main(String[] args) {
        initStore();
    }

    private static void initStore() {
        Scanner scanner = new Scanner(System.in);
        Store store = Store.getInstance();
        PrintStoreCommand printStoreCommand = new PrintStoreCommand(store);
        printStoreCommand.execute();

        clearCartByTimer(store);

        Switch aSwitch = new Switch(new PrintTopPriceCommand(store),
                new ExitCommand(store),
                new PrintSortCommand(store),
                new PrintStoreCommand(store),
                new PrintOrderCommand(store));

        executeStore(scanner, aSwitch);
    }

    private static void clearCartByTimer(Store store) { // checking that products are added to the cart and then cleared
        Runnable cartCleaner = () -> {
            System.out.println("****** Before clearing the cart ******");
            for (Product p : store.getPurchasedItems()) {
                System.out.println(p.toString());
            }
            store.getPurchasedItems().clear();
            System.out.println("****** After clearing the cart ******");
            for (Product p : store.getPurchasedItems()) {
                System.out.println(p.toString());
            }
            System.out.println("****** Cart has been cleared ******");
        };
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(cartCleaner, 60, 120, TimeUnit.SECONDS);
    }

    private static void executeStore(Scanner scanner, Switch s) {
        System.out.println("Available list of commands: print, sort, top, order, quit");

        String storeCommand = scanner.nextLine();

        switch (storeCommand) {
            case print:
                System.out.println("Store returned to standard state");
                s.printStore();
                break;
            case sort:
                s.printSort();
                break;
            case top:
                s.printTopPrice();
                break;
            case order:
                s.printOrder();
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