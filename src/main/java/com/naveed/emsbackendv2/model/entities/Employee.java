package com.naveed.emsbackendv2.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ٹیچر کے بتائے ہوئے اصول کے مطابق ہم سیکیورٹی کے لیے UUID استعمال کریں گے
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(name = "employee_name", length = 150, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    // Soft Delete کے لیے (تاکہ ڈیٹا بیس سے ہمیشہ کے لیے ڈیلیٹ نہ ہو)
    private Boolean isDeleted;
    // کئی ملازمین ایک ڈیپارٹمنٹ کا حصہ ہیں (Many-To-One Relationship)
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    // کئی ملازمین کی ایک ہی پوزیشن ہو سکتی ہے (Many-To-One Relationship)
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}