package com.java.courses.task9.entity;


public class Employer{

    private Long id;
    private String name;
    private Integer age;
    private String e_mail;
    private Long department_id;


    public Employer() {

    }

    public Employer(Long id, String name, Integer age, String e_mail, Long department_id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.e_mail =e_mail;
        this.department_id = department_id;



    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return e_mail;
    }

    public void setEmail(String e_mail) {
        this.e_mail = e_mail;
    }

    public Long getDepartmentId() {
        return department_id;
    }

    public void setDepartmentId(Long department_id) {
        this.department_id = department_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employer employer = (Employer) o;

        if (!age.equals(employer.age)) return false;
        if (!id.equals(employer.id)) return false;
        if (!name.equals(employer.name)) return false;
        if (!e_mail.equals(employer.e_mail)) return false;
        if (!department_id.equals(employer.department_id)) return false;



        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + e_mail.hashCode();
        result = 31 * result + department_id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", e_mail='" + e_mail +
                ", department_id='" + department_id +
                '}';
    }
}
