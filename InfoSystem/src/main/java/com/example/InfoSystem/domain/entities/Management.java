package com.example.InfoSystem.domain.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "managements")
public class Management extends BaseEntity {
    private String name;
    private String description;
    private Employee director;
    private Set<Department> departments;

    public Management() {

    }

    @Column(name = "name" , nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description" , nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id" , nullable = false , unique = true)
    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    @OneToMany(mappedBy = "management" , cascade = CascadeType.ALL)
    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
