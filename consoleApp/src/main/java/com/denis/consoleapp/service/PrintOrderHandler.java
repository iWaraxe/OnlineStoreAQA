package com.denis.consoleapp.service;

import com.denis.domain.Category;
import com.denis.domain.Product;
import com.denis.store.Store;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PrintOrderHandler extends Handler {

    @Override
    public boolean isAccessibleHandler(String handler) {
        return "order".equalsIgnoreCase(handler);
    }

    @Override
    public void execute(Store store) {
        Scanner scanner = new Scanner(System.in);
        List<Category> categoryList = store.getCategoryList();
        printOrderCategory(categoryList);
        int categoryNumber = getOrderCategory(categoryList, scanner);
        List<Product> productList = categoryList.get(categoryNumber).getProductList();

        System.out.println("Selected category of products: " + categoryList.get(categoryNumber).getName());
        printOrderProducts(categoryNumber, categoryList, productList);

        int productNumber = getProduct(scanner);


        if (productNumber >= 0 && productNumber < productList.size()) {
            scheduledExecutor(store, productList.get(productNumber));
        } else {
            System.out.println("Invalid product number!");
            execute(store);
        }
    }

    public int getOrderCategory(List<Category> categoryList, Scanner scanner) {
        int inputNumber = scanner.nextInt();
        int categoryNumber = inputNumber - 1;
        if (!(inputNumber > 0 && inputNumber <= categoryList.size())) {
            System.out.println("Invalid category number!");
            getOrderCategory(categoryList, scanner);
        }
        return categoryNumber;
    }

    public int getProduct(Scanner scanner) {
        int inputNumber = scanner.nextInt();
        int productNumber = inputNumber - 1;
        return productNumber;
    }

    public void printOrderCategory(List<Category> categoryList) {
        System.out.println("Select the category of products:");
        int numberOfCategories = categoryList.size();
        for (int i = 1; i <= numberOfCategories; i++) {
            System.out.println(i + ": " + categoryList.get(i - 1).getName());
        }
    }

    public void printOrderProducts(int categoryNumber, List<Category> categoryList, List<Product> productList) {
        int numberOfProducts = categoryList.get(categoryNumber).getProductList().size();
        for (int i = 1; i <= numberOfProducts; i++) {
            System.out.println(i + ": " + printProductLine(productList.get(i - 1)));
        }
    }

    public void scheduledExecutor(Store store, Product orderedProduct) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        int threadTime = 1 + (int) (Math.random() * 29);
        Runnable removePurchasedItems = () -> {
            store.getPopulator().addToCart(orderedProduct);
            System.out.println("Product '" + orderedProduct.getName() + "'" + " is added to the cart");
        };
        scheduledExecutorService.schedule(removePurchasedItems, threadTime, TimeUnit.SECONDS);
    }
}