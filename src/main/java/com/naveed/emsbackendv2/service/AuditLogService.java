package com.naveed.emsbackendv2.service;

public interface AuditLogService {
    // آڈٹ لاگ محفوظ کرنے کا نیا اور پروفیشنل فنکشن
    void logAction(String action, String entityName, String entityId, String performedBy);
}