package com.naveed.emsbackendv2.model.dto.response;

public record EmployeeResponseDto(
        String uuid,
        String name,
        String email
        // نوٹ: ڈیپارٹمنٹ اور پوزیشن کے نام ہم بعد میں جوڑیں گے
) {
}