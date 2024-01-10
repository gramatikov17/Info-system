package com.example.InfoSystem.controllers;

import com.example.InfoSystem.domain.entities.Department;
import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.entities.Management;
import com.example.InfoSystem.domain.models.ManagementDto;
import com.example.InfoSystem.services.ManagementService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> createManagement(@RequestBody ManagementDto managementDto) {
        try {
            Management management = managementService.createManagement(managementDto);
            return new ResponseEntity<>("Management created successfully with ID: " + management.getId(), HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create employee: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    @DeleteMapping("delete/{managementId}")
    public ResponseEntity<String> deleteManagement(@PathVariable Long managementId) {
        try {
            managementService.deleteManagementById(managementId);
            return new ResponseEntity<>("Management deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Management: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllManagement() {
        try {
            List<Management> managements = managementService.getAllManagements();
            return ResponseEntity.ok(managements);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @PutMapping("/updateDirector/{managementId}")
    public ResponseEntity<?> changeDirect(@PathVariable Long managementId, @RequestBody ManagementDto managementDto) {
        try {
            managementService.changeDirector(managementId, managementDto);
            return new ResponseEntity<>("Management was updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete Management: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}

