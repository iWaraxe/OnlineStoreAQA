package com.denis.consoleapp.service;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PrintOrderCommand extends Handler {
    Scanner scanner = new Scanner(System.in);
    private int categoryNumber;
    private int productNumber;
    private List<Category> categoryList = store.getCategoryList();
    private List<Product> productList;

    public PrintOrderCommand(Store store) {
        super(store);
    }

    @Override
    public boolean handler(String command) {
        return "order".equalsIgnoreCase(command);
    }

    @Override
    public void execute(String command) {
        setOrderCategory();
        int inputNumber = scanner.nextInt();
        productNumber = inputNumber - 1;
        productList = categoryList.get(categoryNumber).getProductList();
        if (productNumber >= 0 && productNumber < productList.size()) {
            scheduledExecutor();
        } else {
            System.out.println("Invalid product number!");
            execute(command);
        }
    }

    public void setOrderCategory() {
        printOrderCategory();
        int inputNumber = scanner.nextInt();
        if (inputNumber > 0 && inputNumber <= categoryList.size()) {
            this.categoryNumber = inputNumber - 1;
            System.out.println("Selected category of products: " + categoryList.get(this.categoryNumber).getName());
            printOrderProducts();
        } else {
            System.out.println("Invalid category number!");
            setOrderCategory();
        }
    }

    public void printOrderCategory() {
        System.out.println("Select the category of products:");
        int numberOfCategories = categoryList.size();
        for (int i = 1; i <= numberOfCategories; i++) {
            System.out.println(i + ": " + categoryList.get(i - 1).getName());
        }
    }

    public void printOrderProducts() {
        int numberOfProducts = categoryList.get(categoryNumber).getProductList().size();
        for (int i = 1; i <= numberOfProducts; i++) {
            System.out.println(i + ": " + categoryList.get(categoryNumber).getProductList().get(i - 1));
        }
    }

    public void scheduledExecutor() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        int threadTime = 1 + (int) (Math.random() * 29);
        Runnable removePurchasedItems = () -> {
            store.getPurchasedItems().add(productList.get(productNumber));
            System.out.println("Product '" + productList.get(productNumber).getName() + "'" + " is added to the cart");
        };
        scheduledExecutorService.schedule(removePurchasedItems, threadTime, TimeUnit.SECONDS);
    }
}