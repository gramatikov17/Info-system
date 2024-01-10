package com.example.InfoSystem.repositories;

import com.example.InfoSystem.domain.entities.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementRepository extends JpaRepository<Management , Long> {

    Management findByName(String name);
}
