package com.example.InfoSystem.services.impl;

import com.example.InfoSystem.domain.entities.Employee;
import com.example.InfoSystem.domain.entities.Management;
import com.example.InfoSystem.domain.models.ManagementDto;
import com.example.InfoSystem.repositories.ManagementRepository;
import com.example.InfoSystem.services.DepartmentService;
import com.example.InfoSystem.services.EmployeeService;
import com.example.InfoSystem.services.ManagementService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementServiceImpl implements ManagementService {

    private final ManagementRepository managementRepository;
    private final EmployeeService employeeService;

    private final DepartmentService departmentService;

    private final ModelMapper modelMapper;

    public ManagementServiceImpl(ManagementRepository managementRepository, EmployeeService employeeService, DepartmentService departmentService, ModelMapper modelMapper) {
        this.managementRepository = managementRepository;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Management createManagement(ManagementDto managementDto) {
        if (managementRepository.findByName(managementDto.getName()) != null) {
            throw new IllegalArgumentException("This management already exist");
        } else {
            Management newManagement = modelMapper.map(managementDto, Management.class);

            Management management = managementRepository.saveAndFlush(newManagement);
            departmentService.addDepartmentsToManagement(managementDto.getDepartments(), management);

            return managementRepository.saveAndFlush(management);
        }
    }


    @Override
    public void deleteManagementById(Long managementId) {
        Management management = managementRepository.findById(managementId)
                .orElseThrow(() -> new EntityNotFoundException("Management not found with id: " + managementId));
        managementRepository.delete(management);
    }

    @Override
    public Management getManagementByName(String managementName) {
        Management management = managementRepository.findByName(managementName);

        if (management == null) {
            throw new EntityExistsException(String.format("Management with name %s doesn`t exist", managementName));
        }
        return management;
    }

    @Override
    public List<Management> getAllManagements() {
        return managementRepository.findAll();
    }

    @Override
    public void changeDirector(Long managementId, ManagementDto managementDto) {
        Optional<Management> management = managementRepository.findById(managementId);

        if (management.isEmpty()) {
            throw new EntityExistsException(String.format("Management with Id %s doesn`t exist", managementId));
        }

        int newDirectorEgn = managementDto.getDirector().getEgn();
        Employee employee = employeeService.changeEmployeePosition(management.get().getDirector().getEgn(), newDirectorEgn);
        management.get().setDirector(employee);
        managementRepository.saveAndFlush(management.get());
    }
}
