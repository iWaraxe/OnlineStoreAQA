package com.denis.consoleapp;

import com.denis.consoleapp.service.*;
import com.denis.domain.Product;
import com.denis.store.Store;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.denis.store.utility.PrintHelper.*;

public class StoreApp {

    public static void main(String[] args) {
        initStore();
    }

    private static void initStore() {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        HandlerManager manager = new HandlerManager();
        manager.execute("print", store);

        clearCartByTimer(store);
        executeStore(scanner, manager, store);
    }

    private static void clearCartByTimer(Store store) { // checking that products are added to the cart and then cleared
        Runnable cartCleaner = () -> {
            System.out.println("****** Before clearing the cart ******");
            for (Product p : store.getPurchasedItems()) {
                System.out.println(printProductLine(p));
            }
            store.getPurchasedItems().clear();
            System.out.println("****** After clearing the cart ******");
            for (Product p : store.getPurchasedItems()) {
                System.out.println(printProductLine(p));
            }
            System.out.println("****** Cart has been cleared ******");
        };
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(cartCleaner, 60, 120, TimeUnit.SECONDS);
    }

    private static void executeStore(Scanner scanner, HandlerManager manager, Store store) {
        System.out.println("Available list of commands: print, sort, top, order, quit");

        String storeCommand = scanner.nextLine();
        manager.execute(storeCommand, store);
        executeStore(scanner, manager, store);
    }
}