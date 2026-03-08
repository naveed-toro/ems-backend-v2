package com.naveed.emsbackendv2.utils;

import lombok.Builder;
import java.util.Date;

@Builder
public record ResponseTemplate<T>(
        String status,
        Date date,
        String message,
        T data
) {
}