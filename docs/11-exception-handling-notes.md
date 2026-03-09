# 🚀 Phase 11: Global Exception Handling

## 1. Centralized Error Management
**Location:** `com.naveed.emsbackendv2.exception.GlobalExceptionHandler`
* **What we did:** Created a global error handler using the `@RestControllerAdvice` annotation.
* **The "Why" Factor:** Instead of letting Spring Boot return ugly, raw Java stack traces to the frontend when an error occurs, this interceptor catches all exceptions and formats them into clean, standardized, and user-friendly JSON responses.

## 2. Handled Exceptions
* **`RuntimeException`:** Caught to return a `404 Not Found` status with a clear error message whenever a required resource (like a specific Department or Position UUID) does not exist in the database.
* **`MethodArgumentNotValidException`:** Caught to return a `400 Bad Request`. It intelligently extracts the specific validation errors (e.g., from `@NotBlank` or `@Email` in our DTOs) and presents exactly which fields failed the validation.