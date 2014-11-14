package com.java.courses.task9.dao.impl;


import com.java.courses.task9.connector.DBConnector;
import com.java.courses.task9.dao.DepartmentDAO;
import com.java.courses.task9.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDAOImpl implements DepartmentDAO {
    private DBConnector connector;
    private Connection connection;

    {
        connector = new DBConnector();
    }

    @Override
    public void create(Department department) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "INSERT INTO departments (id, name) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, department.getId());
        statement.setString(2, department.getName());


        // Get results
        statement.executeUpdate();

        // Clear resources
        statement.close();
        connection.close();
    }

    @Override
    public Department read(Long id) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "SELECT * FROM departments WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        // Get results
        Department department = new Department();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            department.setId(resultSet.getLong("id"));
            department.setName(resultSet.getString("name"));
        }

        // Clear resources
        resultSet.close();
        statement.close();
        connection.close();
        return department;
    }

    @Override
    public List<Department> readAll() throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "SELECT * FROM departments";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Get results
        List<Department> list = new ArrayList<Department>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            list.add(new Department(resultSet.getLong("id"), resultSet.getString("name")));
        }

        // Clear resources
        resultSet.close();
        statement.close();
        connection.close();
        return list;
    }

    @Override
    public void update(Department department) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "UPDATE departments SET name = ?,  WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, department.getName());
        statement.setLong(2, department.getId());

        // Get results
        statement.executeUpdate();

        // Clear resources
        statement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "DELETE FROM departments WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        // Get results
        statement.executeUpdate();

        // Clear resources
        statement.close();
        connection.close();
    }

    public String searchDepartmentByEmployersID(Long id) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query

        String sql = "SELECT name FROM departments WHERE id IN (SELECT department_id FROM employers WHERE id = ? )";
        //String sql = "SELECT name FROM departments WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

       // Get results
        Department department = new Department();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            department.setName(resultSet.getString("name"));
        }
        // Clear resources
        resultSet.close();
        statement.close();
        connection.close();
        return department.getName();
    }

}
