package com.naveed.emsbackendv2.service;

import com.naveed.emsbackendv2.model.dto.request.CreateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.response.EmployeeResponseDto;
import java.util.List;

public interface EmployeeService {

    // 1. نیا ملازم بنانے کے لیے
    EmployeeResponseDto createEmployee(CreateEmployeeDto createEmployeeDto);

    // 2. تمام ملازمین کی لسٹ دیکھنے کے لیے
    List<EmployeeResponseDto> getAllEmployees();

    // 3. کسی ایک خاص ملازم کو تلاش کرنے کے لیے
    EmployeeResponseDto getEmployeeByUuid(String uuid);
}