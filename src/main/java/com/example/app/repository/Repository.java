package com.example.app.repository;

public interface Repository<T> {

    public void save(T t);
    public void update(T t);
    public void delete(T t);
}
