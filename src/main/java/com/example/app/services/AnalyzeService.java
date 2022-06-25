package com.example.app.services;

public interface AnalyzeService<T> {

    public T calculateHigher();
    public T calculateLowest();
    public Double averagePrice();
    public Double calculateTotal();
}
