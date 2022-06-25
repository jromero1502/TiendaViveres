package com.example.app.services;

import com.example.app.exceptions.DuplicatedProductException;
import com.example.app.models.ProductModel;
import com.example.app.repository.ProductRepository;
import com.example.app.repository.Repository;

public class ProductRepositoryService implements RepositoryService<ProductModel> {

    private final Repository<ProductModel> repository;

    public ProductRepositoryService(Repository<ProductModel> repository) {
        this.repository = repository;
    }

    @Override
    public void save(ProductModel model) {
        try {
            repository.save(model);
        } catch (DuplicatedProductException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public void update(ProductModel model) {
        repository.update(model);
    }

    @Override
    public void delete(ProductModel model) {
        repository.delete(model);
    }
}
