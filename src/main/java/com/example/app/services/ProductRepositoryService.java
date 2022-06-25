package com.example.app.services;

import com.example.app.exceptions.DuplicatedProductException;
import com.example.app.exceptions.NonExistentProductException;
import com.example.app.models.ProductModel;
import com.example.app.repository.Repository;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ProductRepositoryService implements RepositoryService<ProductModel>, AnalyzeService<ProductModel> {

    private final Repository<ProductModel, Integer> repository;

    public ProductRepositoryService(Repository<ProductModel, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public void save(ProductModel model) {
        try {
            repository.save(model);
            System.out.println("Producto agregado");
            System.out.println(model.toString());
        } catch (DuplicatedProductException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public void update(ProductModel model) {

        try {
            repository.update(model);
            System.out.println("Producto Actualizado");
            System.out.println(model.toString());
        } catch (NonExistentProductException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public void delete(ProductModel model) {

        try {
            repository.delete(model);
            System.out.println("Producto Eliminado");
            System.out.println(model.toString());
        } catch (NonExistentProductException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    @Override
    public ProductModel calculateHigher() {
        HashMap<Integer, ProductModel> data = repository.getAll();
        AtomicReference<Double> higherPrice = new AtomicReference<>(0.0);
        AtomicReference<ProductModel> higherProduct = new AtomicReference<>();
        data.forEach((key, value) -> {
            if (value.getPrice() > higherPrice.get()) {
                higherProduct.set(value);
                higherPrice.set(value.getPrice());
            }
        });
        return higherProduct.get();
    }

    @Override
    public ProductModel calculateLowest() {
        HashMap<Integer, ProductModel> data = repository.getAll();
        AtomicReference<Double> lowestPrice = new AtomicReference<>(0.0);
        AtomicReference<ProductModel> lowestProduct = new AtomicReference<>();;
        AtomicInteger i = new AtomicInteger();
        data.forEach((key, value) -> {
            if (i.get() == 0) {
                lowestPrice.set(value.getPrice());
                lowestProduct.set(value);
            } else {
                if (value.getPrice() < lowestPrice.get()) {
                    lowestPrice.set(value.getPrice());
                    lowestProduct.set(value);
                }
            }
            i.getAndIncrement();
        });
        return lowestProduct.get();
    }

    @Override
    public Double averagePrice() {
        HashMap<Integer, ProductModel> data = repository.getAll();
        AtomicReference<Double> averagePrice = new AtomicReference<>(new Double(0.0));
        data.forEach((key, value) -> {
            averagePrice.updateAndGet(v -> v + value.getPrice());
        });
        return averagePrice.get() / data.size();
    }

    @Override
    public Double calculateTotal() {
        HashMap<Integer, ProductModel> data = repository.getAll();
        AtomicReference<Double> totalPrice = new AtomicReference<>(new Double(0.0));
        data.forEach((key, value) -> {
            Double total = value.getPrice() * value.getInventario();
            totalPrice.updateAndGet(v -> v + total);
        });
        return totalPrice.get();
    }
}
