package com.naveed.emsbackendv2.controller;

import com.naveed.emsbackendv2.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // ============== پہلا API: CSV (Excel) ڈاؤن لوڈ کے لیے ==============
    @GetMapping("/employees/csv")
    public ResponseEntity<byte[]> downloadEmployeeCsv() {
        byte[] csvData = reportService.generateEmployeeCsvReport();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees_report.csv");
        headers.setContentType(MediaType.parseMediaType("text/csv"));

        return ResponseEntity.ok()
                .headers(headers)
                .body(csvData);
    }

    // ============== دوسرا API: PDF ڈاؤن لوڈ کے لیے ==============
    @GetMapping("/employees/pdf")
    public ResponseEntity<byte[]> downloadEmployeePdf() {
        // سروس سے PDF فائل کا ڈیٹا منگوائیں
        byte[] pdfData = reportService.generateEmployeePdfReport();

        // براؤزر کو بتائیں کہ یہ ایک PDF فائل ہے
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees_report.pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        // فائل یوزر کو بھیج دیں
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfData);
    }
}