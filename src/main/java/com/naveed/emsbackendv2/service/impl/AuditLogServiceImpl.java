package com.naveed.emsbackendv2.service.impl;

import com.naveed.emsbackendv2.model.entities.AuditLog;
import com.naveed.emsbackendv2.model.repository.AuditLogRepository;
import com.naveed.emsbackendv2.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void logAction(String action, String entityName, String entityId) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setPerformedAt(LocalDateTime.now());

        // 🪄 میجک: سیکیورٹی کانٹیکسٹ سے لاگ ان یوزر کا نام نکالنا
        log.setPerformedBy(getCurrentUsername());

        auditLogRepository.save(log);
    }

    private String getCurrentUsername() {
        // اسپرنگ سیکیورٹی سے کرنٹ لاگ ان بندے کی معلومات لینا
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            // Keycloak ٹوکن میں عام طور پر "preferred_username" اصل یوزر نیم ہوتا ہے
            return jwt.getClaimAsString("preferred_username");
        }

        return "Anonymous"; // اگر کوئی لاگ ان نہ ہو (مثلاً پبلک API)
    }
}