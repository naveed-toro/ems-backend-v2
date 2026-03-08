# 🏗️ Employee Management System (EMS) - v2
**Deadline:** 02/20/2026

## 1. Tech Stack Decisions
* **Spring Boot:** 3.5.11 (Stable/GA) - Chosen for stability and compatibility with teacher's 3.x guidelines.
* **Java:** 21 (LTS) - Long-term support version.
* **Build Tool:** Gradle (Groovy) - Fast and modern dependency management.
* **Database:** PostgreSQL.
* **Dependencies:** Lombok, Spring Web, Spring Data JPA, Validation, Spring Security, OAuth2 Resource Server, DevTools.

## 2. Project Requirements & Features (As per Final Assignment)
Based on the official assignment guidelines, the following features will be implemented:

* **0. Database Architecture:** At least 5 tables with proper relationships.
* **1. Employee Management (CRUD):**
    * Fetching data with pagination and sorting.
    * Searching employees by name.
* **2. Department Management (CRUD):**
    * Fetching data with pagination and sorting.
    * Searching departments by name.
* **3. Export / Reporting:**
    * Generate reports in CSV, Excel, or PDF formats.
    * Filter reports by department, position, or status.
* **4. Audit / Logging:**
    * Track user actions (who created/updated/deleted employees and when).
* **5. Security (Auth):**
    * OAuth2 Implementation using **Keycloak**.

## 3. Database Design Plan (The 5 Tables Rule)
To fulfill the "At least 5 tables" requirement, our architecture will include:
1. `employees` (Main entity)
2. `departments` (Relation: One Department has Many Employees)
3. `positions` (Job titles/roles for filtering)
4. `audit_logs` (To track create/update/delete actions for Feature 4)
5. `users` (For Keycloak UUID mapping and system roles)