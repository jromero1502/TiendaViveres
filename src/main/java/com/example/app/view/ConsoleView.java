package com.example.app.view;

import com.example.app.models.ProductModel;
import com.example.app.services.RepositoryService;

import java.util.Scanner;

public class ConsoleView {

    private Scanner input;
    private int applicationStatus = 1;

    private final RepositoryService<ProductModel> service;

    public ConsoleView(RepositoryService<ProductModel> service) {
        this.service = service;
    }

    public void start() {
        configInit();
        showTitle();
        while(applicationStatus != 0) {
            showOptions();
            handleUserOption();
        }
    }

    private void configInit() {
        input = new Scanner(System.in);
    }

    private void showTitle() {
        System.out.println("**************************************");
        System.out.println("        INVENTARIO DE VIVERES        ");
        System.out.println("**************************************");
    }

    private void showOptions() {
        System.out.println("Seleccione una de las siguientes opciones: ");
        System.out.println("1. Administrar inventario");
        System.out.println("2. Analizar datos");
        System.out.println("0. Salir");
        applicationStatus = input.nextInt();
    }

    private void handleUserOption() {
        switch (applicationStatus) {
            case 0:
                exitApplication();
                break;
            case 1:
                showAdminOptions();
                break;
            case 2:
                showAnalyzeOptions();
                break;
        }
    }

    private void showAdminOptions() {
        System.out.println("\n¿Qué operación desea realizar?: ");
        System.out.println("1. ACTUALIZAR");
        System.out.println("2. BORRAR");
        System.out.println("3. AGREGAR");
        int response = input.nextInt();
        switch (response) {
            case 1:
                updateProduct();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                addProduct();
                break;

        }
    }

    private void addProduct() {
        ProductModel newProduct = new ProductModel();
        System.out.println("\nCódigo:  ");
        newProduct.setCode(input.nextInt());
        System.out.println("Nombre:");
        newProduct.setName(input.next());
        System.out.println("Precio:");
        newProduct.setPrice(input.next());
        System.out.println("Inventario: ");
        newProduct.setInventario(input.next());
        service.save(newProduct);
    }

    private void updateProduct() {
        ProductModel newProduct = new ProductModel();
        System.out.println("\nCódigo:  ");
        newProduct.setCode(input.nextInt());
        System.out.println("Nombre:");
        newProduct.setName(input.nextLine());
        System.out.println("Precio:");
        newProduct.setPrice(input.nextLine());
        System.out.println("Inventario: ");
        newProduct.setInventario(input.nextLine());
        service.update(newProduct);
    }

    private void deleteProduct() {
        ProductModel newProduct = new ProductModel();
        System.out.println("\nCódigo:  ");
        newProduct.setCode(input.nextInt());
        service.delete(newProduct);
    }

    private void showAnalyzeOptions() {
        System.out.println("\n¿Qué operación desea realizar?: ");
        System.out.println("1. Producto con mayor precio");
        System.out.println("2. Producto con menor precio");
        System.out.println("3. Promedio de precios");
        System.out.println("4. Valor del inventario");
    }

    private void exitApplication() {
        System.out.println("Cerrando inventario...");
    }
}

