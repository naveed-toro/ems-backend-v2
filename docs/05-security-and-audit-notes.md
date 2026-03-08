# 🚀 Phase 5: Security & Audit Logging Foundation

## 1. The `User` Entity (Feature 5 - Security)
**Location:** `com.naveed.emsbackendv2.model.entities.User`
* **What we did:** Created the `User` entity to map with the `users` table.
* **Key Security Decision:** Deliberately excluded the `password` field from our local database.
* **The "Why" Factor:** According to Teacher Kim Chansokpheng's **Jan 3, 2026** class, we delegate authentication entirely to Keycloak. The `uuid` field here will simply store Keycloak's generated user ID, keeping our local system securely in sync without storing sensitive credentials.

## 2. The `AuditLog` Entity (Feature 4 - Auditing)
**Location:** `com.naveed.emsbackendv2.model.entities.AuditLog`
* **What we did:** Created the `AuditLog` entity to track system activities.
* **The "Why" Factor:** This strictly fulfills **Feature 4 (Audit / Logging)** of the final assignment. It is designed to track which `action` (CREATE, UPDATE, DELETE) occurred on which target (`entityName` and `entityId`), and links it to the exact Keycloak user via the `performedBy` field.