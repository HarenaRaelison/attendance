package com.example.attendancepersonnel.Service.Implement;

import com.example.attendancepersonnel.DTO.DailyReportDTO;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReportService {

    public byte[] generateDailyReportPdf(List<DailyReportDTO> dailyReport) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Create a table with 3 columns
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 5, 2}));
        table.setWidth(UnitValue.createPercentValue(100));

        // Define header cell style
        Color headerBgColor = new DeviceRgb(63, 81, 181);
        Color headerTextColor = new DeviceRgb(255, 255, 255);

        // Add table headers
        addStyledHeaderCell(table, "Date", headerBgColor, headerTextColor);
        addStyledHeaderCell(table, "Employee Name", headerBgColor, headerTextColor);
        addStyledHeaderCell(table, "Status", headerBgColor, headerTextColor);

        // Add table rows
        for (DailyReportDTO report : dailyReport) {
            addStyledCell(table, report.getDate().toString());
            addStyledCell(table, report.getNom());
            addStyledCell(table, report.isStatutPresence() ? "Présent" : "Absent");
        }

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }

    private void addStyledHeaderCell(Table table, String content, Color bgColor, Color textColor) {
        Cell cell = new Cell()
                .add(new Paragraph(content))
                .setBackgroundColor(bgColor)
                .setFontColor(textColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setBold();
        table.addHeaderCell(cell);
    }

    private void addStyledCell(Table table, String content) {
        Cell cell = new Cell()
                .add(new Paragraph(content))
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.addCell(cell);
    }
}
