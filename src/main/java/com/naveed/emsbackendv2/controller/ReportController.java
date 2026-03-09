package com.naveed.emsbackendv2.controller;

import com.naveed.emsbackendv2.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // ============== پہلا API: CSV (Excel) ڈاؤن لوڈ کے لیے ==============
    @GetMapping("/employees/csv")
    public ResponseEntity<byte[]> downloadEmployeeCsv(
            @RequestParam(required = false) String departmentUuid,
            @RequestParam(required = false) String positionUuid,
            @RequestParam(required = false) String status) {

        // کلائنٹ سے آنے والے فلٹرز سروس کو بھیجیں
        byte[] csvData = reportService.generateEmployeeCsvReport(departmentUuid, positionUuid, status);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees_report.csv");
        headers.setContentType(MediaType.parseMediaType("text/csv"));

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvData);
    }

    // ============== دوسرا API: PDF ڈاؤن لوڈ کے لیے ==============
    @GetMapping("/employees/pdf")
    public ResponseEntity<byte[]> downloadEmployeePdf(
            @RequestParam(required = false) String departmentUuid,
            @RequestParam(required = false) String positionUuid,
            @RequestParam(required = false) String status) {

        // کلائنٹ سے آنے والے فلٹرز سروس کو بھیجیں
        byte[] pdfData = reportService.generateEmployeePdfReport(departmentUuid, positionUuid, status);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees_report.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfData);
    }
}