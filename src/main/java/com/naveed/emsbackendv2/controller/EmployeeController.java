package com.naveed.emsbackendv2.controller;

import com.naveed.emsbackendv2.model.dto.request.CreateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.request.UpdateEmployeeDto;
import com.naveed.emsbackendv2.model.dto.response.EmployeeResponseDto;
import com.naveed.emsbackendv2.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // 1. Create (نیا ملازم بنانا)
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody CreateEmployeeDto createEmployeeDto) {
        EmployeeResponseDto response = employeeService.createEmployee(createEmployeeDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. Read All (Pagination کے ساتھ تمام ملازمین دیکھنا)
    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDto>> getAllEmployees(Pageable pageable) {
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    // 3. Read One (کسی ایک خاص ملازم کو دیکھنا)
    @GetMapping("/{uuid}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(employeeService.getEmployeeByUuid(uuid));
    }

    // 4. Update (ملازم کا ڈیٹا تبدیل کرنا)
    @PatchMapping("/{uuid}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeByUuid(
            @PathVariable String uuid,
            @Valid @RequestBody UpdateEmployeeDto updateEmployeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployeeByUuid(uuid, updateEmployeeDto));
    }

    // 5. Delete (ملازم کو ڈیلیٹ کرنا)
    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteEmployeeByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(employeeService.deleteEmployeeByUuid(uuid));
    }

    // 6. ملازم کو نام سے تلاش کرنا (Search by Name)
    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeResponseDto>> searchEmployeesByName(
            @RequestParam String name,
            Pageable pageable) {
        return ResponseEntity.ok(employeeService.searchEmployeesByName(name, pageable));
    }
}