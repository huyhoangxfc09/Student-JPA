package com.example.student.repository;

import java.util.List;

public interface IGeneralRepository<T> {
    List<T> findAll();

    T findById(Long id);

    void create(T t);
    void update(T t);

    void remove(Long id);
}
