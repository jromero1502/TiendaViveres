package com.example.app;

import com.example.app.models.ProductModel;
import com.example.app.repository.ProductRepository;
import com.example.app.services.ProductRepositoryService;
import com.example.app.view.ConsoleView;

import java.util.HashMap;

public class Startup {


    public static void main(String[] args) {
        HashMap<Integer, ProductModel> dataSource = new HashMap<Integer, ProductModel>();
        ProductRepository repository = new ProductRepository(dataSource);
        ProductRepositoryService service = new ProductRepositoryService(repository);
        new ConsoleView(service).start();
    }

}
