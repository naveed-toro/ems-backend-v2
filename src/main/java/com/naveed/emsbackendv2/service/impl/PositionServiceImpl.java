package com.naveed.emsbackendv2.service.impl;

import com.naveed.emsbackendv2.model.dto.request.CreatePositionDto;
import com.naveed.emsbackendv2.model.dto.response.PositionResponseDto;
import com.naveed.emsbackendv2.model.entities.Position;
import com.naveed.emsbackendv2.model.mapper.PositionMapper;
import com.naveed.emsbackendv2.model.repository.PositionRepository;
import com.naveed.emsbackendv2.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    @Override
    public PositionResponseDto createPosition(CreatePositionDto createPositionDto) {
        Position position = positionMapper.toEntity(createPositionDto);

        position.setUuid(UUID.randomUUID().toString());
        position.setIsDeleted(false);

        Position savedPosition = positionRepository.save(position);
        return positionMapper.toResponseDto(savedPosition);
    }

    @Override
    public List<PositionResponseDto> getAllPositions() {
        return positionRepository.findAll()
                .stream()
                .map(positionMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PositionResponseDto getPositionByUuid(String uuid) {
        Position position = positionRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Position not found with UUID: " + uuid));

        return positionMapper.toResponseDto(position);
    }
}