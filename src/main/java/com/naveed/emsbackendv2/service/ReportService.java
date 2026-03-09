package com.naveed.emsbackendv2.service;

public interface ReportService {

    // 1. CSV فائل بنانے کا فنکشن (فلٹرز کے ساتھ)
    byte[] generateEmployeeCsvReport(String departmentUuid, String positionUuid, String status);

    // 2. PDF فائل بنانے کا فنکشن (فلٹرز کے ساتھ)
    byte[] generateEmployeePdfReport(String departmentUuid, String positionUuid, String status);
}