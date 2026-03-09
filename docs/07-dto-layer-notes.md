# 🚀 Phase 7: Data Transfer Objects (DTOs)

## 1. The DTO Pattern
**Location:** `com.naveed.emsbackendv2.model.dto`
* **What we did:** Created Request (`Create...Dto`) and Response (`...ResponseDto`) objects for Employee, Department, and Position using modern Java `record` structures.
* **The "Why" Factor:** To decouple the internal database models (Entities) from the external API representation. This hides sensitive database fields (like sequential `id`, `isDeleted`) and provides a strict, secure contract for API communication.
* **Reference:** Applied the architectural principles taught by **Teacher Kim Chansokpheng** on **Nov 8, 2025**.

## 2. Input Validation Integration
* **What we did:** Applied Jakarta Validation annotations (`@NotBlank`, `@Email`) to all Request DTOs.
* **The "Why" Factor:** To ensure data integrity and catch bad requests before they even reach our Business Logic (Service Layer) or Database. This strictly follows the validation practices demonstrated in the **Nov 29, 2025** class.