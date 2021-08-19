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
        Store store = new Store();
        CommandManager manager = new CommandManager(store);
        manager.execute("print");

        clearCartByTimer(store);

        executeStore(scanner, manager);
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

    private static void executeStore(Scanner scanner, CommandManager manager) {
        System.out.println("Available list of commands: print, sort, top, order, quit");

        String storeCommand = scanner.nextLine();

        switch (storeCommand) {
            case print:
                manager.execute("print");
                break;
            case sort:
                manager.execute("sort");
                break;
            case top:
                manager.execute("top");
                break;
            case order:
                manager.execute("order");
                break;
            case quit:
                manager.execute("quit");
                break;
            default:
                System.out.println("Invalid command. Please enter the correct one!");
                manager.execute("");
                break;
        }
        executeStore(scanner, manager);
    }
}