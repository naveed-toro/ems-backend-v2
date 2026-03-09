package com.naveed.emsbackendv2.service;

public interface AuditLogService {
    // اب ہمیں performedBy باہر سے دینے کی ضرورت نہیں، سسٹم خود نکال لے گا
    void logAction(String action, String entityName, String entityId);
}