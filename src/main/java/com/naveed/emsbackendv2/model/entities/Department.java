package com.naveed.emsbackendv2.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(name = "department_name", length = 100, nullable = false)
    private String name;

    private Boolean isDeleted;

    // ایک ڈیپارٹمنٹ میں کئی ملازمین (One-To-Many Relationship)
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();
}