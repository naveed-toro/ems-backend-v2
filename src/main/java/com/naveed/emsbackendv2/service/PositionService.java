package com.naveed.emsbackendv2.service;

import com.naveed.emsbackendv2.model.dto.request.CreatePositionDto;
import com.naveed.emsbackendv2.model.dto.response.PositionResponseDto;
import java.util.List;

public interface PositionService {

    PositionResponseDto createPosition(CreatePositionDto createPositionDto);

    List<PositionResponseDto> getAllPositions();

    PositionResponseDto getPositionByUuid(String uuid);
}