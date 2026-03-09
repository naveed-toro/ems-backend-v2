package com.naveed.emsbackendv2.controller;

import com.naveed.emsbackendv2.model.dto.request.CreateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.request.UpdateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.response.DepartmentResponseDto;
import com.naveed.emsbackendv2.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@Valid @RequestBody CreateDepartmentDto createDepartmentDto) {
        DepartmentResponseDto response = departmentService.createDepartment(createDepartmentDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentResponseDto>> getAllDepartments(Pageable pageable) {
        return ResponseEntity.ok(departmentService.getAllDepartments(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(departmentService.getDepartmentByUuid(uuid));
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<DepartmentResponseDto> updateDepartmentByUuid(
            @PathVariable String uuid,
            @Valid @RequestBody UpdateDepartmentDto updateDepartmentDto) {
        return ResponseEntity.ok(departmentService.updateDepartmentByUuid(uuid, updateDepartmentDto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteDepartmentByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(departmentService.deleteDepartmentByUuid(uuid));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DepartmentResponseDto>> searchDepartmentsByName(
            @RequestParam String name,
            Pageable pageable) {
        return ResponseEntity.ok(departmentService.searchDepartmentsByName(name, pageable));
    }
}