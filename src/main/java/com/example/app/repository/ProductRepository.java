package com.example.app.repository;

import com.example.app.exceptions.DuplicatedProductException;
import com.example.app.models.ProductModel;

import java.util.HashMap;

public class ProductRepository implements Repository<ProductModel> {

    private final HashMap<Integer, ProductModel> dataSource;

    public ProductRepository(HashMap dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(ProductModel model) {
        if (dataSource.putIfAbsent(model.getCode(), model) != null) {
            throw new DuplicatedProductException("Ya existe un producto con ese codigo");
        }
    }

    @Override
    public void update(ProductModel model) {
        dataSource.replace(model.getCode(), model);
    }

    @Override
    public void delete(ProductModel model) {
        dataSource.remove(model.getCode());
    }

}
