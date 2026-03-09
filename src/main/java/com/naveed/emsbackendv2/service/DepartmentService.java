package com.naveed.emsbackendv2.service;

import com.naveed.emsbackendv2.model.dto.request.CreateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.response.DepartmentResponseDto;
import java.util.List;

public interface DepartmentService {

    // 1. نیا ڈیپارٹمنٹ بنانے کے لیے
    DepartmentResponseDto createDepartment(CreateDepartmentDto createDepartmentDto);

    // 2. سارے ڈیپارٹمنٹس کی لسٹ دیکھنے کے لیے
    List<DepartmentResponseDto> getAllDepartments();

    // 3. کسی ایک خاص ڈیپارٹمنٹ کو اس کے UUID سے تلاش کرنے کے لیے
    DepartmentResponseDto getDepartmentByUuid(String uuid);

    // نوٹ: Update اور Delete کے فنکشنز ہم اگلے مرحلے میں شامل کریں گے
}