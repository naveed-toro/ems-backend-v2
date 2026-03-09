package com.naveed.emsbackendv2.service.impl;

import com.naveed.emsbackendv2.model.dto.request.CreateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.request.UpdateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.response.EmployeeResponseDto;
import com.naveed.emsbackendv2.model.entities.Department;
import com.naveed.emsbackendv2.model.entities.Employee;
import com.naveed.emsbackendv2.model.entities.Position;
import com.naveed.emsbackendv2.model.mapper.EmployeeMapper;
import com.naveed.emsbackendv2.model.repository.DepartmentRepository;
import com.naveed.emsbackendv2.model.repository.EmployeeRepository;
import com.naveed.emsbackendv2.model.repository.PositionRepository;
import com.naveed.emsbackendv2.service.AuditLogService;
import com.naveed.emsbackendv2.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuditLogService auditLogService;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDto createEmployee(CreateEmployeeDto dto) {
        Department department = departmentRepository.findByUuid(dto.departmentUuid())
                .orElseThrow(() -> new RuntimeException("Department not found with UUID: " + dto.departmentUuid()));

        Position position = positionRepository.findByUuid(dto.positionUuid())
                .orElseThrow(() -> new RuntimeException("Position not found with UUID: " + dto.positionUuid()));

        Employee employee = employeeMapper.toEntity(dto);
        employee.setUuid(UUID.randomUUID().toString());
        employee.setIsDeleted(false);
        employee.setDepartment(department);
        employee.setPosition(position);

        Employee savedEmployee = employeeRepository.save(employee);

        // ✅ فکسڈ: اب صرف 3 پیرامیٹرز بھیج رہے ہیں
        auditLogService.logAction("CREATE", "Employee", savedEmployee.getUuid());

        return employeeMapper.toResponseDto(savedEmployee);
    }

    @Override
    public Page<EmployeeResponseDto> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(employeeMapper::toResponseDto);
    }

    @Override
    public EmployeeResponseDto getEmployeeByUuid(String uuid) {
        Employee employee = employeeRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Employee not found with UUID: " + uuid));
        return employeeMapper.toResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto updateEmployeeByUuid(String uuid, UpdateEmployeeDto dto) {
        Employee employee = employeeRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Employee not found with UUID: " + uuid));

        Department department = departmentRepository.findByUuid(dto.departmentUuid())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Position position = positionRepository.findByUuid(dto.positionUuid())
                .orElseThrow(() -> new RuntimeException("Position not found"));

        employee.setName(dto.name());
        employee.setEmail(dto.email());
        employee.setDepartment(department);
        employee.setPosition(position);

        Employee updatedEmployee = employeeRepository.save(employee);

        // ✅ فکسڈ: اپڈیٹ لاگ (صرف 3 پیرامیٹرز)
        auditLogService.logAction("UPDATE", "Employee", updatedEmployee.getUuid());

        return employeeMapper.toResponseDto(updatedEmployee);
    }

    @Override
    public String deleteEmployeeByUuid(String uuid) {
        Employee employee = employeeRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Employee not found with UUID: " + uuid));

        employee.setIsDeleted(true);
        employeeRepository.save(employee);

        // ✅ فکسڈ: ڈیلیٹ لاگ (صرف 3 پیرامیٹرز)
        auditLogService.logAction("DELETE", "Employee", uuid);

        return uuid;
    }

    @Override
    public Page<EmployeeResponseDto> searchEmployeesByName(String name, Pageable pageable) {
        Page<Employee> employees = employeeRepository.findByNameContainingIgnoreCase(name, pageable);
        return employees.map(employeeMapper::toResponseDto);
    }
}