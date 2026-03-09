package com.naveed.emsbackendv2.service;

import com.naveed.emsbackendv2.model.dto.request.CreateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.request.UpdateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.response.DepartmentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    // 1. نیا ڈیپارٹمنٹ بنانا
    DepartmentResponseDto createDepartment(CreateDepartmentDto createDepartmentDto);

    // 2. Pagination کے ساتھ سارے ڈیپارٹمنٹ دیکھنا
    Page<DepartmentResponseDto> getAllDepartments(Pageable pageable);

    // 3. کسی ایک ڈیپارٹمنٹ کو تلاش کرنا
    DepartmentResponseDto getDepartmentByUuid(String uuid);

    // 4. ڈیپارٹمنٹ کا نام اپڈیٹ کرنا
    DepartmentResponseDto updateDepartmentByUuid(String uuid, UpdateDepartmentDto updateDepartmentDto);

    // 5. ڈیپارٹمنٹ کو ڈیلیٹ کرنا (Soft Delete)
    String deleteDepartmentByUuid(String uuid);

    // 6. ڈیپارٹمنٹ کو نام سے تلاش کرنا (Search by Name)
    Page<DepartmentResponseDto> searchDepartmentsByName(String name, Pageable pageable);
}