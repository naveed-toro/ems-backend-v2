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

    // ============== پہلا فنکشن: CSV فائل بنانے کے لیے ==============
    @Override
    @Transactional(readOnly = true)
    public byte[] generateEmployeeCsvReport() {
        List<Employee> employees = employeeRepository.findAll();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(out);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Email", "Department", "Position"))) {

            for (Employee emp : employees) {
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
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate CSV report: " + e.getMessage());
        }
    }

    // ============== دوسرا اور نیا فنکشن: PDF فائل بنانے کے لیے ==============
    @Override
    @Transactional(readOnly = true)
    public byte[] generateEmployeePdfReport() {
        List<Employee> employees = employeeRepository.findAll();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // 1. ایک نیا PDF ڈاکومنٹ بنائیں
            com.lowagie.text.Document document = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4);
            com.lowagie.text.pdf.PdfWriter.getInstance(document, out);
            document.open();

            // 2. رپورٹ کا ٹائٹل (Heading)
            com.lowagie.text.Font titleFont = com.lowagie.text.FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA_BOLD, 18);
            com.lowagie.text.Paragraph title = new com.lowagie.text.Paragraph("Employee Report", titleFont);
            title.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            document.add(title);
            document.add(new com.lowagie.text.Paragraph(" ")); // ایک خالی لائن

            // 3. ٹیبل بنانا (4 کالم: Name, Email, Department, Position)
            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
            table.setWidthPercentage(100);

            // ٹیبل کے ہیڈرز
            table.addCell("Name");
            table.addCell("Email");
            table.addCell("Department");
            table.addCell("Position");

            // 4. ڈیٹا بیس سے ملازمین کا ڈیٹا ٹیبل میں ڈالنا
            for (Employee emp : employees) {
                table.addCell(emp.getName());
                table.addCell(emp.getEmail());
                table.addCell(emp.getDepartment() != null ? emp.getDepartment().getName() : "N/A");
                table.addCell(emp.getPosition() != null ? emp.getPosition().getName() : "N/A");
            }

            // 5. ٹیبل کو PDF میں شامل کر کے فائل بند کر دیں
            document.add(table);
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report: " + e.getMessage());
        }
    }
}