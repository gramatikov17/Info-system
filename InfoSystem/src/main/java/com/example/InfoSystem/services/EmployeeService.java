package com.example.InfoSystem.services;

import com.example.InfoSystem.domain.entities.Department;
import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.models.DepartmentDto;
import com.example.InfoSystem.domain.models.EmployeeDto;

import java.util.List;
import java.util.Set;

public interface EmployeeService {

    Employee createEmployee(EmployeeDto employeeDTO);

    void addBossFromDepartment(Department department);

    void addDepartmentToEmployee(Department department);

    void updateEmployeeToBoss(Long employeeId);

    void changePositionOfEmployeeToDeveloper(Employee boss);

    void deleteEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee changeEmployeePosition(int previous, int next);
}
