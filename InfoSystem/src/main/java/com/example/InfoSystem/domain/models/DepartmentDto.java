package com.example.InfoSystem.domain.models;


import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.entities.Management;

import java.util.Set;

public class DepartmentDto {

    private String name;
    private String description;
    private ManagementDto management;
    private EmployeeDto boss;
    private Set<EmployeeDto> employees;

    public DepartmentDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ManagementDto getManagement() {
        return management;
    }

    public void setManagement(ManagementDto management) {
        this.management = management;
    }

    public EmployeeDto getBoss() {
        return boss;
    }

    public void setBoss(EmployeeDto boss) {
        this.boss = boss;
    }

    public Set<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDto> employees) {
        this.employees = employees;
    }
}
