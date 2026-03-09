package com.naveed.emsbackendv2.service.impl;

import com.naveed.emsbackendv2.model.dto.request.CreateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.request.UpdateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.response.DepartmentResponseDto;
import com.naveed.emsbackendv2.model.entities.Department;
import com.naveed.emsbackendv2.model.mapper.DepartmentMapper;
import com.naveed.emsbackendv2.model.repository.DepartmentRepository;
import com.naveed.emsbackendv2.service.AuditLogService; // 👈 آڈٹ سروس کی امپورٹ
import com.naveed.emsbackendv2.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final AuditLogService auditLogService; // 👈 ڈائری منگوا لی

    @Override
    public DepartmentResponseDto createDepartment(CreateDepartmentDto dto) {
        Department department = departmentMapper.toEntity(dto);
        department.setUuid(UUID.randomUUID().toString());
        department.setIsDeleted(false);
        Department savedDepartment = departmentRepository.save(department);

        // 🪄 جادو: نیا ڈیپارٹمنٹ بننے کا ریکارڈ ڈائری میں لکھنا
        auditLogService.logAction("CREATE", "Department", savedDepartment.getUuid(), "System_User");

        return departmentMapper.toResponseDto(savedDepartment);
    }

    @Override
    public Page<DepartmentResponseDto> getAllDepartments(Pageable pageable) {
        return departmentRepository.findAll(pageable)
                .map(departmentMapper::toResponseDto);
    }

    @Override
    public DepartmentResponseDto getDepartmentByUuid(String uuid) {
        Department department = departmentRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Department not found with UUID: " + uuid));
        return departmentMapper.toResponseDto(department);
    }

    @Override
    public DepartmentResponseDto updateDepartmentByUuid(String uuid, UpdateDepartmentDto dto) {
        Department department = departmentRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Department not found with UUID: " + uuid));

        department.setName(dto.name());
        Department updatedDepartment = departmentRepository.save(department);

        // 🪄 جادو: ڈیپارٹمنٹ کے اپڈیٹ ہونے کا ریکارڈ ڈائری میں لکھنا
        auditLogService.logAction("UPDATE", "Department", updatedDepartment.getUuid(), "System_User");

        return departmentMapper.toResponseDto(updatedDepartment);
    }

    @Override
    public String deleteDepartmentByUuid(String uuid) {
        Department department = departmentRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Department not found with UUID: " + uuid));

        department.setIsDeleted(true);
        departmentRepository.save(department);

        // 🪄 جادو: ڈیپارٹمنٹ کے ڈیلیٹ ہونے کا ریکارڈ ڈائری میں لکھنا
        auditLogService.logAction("DELETE", "Department", uuid, "System_User");

        return uuid;
    }

    @Override
    public Page<DepartmentResponseDto> searchDepartmentsByName(String name, Pageable pageable) {
        return departmentRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(departmentMapper::toResponseDto);
    }
}