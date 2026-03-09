package com.naveed.emsbackendv2.controller;

import com.naveed.emsbackendv2.model.dto.request.CreateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.response.EmployeeResponseDto;
import com.naveed.emsbackendv2.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees") // اس کا راستہ employees ہوگا
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody CreateEmployeeDto createEmployeeDto) {
        EmployeeResponseDto response = employeeService.createEmployee(createEmployeeDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}