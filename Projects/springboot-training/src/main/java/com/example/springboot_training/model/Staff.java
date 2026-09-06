package com.example.springboot_training.model;

public class Staff {

    private int id;
    private String name;
    private String department;
    private String role;

    public Staff() {
    }

    public Staff(int id, String name, String department, String role) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{" + "id= " + id + ", name: " + name + '\'' + ", department: " + department + '\'' + ", Role: "
                + role + '\'' + "}";
    }

}
