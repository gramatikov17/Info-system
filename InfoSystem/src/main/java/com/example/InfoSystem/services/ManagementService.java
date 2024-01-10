package com.example.InfoSystem.services;

import com.example.InfoSystem.domain.entities.Management;
import com.example.InfoSystem.domain.models.EmployeeDto;
import com.example.InfoSystem.domain.models.ManagementDto;

import java.util.List;

public interface ManagementService {
    Management createManagement (ManagementDto managementDto);

    void deleteManagementById(Long managementId);

    Management getManagementByName(String managementName);

    List<Management> getAllManagements();

    void changeDirector(Long managementId, ManagementDto managementDto);
}
