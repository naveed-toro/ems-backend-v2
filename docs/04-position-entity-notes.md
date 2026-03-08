# 🚀 Phase 4: Position Entity & Reporting Foundation

## 1. The `Position` Entity
**Location:** `com.naveed.emsbackendv2.model.entities.Position`
* **What we did:** Created the `Position` entity mapped to the `positions` table.
* **Fields:** Added standard fields including `id`, `uuid` (for secure API exposure), `name` (e.g., Manager, Developer), and `isDeleted` (soft delete flag).

## 2. JPA Relational Mapping & The "Why" Factor
**What we did:** Established a bidirectional relationship between `Position` and `Employee`.
* **The Logic (One-To-Many):** One Position can be assigned to multiple Employees.
* **Implementation:** - In `Position.java`: `@OneToMany(mappedBy = "position")`.
    - In `Employee.java`: `@ManyToOne` with `@JoinColumn(name = "position_id")`.
* **The "Why" Factor (Feature 3):** This structure is explicitly required for **Feature 3 (Export / Reporting)** of our assignment. It lays the foundation for filtering employee reports by their job positions in the future.
* **Reference:** Applied the exact relational mapping logic demonstrated by Teacher Kim Chansokpheng in the **Nov 22, 2025** class.