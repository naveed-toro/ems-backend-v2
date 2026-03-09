package com.naveed.emsbackendv2.service.impl;

import com.naveed.emsbackendv2.model.entities.AuditLog;
import com.naveed.emsbackendv2.model.repository.AuditLogRepository;
import com.naveed.emsbackendv2.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void logAction(String action, String entityName, String entityId, String performedBy) {
        // آپ کی Entity کے مطابق نیا آڈٹ لاگ بنانا
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setPerformedBy(performedBy);
        log.setPerformedAt(LocalDateTime.now());

        // لاگ کو ڈیٹا بیس میں محفوظ کر دینا
        auditLogRepository.save(log);
    }
}