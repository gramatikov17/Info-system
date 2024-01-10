package com.example.InfoSystem.domain.models;

import com.example.InfoSystem.domain.entities.Department;
import com.example.InfoSystem.domain.entities.Position;

public class EmployeeDto {
    private String firstName;
    private String lastName;
    private int egn;
    private int age;
    private DepartmentDto department;

    public EmployeeDto() {

    }

    private Position position;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEgn() {
        return egn;
    }

    public void setEgn(int egn) {
        this.egn = egn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
