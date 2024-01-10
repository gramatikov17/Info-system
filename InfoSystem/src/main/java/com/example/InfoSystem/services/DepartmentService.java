package com.example.InfoSystem.services;

import com.example.InfoSystem.domain.entities.Department;
import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.entities.Management;
import com.example.InfoSystem.domain.models.DepartmentDto;
import com.example.InfoSystem.domain.models.ManagementDto;

import java.util.List;
import java.util.Set;

public interface DepartmentService {
    Department createDepartment(DepartmentDto departmentDto);
    void addDepartmentsToManagement(Set<DepartmentDto> departments , Management management);

    Department getDepartmentByName(String name);

    void addBoss(Department department ,Employee employee);

    void deleteDepartmentWithId(Long departmentId);

    void changeDepartmentName(Long departmentId , DepartmentDto departmentDto);

    void setManagementToDepartment(Long departmentId, DepartmentDto departmentDto);

    List<Department> getAllDepartments();

    void setBossToDepartment(Department department, Employee boss);
}
