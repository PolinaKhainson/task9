package com.java.courses.task9.dao;


import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<T> {

    // Create
    void create(T t) throws SQLException;

    // Read
    T read(Long id) throws SQLException;
    List<T> readAll() throws SQLException;

    // Update
    void update(T t) throws SQLException;

    // Delete
    void delete(Long id) throws SQLException;
}
