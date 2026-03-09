package com.naveed.emsbackendv2.service.impl;

import com.naveed.emsbackendv2.model.dto.request.CreateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.response.DepartmentResponseDto;
import com.naveed.emsbackendv2.model.entities.Department;
import com.naveed.emsbackendv2.model.mapper.DepartmentMapper;
import com.naveed.emsbackendv2.model.repository.DepartmentRepository;
import com.naveed.emsbackendv2.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

// یہ اینوٹیشن اسپرنگ کو بتاتی ہے کہ یہ ہماری مین بزنس لاجک کی کلاس ہے
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    // Dependency Injection (ریپازٹری اور میپر کو یہاں بلایا گیا ہے)
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDto createDepartment(CreateDepartmentDto createDepartmentDto) {
        // 1. DTO کو Entity میں تبدیل کریں (MapStruct میپر کی مدد سے)
        Department department = departmentMapper.toEntity(createDepartmentDto);

        // 2. سیکیورٹی کے لیے نیا منفرد UUID خود جنریٹ کریں
        department.setUuid(UUID.randomUUID().toString());
        department.setIsDeleted(false); // شروع میں ڈیلیٹ اسٹیٹس false ہوگا

        // 3. ڈیٹا بیس میں محفوظ کریں (Repository کی مدد سے)
        Department savedDepartment = departmentRepository.save(department);

        // 4. واپس یوزر کو دکھانے کے لیے Entity کو ResponseDTO میں بدلیں
        return departmentMapper.toResponseDto(savedDepartment);
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        // ڈیٹا بیس سے تمام ڈیپارٹمنٹس نکالیں اور انہیں DTOs کی لسٹ میں بدل کر بھیج دیں
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDto getDepartmentByUuid(String uuid) {
        // UUID کے ذریعے ڈیپارٹمنٹ تلاش کریں، اگر نہ ملے تو ایرر دیں
        Department department = departmentRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Department not found with UUID: " + uuid));

        return departmentMapper.toResponseDto(department);
    }
}