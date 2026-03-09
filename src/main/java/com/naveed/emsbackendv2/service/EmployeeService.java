package com.naveed.emsbackendv2.service;

import com.naveed.emsbackendv2.model.dto.request.CreateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.request.UpdateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.response.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    // 1. Create (نیا ملازم بنانا)
    EmployeeResponseDto createEmployee(CreateEmployeeDto createEmployeeDto);

    // 2. Read All (تمام ملازمین کو Pagination کے ساتھ دیکھنا)
    Page<EmployeeResponseDto> getAllEmployees(Pageable pageable);

    // 3. Read One (کسی ایک خاص ملازم کو تلاش کرنا)
    EmployeeResponseDto getEmployeeByUuid(String uuid);

    // 4. Update (ملازم کا ڈیٹا تبدیل کرنا)
    EmployeeResponseDto updateEmployeeByUuid(String uuid, UpdateEmployeeDto updateEmployeeDto);

    // 5. Delete (ملازم کو ڈیلیٹ کرنا)
    String deleteEmployeeByUuid(String uuid);
}