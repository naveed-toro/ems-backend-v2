# 🚀 Phase 3: Department Entity & Database Relationships

## 1. The `Department` Entity (Feature 2)
**Location:** `com.naveed.emsbackendv2.model.entities.Department`
* **What we did:** Created the `Department` entity mapped to the `departments` table.
* **Fields:** Added `id`, highly secure `uuid` (for API exposure), `name`, and a `isDeleted` flag for soft deletion.

## 2. JPA Relational Mapping (The Core Architecture)
**What we did:** Established a bidirectional database relationship between `Employee` and `Department`.

* **The Logic (One-To-Many):** One Department can have multiple Employees.
* **Implementation in `Department.java`:** Used `@OneToMany(mappedBy = "department")`. This tells Hibernate that the `Department` entity does not own the foreign key column; it is mapped by the `department` field in the `Employee` class.
* **Implementation in `Employee.java`:** Used `@ManyToOne` and `@JoinColumn(name = "department_id")`. This means multiple employees belong to one department, and the `employees` table in PostgreSQL will contain the actual foreign key column named `department_id`.

**Reference:** This relational mapping strictly follows the database design principles taught by **Teacher Kim Chansokpheng** in the **Nov 22, 2025** class (specifically referencing the Customer-Order relationship example).