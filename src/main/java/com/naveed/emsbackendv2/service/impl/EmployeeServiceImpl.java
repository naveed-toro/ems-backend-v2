package com.naveed.emsbackendv2.service.impl;

import com.naveed.emsbackendv2.model.dto.request.CreateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.response.EmployeeResponseDto;
import com.naveed.emsbackendv2.model.entities.Department;
import com.naveed.emsbackendv2.model.entities.Employee;
import com.naveed.emsbackendv2.model.entities.Position;
import com.naveed.emsbackendv2.model.mapper.EmployeeMapper;
import com.naveed.emsbackendv2.model.repository.DepartmentRepository;
import com.naveed.emsbackendv2.model.repository.EmployeeRepository;
import com.naveed.emsbackendv2.model.repository.PositionRepository;
import com.naveed.emsbackendv2.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    // ہمیں ان چاروں چیزوں کی ضرورت پڑے گی
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDto createEmployee(CreateEmployeeDto dto) {

        // 1. پہلے چیک کریں کہ کیا ڈیپارٹمنٹ موجود ہے؟
        Department department = departmentRepository.findByUuid(dto.departmentUuid())
                .orElseThrow(() -> new RuntimeException("Department not found with UUID: " + dto.departmentUuid()));

        // 2. پھر چیک کریں کہ کیا پوزیشن موجود ہے؟
        Position position = positionRepository.findByUuid(dto.positionUuid())
                .orElseThrow(() -> new RuntimeException("Position not found with UUID: " + dto.positionUuid()));

        // 3. اگر دونوں مل جائیں، تو DTO کو Entity (ڈیٹا بیس والے ماڈل) میں بدلیں
        Employee employee = employeeMapper.toEntity(dto);

        // 4. سیکیورٹی (UUID) اور ریلیشن شپس (Relationships) سیٹ کریں
        employee.setUuid(UUID.randomUUID().toString());
        employee.setIsDeleted(false);
        employee.setDepartment(department);
        employee.setPosition(position);

        // 5. ڈیٹا بیس میں محفوظ کریں
        Employee savedEmployee = employeeRepository.save(employee);

        // 6. واپس DTO بنا کر یوزر کو دکھائیں
        return employeeMapper.toResponseDto(savedEmployee);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto getEmployeeByUuid(String uuid) {
        Employee employee = employeeRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Employee not found with UUID: " + uuid));
        return employeeMapper.toResponseDto(employee);
    }
}