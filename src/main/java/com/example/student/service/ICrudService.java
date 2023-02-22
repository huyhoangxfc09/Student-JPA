package com.example.student.service;

import java.util.List;

public interface ICrudService<T> {
    List<T> findAll();

    T findById(Long id);
    void create(T t);
    void update(T t);

    void remove(Long id);
}
