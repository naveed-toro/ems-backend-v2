package com.naveed.emsbackendv2.model.mapper;

import com.naveed.emsbackendv2.model.dto.request.CreateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.response.EmployeeResponseDto;
import com.naveed.emsbackendv2.model.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// یہ لائن اسپرنگ بوٹ کو بتاتی ہے کہ اس میپر کو خود سنبھالے (Dependency Injection کے لیے)
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    // 1. Entity کو DTO میں بدلنے کے لیے (ڈیٹا بیس سے نکال کر یوزر کو دکھانے کے لیے)
    EmployeeResponseDto toResponseDto(Employee employee);

    // 2. DTO کو Entity میں بدلنے کے لیے (یوزر سے لے کر ڈیٹا بیس میں سیو کرنے کے لیے)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "department", ignore = true) // یہ ہم سروس لیئر میں خود سیٹ کریں گے
    @Mapping(target = "position", ignore = true)   // یہ بھی سروس لیئر میں خود سیٹ کریں گے
    Employee toEntity(CreateEmployeeDto createEmployeeDto);
}