package com.denis.consoleapp.service;

import com.denis.domain.Product;
import com.denis.store.Store;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PrintOrderCommand implements Command {
    Scanner scanner = new Scanner(System.in);
    private Store store;
    private int categoryNumber;
    private int productNumber;

    public PrintOrderCommand(Store store) {
        this.store = store;
    }

    @Override
    public void execute() {
        setOrderCategory();
        int inputNumber = scanner.nextInt();
        productNumber = inputNumber - 1;
        List<Product> productList = store.getCategoryList().get(categoryNumber).getProductList();
        if (productNumber >= 0 && productNumber < productList.size()) {
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
            int threadTime = 1 + (int) (Math.random() * 29);
            Runnable removePurchasedItems = () -> {
                store.getPurchasedItems().add(productList.get(productNumber));
                System.out.println("Product '" + productList.get(productNumber).getName() + "'" + " is added to the cart");
            };
            scheduledExecutorService.schedule(removePurchasedItems, threadTime, TimeUnit.SECONDS);
        } else {
            System.out.println("Invalid product number!");
            execute();
        }
    }

    public void setOrderCategory() {
        printOrderCategory();
        int inputNumber = scanner.nextInt();
        if (inputNumber > 0 && inputNumber <= store.getCategoryList().size()) {
            this.categoryNumber = inputNumber - 1;
            System.out.println("Selected category of products: " + store.getCategoryList().get(this.categoryNumber).getName());
            printOrderProducts();
        } else {
            System.out.println("Invalid category number!");
            setOrderCategory();
        }
    }

    public void printOrderCategory() {
        System.out.println("Select the category of products:");
        int numberOfCategories = store.getCategoryList().size();
        for (int i = 1; i <= numberOfCategories; i++) {
            System.out.println(i + ": " + store.getCategoryList().get(i - 1).getName());
        }
    }

    public void printOrderProducts() {
        int numberOfProducts = store.getCategoryList().get(categoryNumber).getProductList().size();
        for (int i = 1; i <= numberOfProducts; i++) {
            System.out.println(i + ": " + store.getCategoryList().get(categoryNumber).getProductList().get(i - 1));
        }
    }
}
