package com.example.InfoSystem.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    private String name;
    private String description;
    private Management management;
    private Employee boss;
    private Set<Employee> employees;

    public Department() {

    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "management_id")
    @JsonIgnore
    public Management getManagement() {
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boss_id", unique = true)
    @JsonIgnore
    public Employee getBoss() {
        return boss;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
