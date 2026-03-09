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

    // ملازمین کا ڈیٹا CSV (Excel) میں ڈاؤن لوڈ کرنے کا API
    @GetMapping("/employees/csv")
    public ResponseEntity<byte[]> downloadEmployeeCsv() {
        // سروس سے فائل کا ڈیٹا (bytes) منگوائیں
        byte[] csvData = reportService.generateEmployeeCsvReport();

        // براؤزر کو بتائیں کہ یہ کوئی عام ٹیکسٹ نہیں بلکہ ایک فائل ہے جسے ڈاؤن لوڈ کرنا ہے
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees_report.csv");
        headers.setContentType(MediaType.parseMediaType("text/csv"));

        // فائل یوزر کو بھیج دیں
        return ResponseEntity.ok()
                .headers(headers)
                .body(csvData);
    }
}