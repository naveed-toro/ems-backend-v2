package com.naveed.emsbackendv2.controller;

import com.naveed.emsbackendv2.model.dto.request.CreateDepartmentDto;
import com.naveed.emsbackendv2.model.dto.response.DepartmentResponseDto;
import com.naveed.emsbackendv2.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// یہ اینوٹیشن بتاتی ہے کہ یہ کلاس REST APIs کو ہینڈل کرے گی
@RestController
// یہ ہمارے API کا بنیادی راستہ (URL) ہے
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // 1. نیا ڈیپارٹمنٹ بنانے کا API (POST Request)
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> createDepartment(@Valid @RequestBody CreateDepartmentDto createDepartmentDto) {
        DepartmentResponseDto response = departmentService.createDepartment(createDepartmentDto);
        // جب نیا ڈیٹا بنتا ہے تو ہم 201 (CREATED) کا اسٹیٹس بھیجتے ہیں
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. تمام ڈیپارٹمنٹس دیکھنے کا API (GET Request)
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        List<DepartmentResponseDto> response = departmentService.getAllDepartments();
        // جب ڈیٹا کامیابی سے مل جائے تو ہم 200 (OK) کا اسٹیٹس بھیجتے ہیں
        return ResponseEntity.ok(response);
    }
}