package com.example.InfoSystem.domain.models;

import com.example.InfoSystem.domain.entities.Department;
import com.example.InfoSystem.domain.entities.Employee;

import java.util.Set;

public class ManagementDto {
    private String name;
    private String description;
    private EmployeeDto director;
    private Set<DepartmentDto> departments;

    public ManagementDto() {

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

    public EmployeeDto getDirector() {
        return director;
    }

    public void setDirector(EmployeeDto director) {
        this.director = director;
    }

    public Set<DepartmentDto> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentDto> departments) {
        this.departments = departments;
    }
}
