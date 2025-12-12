/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.properties.TabAlignment;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;

public class AcademicReportService {

    private final PDFService pdfService;
    private final ReportHelper helper = new ReportHelper();
    private final GradeService grade = new GradeService();
    private final FileService data = new FileService();
    private List<IBlockElement> reportElements;

    public AcademicReportService() {
        this.pdfService = new PDFService();
        this.reportElements = new ArrayList<>();
    }

    public List<IBlockElement> buildReport(String studentId) {
        reportElements = new ArrayList<>();
        addHead(studentId);
        addBody(studentId);
        addFooter();
        return reportElements;
    }

    private void addHead(String studentId) {
        Table table = new Table(new float[]{1, 3}).useAllAvailableWidth();
        Cell logoCell = new Cell().setBorder(null);
        try (InputStream is = getClass().getResourceAsStream("/resources/pfp/Logo.png")) {
            if (is == null) throw new RuntimeException("Image not found");
            Image logo = new Image(ImageDataFactory.create(is.readAllBytes())).scaleToFit(100, 100);
            logoCell.add(new Paragraph().add(logo).setTextAlignment(TextAlignment.LEFT));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load logo image", e);
        }
        table.addCell(logoCell);

        Cell titleCell = new Cell().setBorder(null).setVerticalAlignment(VerticalAlignment.MIDDLE).setPaddingLeft(100);

        Map<String, String> studentInfo = new LinkedHashMap<>();
        List<String> studentRow = data.retrieveOneMatchLine("student", "student_id", studentId);
        studentInfo.put("Student Name", studentRow.size() >= 4 ? studentRow.get(2) + " " + studentRow.get(3) : "Unknown");
        studentInfo.put("Student ID", studentId);
        studentInfo.put("Program", studentRow.size() >= 5 ? studentRow.get(4) : "Unknown");

        titleCell.add(new Paragraph("Student Academic Report").setBold().setFontSize(18).setMarginBottom(5));
        studentInfo.forEach((key, value) ->
                titleCell.add(new Paragraph().add(new Text(key + ": ").setBold()).add(new Text(value)).setMargin(0))
        );

        table.addCell(titleCell);
        reportElements.add(table);
        reportElements.add(new Paragraph("\n"));
    }

    private void addBody(String studentId) {
        Set<String> semesters = grade.getAllStudentSemester(studentId);
        int count = 0;
        int totalSemesters = semesters.size();

        for (String semester : semesters) {
            count++;
            boolean isLast = (count == totalSemesters);
            helper.addSemesterTable(reportElements, studentId, semester, isLast, grade, data);
            reportElements.add(new Paragraph("\n"));
        }
    }

    private void addFooter() {
        reportElements.add(helper.buildGradingScale());
    }

    public void previewReport(JPanel targetPanel) {
        if (reportElements == null || reportElements.isEmpty()) return;
        byte[] pdfBytes = pdfService.createPDFPreview(reportElements);
        if (pdfBytes == null || pdfBytes.length == 0) return;

        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {
            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage image = renderer.renderImageWithDPI(0, 150);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JScrollPane scrollPane = new JScrollPane(imageLabel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            targetPanel.removeAll();
            targetPanel.setLayout(new BorderLayout());
            targetPanel.add(scrollPane, BorderLayout.CENTER);
            targetPanel.revalidate();
            targetPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportReport(String studentId) {
        List<IBlockElement> rebuildReport = buildReport(studentId);

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Report As...");

        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String filename = file.getAbsolutePath();
            if (!filename.toLowerCase().endsWith(".pdf")) filename += ".pdf";

            pdfService.createPDFFile(filename, rebuildReport);

            try {
                Desktop.getDesktop().open(new File(filename));
            } catch (Exception ignored) {}
        }
    }

    public static void main(String[] args) {
        AcademicReportService r = new AcademicReportService();
        r.exportReport("S001");
    }
}

class ReportHelper {

    Table buildTable(List<String> header, List<String> content) {

        Table table = new Table(header.size())
                .setWidth(UnitValue.createPercentValue(100))
                .setTextAlignment(TextAlignment.CENTER);

        for (String headerValue : header) {
            table.addHeaderCell(
                new Cell()
                    .add(new Paragraph(headerValue).setBold())
                    .setPadding(5)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
            );
        }

        for (String row : content) {
            String[] values = row.split("\\|");
            for (String value : values) {
                table.addCell(
                    new Cell()
                        .add(new Paragraph(value))
                        .setPadding(5)
                        .setTextAlignment(TextAlignment.LEFT)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                );
            }
        }

        return table;
    }

    Table buildGradingScale() {
        Table table = new Table(1)
                .useAllAvailableWidth()
                .setMarginTop(10)
                .setMarginBottom(10);

        Paragraph title = new Paragraph("Grading Scale")
                .setBold()
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(5);

        String[][] lines = new String[][]{
            {"A*", "80 - 100", "Distinction", "4.0"},
            {"A", "75 - 79", "Distinction", "3.7"},
            {"B+", "70 - 74", "Merit", "3.3"},
            {"B", "65 - 69", "Merit", "3.0"},
            {"C+", "60 - 64", "Pass", "2.7"},
            {"C", "55 - 59", "Pass", "2.5"},
            {"C-", "50 - 54", "Pass", "2.0"},
            {"D", "40 - 49", "Fail", "0.0"}
        };

        Cell cell = new Cell().setPadding(10).setVerticalAlignment(VerticalAlignment.MIDDLE);

        cell.add(title);

        float fullWidth = 520f; 
        Paragraph p;
        for (String[] parts : lines) {
            p = new Paragraph()
                    .setFontSize(11)
                    .setMultipliedLeading(1.2f)
                    .addTabStops(
                            new TabStop(0, TabAlignment.LEFT),
                            new TabStop(fullWidth / 3f, TabAlignment.CENTER),
                            new TabStop(fullWidth * 2 / 3f, TabAlignment.CENTER),
                            new TabStop(fullWidth, TabAlignment.RIGHT)
                    );
            p.add(parts[0]).add(new Tab())
             .add(parts[1]).add(new Tab())
             .add(parts[2]).add(new Tab())
             .add(parts[3]);
            cell.add(p);
        }

        table.addCell(cell);
        return table;
    }

    void addSemesterTable(List<IBlockElement> reportElements, String studentId, String semester, boolean isLastSemester, GradeService grade, FileService data) {

        reportElements.add(new Paragraph("Semester: " + semester).setBold().setFontSize(14));

        List<String> header = Arrays.asList("Course ID", "Course Name", "Credit", "Exam Score", "Assignment Score", "Final Score", "Grade Point");
        List<String> content = new ArrayList<>();

        List<List<String>> grades = grade.getGradesBySemester(studentId, semester);

        double semesterPoints = 0;
        int semesterCredits = 0;

        for (List<String> gradeCols : grades) {
            String courseId = gradeCols.get(data.getHeaderIndex("studentgrade", "course_id"));
            String examScore = gradeCols.get(data.getHeaderIndex("studentgrade", "exam_score"));
            String assignmentScore = gradeCols.get(data.getHeaderIndex("studentgrade", "assignment_score"));
            String finalScore = gradeCols.get(data.getHeaderIndex("studentgrade", "final_score"));
            int credit = grade.getCourseCredit(courseId);
            double gradePoint = grade.convertToGradePoint(Integer.parseInt(finalScore));

            semesterPoints += gradePoint * credit;
            semesterCredits += credit;

            List<String> courseRow = data.retrieveOneMatchLine("course", "course_id", courseId);
            String courseName = courseRow.isEmpty() ? "Unknown" : courseRow.get(data.getHeaderIndex("course", "course_name"));

            content.add(String.join("|", Arrays.asList(
                    courseId, courseName, String.valueOf(credit),
                    examScore, assignmentScore, finalScore,
                    String.valueOf(gradePoint)
            )));
        }

        double semesterGPA = semesterCredits > 0 ? semesterPoints / semesterCredits : 0;

        Table table = buildTable(header, content);

        for (int i = 0; i < 4; i++) table.addCell(new Cell().setBorder(null));
        table.addCell(new Cell(1, 2)
                .add(new Paragraph("Semester GPA").setBold())
                .setPadding(5)
                .setTextAlignment(TextAlignment.LEFT)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
        );
        table.addCell(new Cell()
                .add(new Paragraph(String.format("%.2f", semesterGPA)))
                .setPadding(5)
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
        );

        if (isLastSemester) {
            double cgpa = grade.getCGPA(studentId);

            for (int i = 0; i < 4; i++) table.addCell(new Cell().setBorder(null));

            table.addCell(new Cell(1, 2)
                    .add(new Paragraph("Cumulative GPA (CGPA)").setBold())
                    .setPadding(5)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
            );

            table.addCell(new Cell()
                    .add(new Paragraph(String.format("%.2f", cgpa)))
                    .setPadding(5)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
            );
        }

        reportElements.add(table);
    }
}
