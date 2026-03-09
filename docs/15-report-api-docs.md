# Export & Reporting API - Documentation

یہ ماڈیول یوزر کو اس قابل بناتا ہے کہ وہ سسٹم میں موجود تمام ملازمین کا ڈیٹا ڈاؤن لوڈ کر سکے۔ یہ فیچر انتظامی کاموں اور ڈیٹا کے تجزیے کے لیے انتہائی مفید ہے۔

## 🚀 فیچرز (Export Features)

### 1. ملازمین کی CSV رپورٹ (Excel Format)
* **Endpoint:** `GET /api/v1/reports/employees/csv`
* **کام کا طریقہ:** یہ API ڈیٹا بیس سے تمام ملازمین کا ریکارڈ لاتی ہے اور اسے `Apache Commons CSV` لائبریری کے ذریعے ایک ایکسل فائل میں تبدیل کر دیتی ہے۔
* **فائل کا نام:** `employees_report.csv`

### 2. ملازمین کی PDF رپورٹ (Portable Format)
* **Endpoint:** `GET /api/v1/reports/employees/pdf`
* **کام کا طریقہ:** یہ API `OpenPDF` لائبریری کا استعمال کرتے ہوئے ایک خوبصورت ٹیبل بناتی ہے جس میں ملازمین کی بنیادی تفصیلات شامل ہوتی ہیں۔
* **فائل کا نام:** `employees_report.pdf`

---
## 🛠️ استعمال شدہ ٹیکنالوجی (Libraries Used)
* **Apache Commons CSV:** ڈیٹا کو کومہ سیپریٹڈ ویلیوز میں بدلنے کے لیے۔
* **OpenPDF (LibrePDF):** جاوا میں پروفیشنل PDF ڈاکومنٹس تیار کرنے کے لیے۔