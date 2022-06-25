package com.example.app.repository;

import java.util.HashMap;

public interface Repository<T, K> {

    public void save(T t);
    public void update(T t);
    public void delete(T t);

    public HashMap<K, T> getAll();
}
