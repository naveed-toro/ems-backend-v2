# 🚀 Phase 6: Data Access Layer (Repositories)

## 1. JPA Repositories Implementation
**Location:** `com.naveed.emsbackendv2.model.repository`
* **What we did:** Created repository interfaces for all 5 core entities (`Employee`, `Department`, `Position`, `User`, `AuditLog`).
* **The Logic:** By extending `JpaRepository`, we inherited powerful, built-in CRUD operations (like `save()`, `findAll()`, `findById()`) without having to write manual boilerplate SQL queries.
* **Reference:** Applied the exact data access architecture and repository patterns taught by **Teacher Kim Chansokpheng** in the **Nov 16, 2025** class.

## 2. Custom Query Methods & Security
**What we did:** Added custom finder methods to specific repositories based on our business and security rules.
* **`findByUuid(String uuid)`:** Added to `Employee`, `Department`, `Position`, and `User` repositories.
    * **The "Why" Factor:** To securely retrieve records from the database using the unique UUID instead of the internal, sequential database `id`. This strict practice prevents ID-guessing attacks.
* **`findByEmail(String email)`:** Added to `UserRepository`.
    * **The "Why" Factor:** This is strategically added to support future authentication workflows. Specifically, it prepares our backend for the "Forgot Password" and keycloak logic demonstrated by the teacher in the **Jan 4, 2026** class.