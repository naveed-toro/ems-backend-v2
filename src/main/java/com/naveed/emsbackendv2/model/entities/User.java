package com.naveed.emsbackendv2.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // یہ UUID براہ راست Keycloak کے فراہم کردہ یوزر ID کے ساتھ جوڑا (Map کیا) جائے گا
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    // نوٹ: ٹیچر کے بتائے گئے سیکیورٹی اصول کے مطابق ہم یہاں 'password' کا کالم نہیں بنا رہے

    private Boolean isDeleted;
}