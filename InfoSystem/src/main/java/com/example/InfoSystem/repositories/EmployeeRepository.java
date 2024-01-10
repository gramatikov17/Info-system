package com.example.InfoSystem.repositories;

import com.example.InfoSystem.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEgn(int egn);

    List<Employee> findAll();

}
