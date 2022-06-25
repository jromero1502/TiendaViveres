package com.example.app.view;

import com.example.app.models.ProductModel;
import com.example.app.services.AnalyzeService;
import com.example.app.services.RepositoryService;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    private Scanner input;
    private int applicationStatus = 1;

    private final RepositoryService<ProductModel> service;
    private final AnalyzeService<ProductModel> analyzeService;

    public ConsoleView(RepositoryService<ProductModel> service, AnalyzeService<ProductModel> analyzeService) {
        this.service = service;
        this.analyzeService = analyzeService;
    }

    public void start() {
        configInit();
        showTitle();
        while(applicationStatus != 0) {
            try {
                showOptions();
                handleUserOption();
            } catch(NumberFormatException ex ) {
                System.out.println("ERROR: Entrada invalida");
            }
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
        String response = input.next();
        applicationStatus = Integer.valueOf(response);
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
            default:
                System.out.println("Seleccione una opcion válida");
                break;
        }
    }

    private void showAdminOptions() {
        System.out.println("\n¿Qué operación desea realizar?: ");
        System.out.println("1. ACTUALIZAR");
        System.out.println("2. BORRAR");
        System.out.println("3. AGREGAR");
        String response = input.next();
        switch (response) {
            case "1":
                updateProduct();
                break;
            case "2":
                deleteProduct();
                break;
            case "3":
                addProduct();
                break;
            default:
                System.out.println("Seleccione una opcion válida");
                break;
        }
    }

    private void addProduct() {
        ProductModel newProduct = new ProductModel();
        System.out.println("\nCódigo:  ");
        String code = input.next();
        newProduct.setCode(Integer.valueOf(code));
        System.out.println("Nombre:");
        newProduct.setName(input.next());
        System.out.println("Precio:");
        String price = input.next();
        newProduct.setPrice(Double.valueOf(price));
        System.out.println("Inventario: ");
        String inventory = input.next();
        newProduct.setInventario(Double.valueOf(inventory));
        service.save(newProduct);
    }

    private void updateProduct() {
        ProductModel newProduct = new ProductModel();
        System.out.println("\nCódigo:  ");
        String code = input.next();
        newProduct.setCode(Integer.valueOf(code));
        System.out.println("Nombre:");
        newProduct.setName(input.next());
        System.out.println("Precio:");
        String price = input.next();
        newProduct.setPrice(Double.valueOf(price));
        System.out.println("Inventario: ");
        String inventory = input.next();
        newProduct.setInventario(Double.valueOf(inventory));
        service.update(newProduct);
    }

    private void deleteProduct() {
        ProductModel newProduct = new ProductModel();
        System.out.println("\nCódigo:  ");
        String code = input.next();
        newProduct.setCode(Integer.valueOf(code));
        System.out.println("Nombre:");
        newProduct.setName(input.next());
        System.out.println("Precio:");
        String price = input.next();
        newProduct.setPrice(Double.valueOf(price));
        System.out.println("Inventario: ");
        String inventory = input.next();
        newProduct.setInventario(Double.valueOf(inventory));
        service.delete(newProduct);
    }

    private void showAnalyzeOptions() {
        System.out.println("\n¿Qué operación desea realizar?: ");
        System.out.println("1. Producto con mayor precio");
        System.out.println("2. Producto con menor precio");
        System.out.println("3. Promedio de precios");
        System.out.println("4. Valor del inventario");
        String response = input.next();
        switch (response) {
            case "1":
                ProductModel higherProduct = analyzeService.calculateHigher();
                System.out.println("Producto precio mayor: " + higherProduct.getName());
                break;
            case "2":
                ProductModel lowestProduct = analyzeService.calculateLowest();
                System.out.println("Producto precio menor: " + lowestProduct.getName());
                break;
            case "3":
                System.out.println("Promedio de precios: " + new DecimalFormat("#.0").format(analyzeService.averagePrice()));
                break;
            case "4":
                System.out.println("Valor del inventario: " + new DecimalFormat("#.0").format(analyzeService.calculateTotal()));
                break;
            default:
                System.out.println("Seleccione una opcion válida");
                break;
        }
    }

    private void exitApplication() {
        System.out.println("Cerrando inventario...");
    }
}

