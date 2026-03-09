package com.naveed.emsbackendv2.service.impl;

import com.naveed.emsbackendv2.model.entities.Employee;
import com.naveed.emsbackendv2.model.repository.EmployeeRepository;
import com.naveed.emsbackendv2.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public byte[] generateEmployeeCsvReport() {
        // 1. ڈیٹا بیس سے تمام ملازمین کا ریکارڈ منگوانا
        List<Employee> employees = employeeRepository.findAll();

        // 2. CSV فائل تیار کرنا
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(out);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Email", "Department", "Position"))) {

            // 3. ہر ملازم کا ڈیٹا فائل کی لائنوں میں لکھنا
            for (Employee emp : employees) {
                // اگر ڈیپارٹمنٹ یا پوزیشن ڈیلیٹ ہو چکی ہو تو کریش سے بچنے کے لیے چیک
                String deptName = (emp.getDepartment() != null) ? emp.getDepartment().getName() : "N/A";
                String posName = (emp.getPosition() != null) ? emp.getPosition().getName() : "N/A";

                csvPrinter.printRecord(
                        emp.getUuid(),
                        emp.getName(),
                        emp.getEmail(),
                        deptName,
                        posName
                );
            }

            csvPrinter.flush();
            return out.toByteArray(); // فائل کو بائٹس (Bytes) میں بھیج دینا تاکہ یوزر ڈاؤن لوڈ کر سکے

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate CSV report: " + e.getMessage());
        }
    }
}