package com.example.InfoSystem.controllers;

import com.example.InfoSystem.domain.entities.Department;
import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.models.DepartmentDto;
import com.example.InfoSystem.services.DepartmentService;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentDto departmentDto) {
        try {
            Department department = departmentService.createDepartment(departmentDto);
            return new ResponseEntity<>("Department created successfully with ID: " + department.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create department: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<?> deleteDepartmentWIthId(@PathVariable Long departmentId) {
        try {
            departmentService.deleteDepartmentWithId(departmentId);
        return new ResponseEntity<>(String.format("Department with id %s was successfully deleted" ,departmentId) , HttpStatus.OK);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/update/{departmentId}")
    public ResponseEntity<?> changeNameOfDepartment(@PathVariable Long departmentId , @RequestBody DepartmentDto departmentDto){
        try{
            departmentService.changeDepartmentName(departmentId , departmentDto);
            return new ResponseEntity<>(String.format("Department with Id %s was successfully updated" , departmentId), HttpStatus.OK);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/updateManagement/{departmentId}")
    public ResponseEntity<?> setManagementToDepartment(@PathVariable Long departmentId , @RequestBody DepartmentDto departmentDto){
        try{
            departmentService.setManagementToDepartment(departmentId , departmentDto);
            return new ResponseEntity<>(String.format("Department with Id %s was successfully updated" , departmentId), HttpStatus.OK);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
//    @PreAuthorize("hasRole('BOSS')")
    public ResponseEntity<?> getAllDepartments(){
        try {
            List<Department> departments =  departmentService.getAllDepartments();
            return ResponseEntity.ok(departments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

}

