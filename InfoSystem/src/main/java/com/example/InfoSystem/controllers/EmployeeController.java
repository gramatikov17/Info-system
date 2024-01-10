package com.example.InfoSystem.controllers;

import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.models.EmployeeDto;
import com.example.InfoSystem.services.EmployeeService;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDTO) {

        try {
            Employee employee = employeeService.createEmployee(employeeDTO);
            return new ResponseEntity<>("Employee created successfully with ID: " + employee.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("update/{employeeId}")
    public ResponseEntity<String> updateEmployeeToBoss(@PathVariable Long employeeId) {
        try {

            employeeService.updateEmployeeToBoss(employeeId);

            return new ResponseEntity<>(String.format("Employee  with ID: %s was successfully updated", employeeId), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee to boss");
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long employeeId) {
        try {
            Employee employees = employeeService.getEmployeeById(employeeId);
            return ResponseEntity.ok(employees);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("delete/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long employeeId) {
        try {
            employeeService.deleteEmployeeById(employeeId);
            return new ResponseEntity<>(String.format("Employee  with ID: %s was successfully deleted", employeeId), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee to boss");
        }

    }


}
