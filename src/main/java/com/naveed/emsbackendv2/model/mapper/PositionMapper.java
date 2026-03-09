package com.naveed.emsbackendv2.model.mapper;

import com.naveed.emsbackendv2.model.dto.CreatePositionDto;
import com.naveed.emsbackendv2.model.dto.PositionResponseDto;
import com.naveed.emsbackendv2.model.entities.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionResponseDto toResponseDto(Position position);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "employees", ignore = true)
    Position toEntity(CreatePositionDto createPositionDto);
}