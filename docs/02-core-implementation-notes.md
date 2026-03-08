# 🚀 Phase 2: Core Code Implementation & Entities Setup

## 1. Standardized API Response (`ResponseTemplate`)
**Location:** `com.naveed.emsbackendv2.utils.ResponseTemplate`
* **What we did:** Created a generic Java `record` named `ResponseTemplate<T>`.
* **The "Why" Factor:** To ensure that every API response (whether success or error) follows a strict, uniform format throughout the project. It includes `status`, `date`, `message`, and the generic payload `data`.
* **Reference:** Implemented based on Teacher Kim Chansokpheng's standard REST API guidelines from the **Nov 8, 2025** class.
* **Tools used:** Lombok `@Builder` for clean object creation.

## 2. Database Mapping: The `Employee` Entity (Feature 0 & 1)
**Location:** `com.naveed.emsbackendv2.model.entities.Employee`
* **What we did:** Created the primary entity class mapped to the `employees` table in PostgreSQL.
* **Key Design Decisions & Security:**
    1. **Lombok `@Data`:** Used to automatically generate Getters, Setters, and Constructors, keeping the code clean.
    2. **Security (`uuid`):** Added a highly secure `uuid` field (`updatable = false`, `unique = true`). Instead of exposing the database's sequential auto-increment `id` (1, 2, 3...) to the frontend, we will expose this UUID. This prevents ID-guessing attacks (Reference: Security principles discussed on **Nov 8, 2025**).
    3. **Soft Deletion (`isDeleted`):** Added a boolean flag to support "Soft Delete" logic. Records will be hidden from queries rather than permanently wiped from the database.
    4. **Data Integrity:** Applied constraints like `nullable = false` and `length = 150` directly at the entity level.