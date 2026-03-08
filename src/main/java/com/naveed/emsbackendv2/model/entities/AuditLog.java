package com.naveed.emsbackendv2.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // کونسا ایکشن ہوا؟ (جیسے CREATE, UPDATE, DELETE)
    @Column(nullable = false)
    private String action;

    // کس ٹیبل میں ایکشن ہوا؟ (جیسے Employee, Department)
    @Column(name = "entity_name", nullable = false)
    private String entityName;

    // اس ریکارڈ کی ID کیا تھی؟
    @Column(name = "entity_id", nullable = false)
    private String entityId;

    // یہ ایکشن کس نے کیا؟ (یہاں ہم Keycloak والے یوزر کا UUID محفوظ کریں گے)
    @Column(name = "performed_by", nullable = false)
    private String performedBy;

    // یہ ایکشن کس وقت ہوا؟
    @Column(name = "performed_at", nullable = false)
    private LocalDateTime performedAt;
}