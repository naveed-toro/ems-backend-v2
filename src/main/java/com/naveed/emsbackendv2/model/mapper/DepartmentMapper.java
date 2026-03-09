package com.naveed.emsbackendv2.model.mapper;

import com.naveed.emsbackendv2.model.dto.CreateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.DepartmentResponseDto;
import com.naveed.emsbackendv2.model.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponseDto toResponseDto(Department department);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "employees", ignore = true) // ایمپلائیز کی لسٹ ہم الگ سے ہینڈل کریں گے
    Department toEntity(CreateDepartmentDto createDepartmentDto);
}