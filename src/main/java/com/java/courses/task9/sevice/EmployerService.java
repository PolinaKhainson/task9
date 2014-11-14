package com.java.courses.task9.sevice;

import com.java.courses.task9.entity.Employer;
import com.java.courses.task9.dao.EmployerDAO;
import com.java.courses.task9.dao.impl.EmployerDAOImpl;

import com.java.courses.task9.entity.Department;
import com.java.courses.task9.dao.DepartmentDAO;
import com.java.courses.task9.dao.impl.DepartmentDAOImpl;

import java.sql.SQLException;
import java.util.List;


public class EmployerService {
    public static void main(String[] args) throws SQLException {
        //Populate DB Table Employers with instances of Employer
        Employer employer1 = new Employer();
        employer1.setId(1l);
        employer1.setName("Jim");
        employer1.setAge(34);
        employer1.setEmail("jim@mail.ru");
        employer1.setDepartmentId(11l);

        Employer employer2 = new Employer();
        employer2.setId(2l);
        employer2.setName("Sam");
        employer2.setAge(48);
        employer2.setEmail("sam@mail.ru");
        employer2.setDepartmentId(12l);

        Employer employer3 = new Employer();
        employer3.setId(3l);
        employer3.setName("Bill");
        employer3.setAge(25);
        employer3.setEmail("bil@mail.ru");
        employer3.setDepartmentId(13l);

        EmployerDAO employerDAO = new EmployerDAOImpl();

        // CRUD operations - Create
        employerDAO.create(employer1);
        employerDAO.create(employer2);
        employerDAO.create(employer3);

        // CRUD operations - Read
        System.out.println("Create in data base:    " + employerDAO.read(1l));
        System.out.println("Create in data base:    " + employerDAO.read(2l));
        System.out.println("Create in data base:    " + employerDAO.read(3l));
        System.out.println("-----");

        // CRUD operations - ReadAll
        List<Employer> list = employerDAO.readAll();
        for (Employer eachEmployer : list) {
            System.out.println("Read from data base:    " + eachEmployer);
        }
        System.out.println("-----");

        // CRUD operations - Update
        employer1.setName("Bob");
        employerDAO.update(employer1);
        employer2.setName("Fob");
        employerDAO.update(employer2);
        employer3.setName("Grob");
        employerDAO.update(employer3);

        for (Employer eachEmployer : list) {
            System.out.println("Update and then read:   " + employerDAO.read(eachEmployer.getId()));
        }
        System.out.println("-----");

        //Populate DB Table Employers with instances of Employer
        Department department1 = new Department();
        department1.setId(11l);
        department1.setName("QA");

        Department department2 = new Department();
        department2.setId(12l);
        department2.setName("Java");

        Department department3 = new Department();
        department3.setId(13l);
        department3.setName("HTML/CSS");

        DepartmentDAO departmentDAO = new DepartmentDAOImpl();
        // CRUD operations - Create
        departmentDAO.create(department1);
        departmentDAO.create(department2);
        departmentDAO.create(department3);
        // CRUD operations - Read
        System.out.println(departmentDAO.read(11l));
        System.out.println(departmentDAO.read(12l));
        System.out.println(departmentDAO.read(13l));
        System.out.println("-----");
        // CRUD operations - ReadALL
        List<Department> listDepartments = departmentDAO.readAll();
        for (Department eachDepartment : listDepartments) {
            System.out.println(eachDepartment);
        }
        System.out.println("-----");
        // Search Department by Employer ID
        DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl();
        System.out.println("Employer with id = 1 works at the department " + departmentDAOImpl.searchDepartmentByEmployersID(1l));
        System.out.println("Employer with id = 2 works at the department " + departmentDAOImpl.searchDepartmentByEmployersID(2l));
        System.out.println("Employer with id = 1 works at the department " + departmentDAOImpl.searchDepartmentByEmployersID(3l));
        System.out.println("-----");
        // Search Employers by Department ID
        EmployerDAOImpl employerDAOImpl = new EmployerDAOImpl();
        System.out.println("Employer " + employerDAOImpl.searchEmployerByDepartmentID(11l) + " works at the department with id = 11");
        System.out.println("Employer " + employerDAOImpl.searchEmployerByDepartmentID(12l) + " works at the department with id = 12");
        System.out.println("Employer " + employerDAOImpl.searchEmployerByDepartmentID(13l) + " works at the department with id = 13");
        System.out.println("-----");
        // Join information from both tables
        List listInfo = employerDAOImpl.selectAllInformationAboutEmployers();
        for (Object info : listInfo) {
            System.out.println(info);
        }
        System.out.println("-----");
        // CRUD operations - Delete
        departmentDAO.delete(11l);
        departmentDAO.delete(12l);
        departmentDAO.delete(13l);
        // CRUD operations - Read All after Delete
        listDepartments = departmentDAO.readAll();
        for (Department eachDepartment : listDepartments) {
            System.out.println(eachDepartment);
        }
        System.out.println("Delete of Departments is complete. \n-----");

        // CRUD operations - Delete
        employerDAO.delete(1l);
        employerDAO.delete(2l);
        employerDAO.delete(3l);

        // CRUD operations - Read All after Delete
        list = employerDAO.readAll();
        for (Employer eachEmployer : list) {
            System.out.println("After delete from data base:" + eachEmployer);
        }
        System.out.println("Delete of Employers is complete. \n-----");

    }



}
