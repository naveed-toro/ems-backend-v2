# 🚀 Phase 9: Service Layer (Business Logic)

## 1. The Core Implementation
**Location:** `com.naveed.emsbackendv2.service` & `impl`
* **What we did:** Created Service Interfaces and their Implementations (`@Service`) for `Department`, `Employee`, and `Position`.
* **The "Why" Factor:** The Service Layer acts as the "brain" of our application. It isolates business rules from the Controller (API) and Repository (Database) layers. We used `@RequiredArgsConstructor` to inject our Repositories and MapStruct Mappers cleanly.

## 2. Business Rules & Validations Applied
* **Secure Identification:** Instead of relying on database IDs, we automatically generate a secure `UUID` (`UUID.randomUUID().toString()`) for every new record before saving it to the database.
* **Soft Delete Preparation:** We set `isDeleted(false)` by default for all new records.
* **Relationship Validation (Employee Creation):** Before creating an Employee, the system strictly validates if the provided `departmentUuid` and `positionUuid` actually exist in the database. If they don't, it throws a `RuntimeException`. This ensures data integrity and prevents orphan records.