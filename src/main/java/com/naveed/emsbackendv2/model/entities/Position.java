package com.naveed.emsbackendv2.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "positions")
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    // پوزیشن کا نام (جیسے Manager, Developer)
    @Column(name = "position_name", length = 100, nullable = false)
    private String name;

    private Boolean isDeleted;

    // ایک پوزیشن پر کئی ملازمین کام کر سکتے ہیں (One-To-Many Relationship)
    @OneToMany(mappedBy = "position")
    private Set<Employee> employees = new HashSet<>();
}