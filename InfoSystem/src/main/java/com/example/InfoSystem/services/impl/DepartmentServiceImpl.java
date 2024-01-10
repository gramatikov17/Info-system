package com.example.InfoSystem.services.impl;

import com.example.InfoSystem.domain.entities.Department;
import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.entities.Management;
import com.example.InfoSystem.domain.models.DepartmentDto;
import com.example.InfoSystem.domain.models.EmployeeDto;
import com.example.InfoSystem.domain.models.ManagementDto;
import com.example.InfoSystem.repositories.DepartmentRepository;
import com.example.InfoSystem.services.DepartmentService;
import com.example.InfoSystem.services.EmployeeService;
import com.example.InfoSystem.services.ManagementService;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;
    private final ManagementService managementService;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeService employeeService, @Lazy ManagementService managementService, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
        this.managementService = managementService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Department createDepartment(DepartmentDto departmentDto) {

        Department department = modelMapper.map(departmentDto, Department.class);

        if (departmentRepository.findByName(departmentDto.getName()) != null) {

            throw new IllegalArgumentException("This department already exist");

        } else {

            Department department1 = departmentRepository.saveAndFlush(department);


            if (departmentDto.getEmployees() != null) {

                employeeService.addDepartmentToEmployee(department1);
                employeeService.addBossFromDepartment(department1);

            }
            return departmentRepository.saveAndFlush(department1);

        }
    }


    @Override
    public void addDepartmentsToManagement(Set<DepartmentDto> departments, Management management) {

        for (DepartmentDto dep : departments) {
            Department department = departmentRepository.findByName(dep.getName());

            department.setManagement(management);
            departmentRepository.saveAndFlush(department);
        }

    }

    @Override
    public Department getDepartmentByName(String name) {

        return departmentRepository.findByName(name);
    }


    @Override
    public void addBoss(Department department, Employee employee) {

        if (department.getBoss() == null) {
            department.setBoss(employee);
        } else {
            Employee boss = department.getBoss();

            employeeService.changePositionOfEmployeeToDeveloper(boss);

            department.setBoss(null);
            department.setBoss(employee);
        }
        departmentRepository.saveAndFlush(department);
    }

    @Override
    public void deleteDepartmentWithId(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(department.isEmpty()){
            throw new EntityExistsException("This department doesn`t exist");
        } else {
            departmentRepository.delete(department.get());
        }
    }

    @Override
    public void changeDepartmentName(Long departmentId , DepartmentDto departmentDto) {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(department.isEmpty()){
            throw new EntityExistsException(String.format("Department with id %s doesn`t exist" , departmentId));
        }

        String departmentName = departmentDto.getName();
        department.get().setName(departmentName);
        departmentRepository.saveAndFlush(department.get());

    }

    @Override
    public void setManagementToDepartment(Long departmentId, DepartmentDto departmentDto) {
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(department.isEmpty()){
            throw new EntityExistsException(String.format("Department with id %s doesn`t exist" , departmentId));
        }

        String managementName = departmentDto.getManagement().getName();
        Management managementByName = managementService.getManagementByName(managementName);

        department.get().setManagement(managementByName);
        departmentRepository.saveAndFlush(department.get());
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void setBossToDepartment(Department department, Employee boss) {
        department.setBoss(boss);
        departmentRepository.saveAndFlush(department);
    }

}
