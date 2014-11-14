package com.java.courses.task9.dao.impl;


import com.java.courses.task9.connector.DBConnector;
import com.java.courses.task9.dao.EmployerDAO;
import com.java.courses.task9.entity.Department;
import com.java.courses.task9.entity.Employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployerDAOImpl implements EmployerDAO {
    private DBConnector connector;
    private Connection connection;

    {
        connector = new DBConnector();
    }

    @Override
    public void create(Employer employer) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "INSERT INTO employers (id, name, age , e_mail, department_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, employer.getId());
        statement.setString(2, employer.getName());
        statement.setInt(3, employer.getAge());
        statement.setString(4, employer.getEmail());
        statement.setLong(5, employer.getDepartmentId());

        // Get results
        statement.executeUpdate();

        // Clear resources
        statement.close();
        connection.close();
    }

    @Override
    public Employer read(Long id) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "SELECT * FROM employers WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        // Get results
        Employer employer = new Employer();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            employer.setId(resultSet.getLong("id"));
            employer.setName(resultSet.getString("name"));
            employer.setAge(resultSet.getInt("age"));
            employer.setEmail(resultSet.getString("e_mail"));
            employer.setDepartmentId(resultSet.getLong("department_id"));


        }

        // Clear resources
        resultSet.close();
        statement.close();
        connection.close();
        return employer;
    }

    @Override
    public List<Employer> readAll() throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "SELECT * FROM employers";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Get results
        List<Employer> list = new ArrayList<Employer>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            list.add(new Employer(resultSet.getLong("id"), resultSet.getString("name"),
                    resultSet.getInt("age"),resultSet.getString("e_mail"), resultSet.getLong("department_id") ));
        }

        // Clear resources
        resultSet.close();
        statement.close();
        connection.close();
        return list;
    }

    @Override
    public void update(Employer employer) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query
        String sql = "UPDATE employers SET name = ?, age = ?, e_mail = ?, department_id = ?  WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employer.getName());
        statement.setInt(2, employer.getAge());
        statement.setString(3, employer.getEmail());
        statement.setLong(4, employer.getDepartmentId());
        statement.setLong(5, employer.getId());




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
        String sql = "DELETE FROM employers WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        // Get results
        statement.executeUpdate();

        // Clear resources
        statement.close();
        connection.close();
    }

    public String searchEmployerByDepartmentID(Long id) throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query

        String sql = "SELECT name FROM employers WHERE department_id = ? ";
        //String sql = "SELECT name FROM departments WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        // Get results
        Employer employer = new Employer();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            employer.setName(resultSet.getString("name"));
        }
        // Clear resources
        resultSet.close();
        statement.close();
        connection.close();
        return employer.getName();
    }

    public List  selectAllInformationAboutEmployers() throws SQLException {
        // Obtain connection
        connection = connector.getConnection();

        // Create SQL query

        String sql = "SELECT employers.id, employers.name,employers.age, employers.e_mail" +
                ", employers.department_id, departments.id AS d_id, " +
                "departments.name AS d_name FROM employers LEFT OUTER JOIN" +
                " departments ON employers.department_id = departments.id" ;
        PreparedStatement statement = connection.prepareStatement(sql);

        // Get results
        List list = new ArrayList();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            list.add (new Employer(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("age"),resultSet.getString("e_mail"), resultSet.getLong("department_id")));
            list.add(new Department(resultSet.getLong("d_id"), resultSet.getString("d_name")));
        }
        // Clear resources
        resultSet.close();
        statement.close();
        connection.close();
        return list;
    }
}
