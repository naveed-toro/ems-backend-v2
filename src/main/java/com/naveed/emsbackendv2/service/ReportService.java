package com.naveed.emsbackendv2.service;

public interface ReportService {
    // 1. CSV فائل بنانے کا فنکشن
    byte[] generateEmployeeCsvReport();

    // 2. نیا: PDF فائل بنانے کا فنکشن
    byte[] generateEmployeePdfReport();
}