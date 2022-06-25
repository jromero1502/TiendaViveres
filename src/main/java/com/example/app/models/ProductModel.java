package com.example.app.models;

import java.text.DecimalFormat;

public class ProductModel {

    private Integer code;
    private String name;
    private Double price;
    private Double inventario;

    public ProductModel() {}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInventario() {
        return inventario;
    }

    public void setInventario(Double inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "Codigo: " + code +
                ", Nombre:'" + name + '\'' +
                ", Precio: '" + new DecimalFormat("#.#").format(price)  + '\'' +
                ", Inventario: '" + new DecimalFormat("#.#").format(inventario)  + '\'' +
                '}';
    }

    public ProductModel(Integer code, String name, Double price, Double inventario) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.inventario = inventario;
    }
}
