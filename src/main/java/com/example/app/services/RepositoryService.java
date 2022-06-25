package com.example.app.services;

public interface RepositoryService<T> {

    public void save(T t);
    public void update(T t);
    public void delete(T t);
}
