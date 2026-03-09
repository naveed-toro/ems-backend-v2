package com.naveed.emsbackendv2.service;

import com.naveed.emsbackendv2.model.dto.CreatePositionDto;
import com.naveed.emsbackendv2.model.dto.PositionResponseDto;
import java.util.List;

public interface PositionService {

    PositionResponseDto createPosition(CreatePositionDto createPositionDto);

    List<PositionResponseDto> getAllPositions();

    PositionResponseDto getPositionByUuid(String uuid);
}