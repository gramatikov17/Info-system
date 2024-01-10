package com.example.InfoSystem.services.impl;

import com.example.InfoSystem.domain.entities.Department;
import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.entities.Position;
import com.example.InfoSystem.domain.models.DepartmentDto;
import com.example.InfoSystem.domain.models.EmployeeDto;
import com.example.InfoSystem.repositories.EmployeeRepository;
import com.example.InfoSystem.services.DepartmentService;
import com.example.InfoSystem.services.EmployeeService;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, @Lazy DepartmentService departmentService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addBossFromDepartment(Department department) {

        Employee employee = employeeRepository.findById(department.getBoss().getId()).get();

        employee.setDepartment(department);

        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void addDepartmentToEmployee(Department department) {
        Set<Employee> employees = new HashSet<>();

        for (Employee emp : department.getEmployees()) {

            Employee byEgn = employeeRepository.findByEgn(emp.getEgn());
            byEgn.setDepartment(department);
            employees.add(emp);
        }
        employeeRepository.saveAll(employees);
    }

    @Override
    public void updateEmployeeToBoss(Long employeeId) {
        Optional<Employee> byId = employeeRepository.findById(employeeId);

        if (byId.isEmpty()) {
            throw new EntityExistsException(String.format("The Employee with id %s doesn`t exist", employeeId));
        }

        Department department = byId.get().getDepartment();

        departmentService.addBoss(department, byId.get());

        byId.get().setPosition(Position.BOSS);

        employeeRepository.saveAndFlush(byId.get());
    }

    @Override
    public void changePositionOfEmployeeToDeveloper(Employee boss) {
        boss.setPosition(Position.DEVELOPER);
        employeeRepository.saveAndFlush(boss);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {

        Optional<Employee> byId = employeeRepository.findById(employeeId);

        if (byId.isPresent()) {

            employeeRepository.delete(byId.get());
        } else {
            throw new EntityExistsException(String.format("The Employee with this id %s doesn`t exist", employeeId));
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> byId = employeeRepository.findById(id);

        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new EntityExistsException("This employee doesn`t exist");
        }
    }

    @Override
    public Employee changeEmployeePosition(int previousEmployeeEng, int nextEmployeeEgn) {

        Employee previousDirector = employeeRepository.findByEgn(previousEmployeeEng);

        Employee newDirector = employeeRepository.findByEgn(nextEmployeeEgn);

        if (previousDirector == null) {
            throw new EntityExistsException(String.format("Employee with id %s does not exist", previousEmployeeEng));
        }
        if (newDirector == null) {
            throw new EntityExistsException(String.format("Employee with id %s does not exist", nextEmployeeEgn));
        }
        previousDirector.setPosition(Position.TESTER);
        newDirector.setPosition(Position.DIRECTOR);
        newDirector.setDepartment(null);

        employeeRepository.saveAndFlush(previousDirector);
        return employeeRepository.saveAndFlush(newDirector);

    }


    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findByEgn(employeeDto.getEgn());

        if (existingEmployee != null) {

            throw new EntityExistsException("This Employee already exist");

        } else {

            Employee employee = modelMapper.map(employeeDto, Employee.class);

            Department departmentByName = departmentService.getDepartmentByName(employeeDto.getDepartment().getName());

            if (departmentByName == null) {
                return employeeRepository.saveAndFlush(employee);

            } else {
                departmentService.setBossToDepartment(departmentByName, employee.getDepartment().getBoss());
                employee.setDepartment(departmentByName);
                return employeeRepository.saveAndFlush(employee);

            }

        }
    }

}
