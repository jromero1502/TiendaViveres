package com.example.app.repository;

import com.example.app.exceptions.DuplicatedProductException;
import com.example.app.exceptions.NonExistentProductException;
import com.example.app.models.ProductModel;

import java.util.HashMap;

public class ProductRepository implements Repository<ProductModel, Integer> {

    private final HashMap<Integer, ProductModel> dataSource;

    public ProductRepository(HashMap dataSource) {

        this.dataSource = dataSource;
        this.addInitialData();

    }

    private void addInitialData() {
        this.dataSource.put(1, new ProductModel(1, "Manzanas", 5000.0, 25.0));
        this.dataSource.put(2, new ProductModel(2, "Limones", 2300.0, 15.0));
        this.dataSource.put(3, new ProductModel(3, "Peras", 2700.0, 33.0));
        this.dataSource.put(4, new ProductModel(4, "Arandanos", 9300.0, 5.0));
        this.dataSource.put(5, new ProductModel(5, "Tomates", 2100.0, 42.0));
        this.dataSource.put(6, new ProductModel(6, "Fresas", 4100.0, 3.0));
        this.dataSource.put(7, new ProductModel(7, "Helado", 4500.0, 41.0));
        this.dataSource.put(8, new ProductModel(8, "Galletas", 500.0, 8.0));
        this.dataSource.put(9, new ProductModel(9, "Chocolates", 3500.0, 80.0));
        this.dataSource.put(10, new ProductModel(10, "Jamon", 15000.0, 10.0));
    }

    @Override
    public void save(ProductModel model) {
        if (dataSource.putIfAbsent(model.getCode(), model) != null) {
            throw new DuplicatedProductException("Ya existe un producto con ese codigo");
        }
    }

    @Override
    public void update(ProductModel model) {
        if (dataSource.replace(model.getCode(), model) == null) {
            throw new NonExistentProductException("No existe el producto");
        }
    }

    @Override
    public void delete(ProductModel model) {
        ProductModel existing = dataSource.get(model.getCode());

        if (existing == null
        || !model.getName().equals(existing.getName())
        || !model.getPrice().equals(existing.getPrice())
        || !model.getInventario().equals(existing.getInventario())) {
            throw new NonExistentProductException("Los atributos del producto no coinciden con ninguno existente");
        }

        if (dataSource.remove(model.getCode()) == null) {
            throw new NonExistentProductException("No existe el producto");
        }
    }

    @Override
    public HashMap<Integer, ProductModel> getAll() {
        return this.dataSource;
    }

}
