This is a professional and comprehensive **README.md** for your project, tailored to meet the specific requirements of your final project proposal.

---

# 🏢 Employee Management System (EMS) v2

A robust, enterprise-grade management system built with **Spring Boot 3** and **Keycloak**. This system provides full control over employee and department records with integrated security and automated auditing.

## ✨ Key Features

* **🔐 OAuth2 Security:** Full integration with **Keycloak** for secure Authentication and Authorization.
* **🕵️‍♂️ Automated Audit Logging:** Seamlessly tracks every `CREATE`, `UPDATE`, and `DELETE` action, automatically capturing the username from the security token.
* **📊 Advanced Reporting:** Export employee lists to **PDF** and **CSV** formats with specific filters.
* **⚡ Professional CRUD:** Efficient data handling using Pagination, Sorting, and Search functionalities.
* **🗺️ Clean Mapping:** Implements **MapStruct** for decoupled and type-safe DTO-to-Entity transformations.

## 🛠 Tech Stack

* **Backend:** Java 21, Spring Boot 3.x, Spring Data JPA.
* **Database:** PostgreSQL.
* **Security:** Keycloak (OAuth2 / OpenID Connect).
* **Build Tool:** Gradle.
* **Utilities:** MapStruct, Lombok, OpenPDF, Apache Commons CSV.

---

## ⚙️ Configuration & Setup

### 1. Database Setup

Create a new database in PostgreSQL:

```sql
CREATE DATABASE ems_db;

```

### 2. Keycloak Configuration

* **Realm:** Create a realm named `ems-realm`.
* **Client:** Create a client `ems-client` (Ensure *Direct Access Grants* and *Service Accounts* are enabled).
* **Users:** Create an administrative user (e.g., `adminuser`) and set a non-temporary password.

### 3. Application Properties

Ensure your `src/main/resources/application.properties` matches your local setup:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ems_db
spring.datasource.username=your_postgres_user
spring.datasource.password=your_postgres_password

# Keycloak Security
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9090/realms/ems-realm

```

---

## 🚀 Running the Application

Open your terminal in the project root and run:

```bash
./gradlew bootRun

```

The server will start at `http://localhost:8080`.

---

## 🧪 Testing with Postman

1. **Authentication:** Request a token from Keycloak using the password grant type.
2. **Authorization:** Use the token as a **Bearer Token** in the header of your API requests.
3. **Audit Verification:** Perform a change (e.g., Update Department) and verify the `audit_logs` table in the database to see the automated entry.

---

## 👤 Author

* **Name:** Naveed
* **Course:** ISTAD Spring 12
* **Deadline:** 20/02/2026

---

---

# 🏢 ایمپلائی مینجمنٹ سسٹم (EMS) v2 - اردو ورژن

یہ ایک بہترین اور جدید مینیجمنٹ سسٹم ہے جسے **Spring Boot 3** اور **Keycloak** کے ذریعے تیار کیا گیا ہے۔ یہ سسٹم ملازمین اور محکموں کا ریکارڈ محفوظ طریقے سے رکھنے اور ہر تبدیلی پر نظر رکھنے کے لیے بنایا گیا ہے۔

## ✨ اہم خصوصیات

* **🔐 سیکیورٹی (OAuth2):** Keycloak کے ذریعے مکمل محفوظ لاگ ان اور رسائی کا کنٹرول۔
* **🕵️‍♂️ آٹومیٹڈ آڈٹ لاگنگ:** سسٹم خود بخود ہر تبدیلی (Create, Update, Delete) کا ریکارڈ رکھتا ہے اور ٹوکن سے یوزر کا نام نکال کر محفوظ کرتا ہے۔
* **📊 رپورٹنگ:** ملازمین کی فہرست کو **PDF** اور **CSV** فارمیٹ میں ڈاؤن لوڈ کرنے کی سہولت۔
* **⚡ پروفیشنل CRUD:** ڈیٹا کو ڈھونڈنے اور ترتیب دینے کے لیے Pagination اور سرچنگ کے فیچرز۔

## 🛠 ٹیکنالوجی اسٹیک

* **بیک اینڈ:** Java 21, Spring Boot 3.x
* **ڈیٹا بیس:** PostgreSQL
* **سیکیورٹی:** Keycloak
* **بلڈ ٹول:** Gradle

---

## ⚙️ سیٹ اپ کی ہدایات

### 1. ڈیٹا بیس

پہلے PostgreSQL میں `ems_db` کے نام سے ڈیٹا بیس بنائیں۔

### 2. Keycloak سیٹنگز

* Keycloak میں `ems-realm` کے نام سے ریلم بنائیں۔
* `ems-client` کے نام سے کلائنٹ بنائیں اور یوزر رجسٹر کریں۔

### 3. ایپلیکیشن پراپرٹیز

اپنی `application.properties` فائل میں ڈیٹا بیس کا پاسورڈ اور Keycloak کا URL چیک کر لیں۔

---

## 🚀 پراجیکٹ چلانے کا طریقہ

ٹرمینل میں یہ کمانڈ لکھیں:

```bash
./gradlew bootRun

```

---

## 👤 ڈیولپر (Developer)

* **نام:** نوید
* **کلاس:** ISTAD Spring 12
* **ڈیڈ لائن:** 20/02/2026

---
🏆
