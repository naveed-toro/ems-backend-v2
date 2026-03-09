package com.naveed.emsbackendv2.service;

public interface ReportService {
    // CSV فائل بنانے کا فنکشن
    byte[] generateEmployeeCsvReport();
}