package com.naveed.emsbackendv2.controller;

import com.naveed.emsbackendv2.model.dto.request.CreatePositionDto;
import com.naveed.emsbackendv2.model.dto.response.PositionResponseDto;
import com.naveed.emsbackendv2.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions") // اس کا راستہ positions ہوگا
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public ResponseEntity<PositionResponseDto> createPosition(@Valid @RequestBody CreatePositionDto createPositionDto) {
        PositionResponseDto response = positionService.createPosition(createPositionDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PositionResponseDto>> getAllPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }
}