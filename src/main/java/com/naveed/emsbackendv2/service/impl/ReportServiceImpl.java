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

    // ============== پہلا فنکشن: CSV فائل (فلٹرز کے ساتھ) ==============
    @Override
    @Transactional(readOnly = true)
    public byte[] generateEmployeeCsvReport(String departmentUuid, String positionUuid, String status) {

        // جادو یہاں ہے: اب ہم سارا ڈیٹا لانے کے بجائے فلٹر کے حساب سے ڈیٹا لا رہے ہیں
        List<Employee> employees = employeeRepository.findByFilters(departmentUuid, positionUuid, status);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(out);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.builder()
                     .setHeader("ID", "Name", "Email", "Department", "Position")
                     .build())) {

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

    // ============== دوسرا فنکشن: PDF فائل (فلٹرز کے ساتھ) ==============
    @Override
    @Transactional(readOnly = true)
    public byte[] generateEmployeePdfReport(String departmentUuid, String positionUuid, String status) {

        // جادو یہاں ہے: اب ہم سارا ڈیٹا لانے کے بجائے فلٹر کے حساب سے ڈیٹا لا رہے ہیں
        List<Employee> employees = employeeRepository.findByFilters(departmentUuid, positionUuid, status);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            com.lowagie.text.Document document = new com.lowagie.text.Document(com.lowagie.text.PageSize.A4);
            com.lowagie.text.pdf.PdfWriter.getInstance(document, out);
            document.open();

            com.lowagie.text.Font titleFont = com.lowagie.text.FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA_BOLD, 18);
            com.lowagie.text.Paragraph title = new com.lowagie.text.Paragraph("Employee Report", titleFont);
            title.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            document.add(title);
            document.add(new com.lowagie.text.Paragraph(" "));

            com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(4);
            table.setWidthPercentage(100);

            table.addCell("Name");
            table.addCell("Email");
            table.addCell("Department");
            table.addCell("Position");

            for (Employee emp : employees) {
                table.addCell(emp.getName());
                table.addCell(emp.getEmail());
                table.addCell(emp.getDepartment() != null ? emp.getDepartment().getName() : "N/A");
                table.addCell(emp.getPosition() != null ? emp.getPosition().getName() : "N/A");
            }

            document.add(table);
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report: " + e.getMessage());
        }
    }
}