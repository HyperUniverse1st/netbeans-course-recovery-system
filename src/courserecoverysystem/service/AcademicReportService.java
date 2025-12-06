/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;
import java.io.InputStream;
import java.io.IOException;
/*
Okay the only thing you need to know is to call it is just like this

    This is to create it
            List<String> header = new ArrayList<>();
            header.add("Header1");
            header.add("Header2");
            header.add("Header3");

            Better if this was already a ListArray
            String[] table = {"Value1|Value2|Value3","Value1|Value2|Value3"};

            This is to just convert to a ListArray
            List<String> list = new ArrayList<>(Arrays.asList(table));

            This just calls it
            academicReportService = new AcademicReportService();

            This will build the report
            academicReportService.buildReport(header, list);



        This will allow you put it in a panel
        academicReportService.previewReport(ExamplePanel);

        This will pull out a dialog where you can select where to save
        academicReportService.exportReport(header, list);
*/


public class AcademicReportService {

    private final PDFService pdfService;
    private List<IBlockElement> reportElements;
     private final ReportHelper helper = new ReportHelper();

    public AcademicReportService() {
        this.pdfService = new PDFService();
        this.reportElements = new ArrayList<>();
    }

    public List<IBlockElement> buildReport(List<String> header, List<String> content) {
        reportElements = new ArrayList<>();
       
        addHead("Student Academic Report");
        reportElements.add(new Paragraph("\n"));
        addBody(header, content);
        reportElements.add(new Paragraph("\n"));
        addFooter();
        return reportElements;
    }


    private void addHead(String titleText) {

        Table table = new Table(new float[]{1, 3}).useAllAvailableWidth();
        Cell logoCell = new Cell().setBorder(null);

        try (InputStream is = getClass().getResourceAsStream("/resources/pfp/Logo.png")) { //TODO j please make gradescale version
            if (is == null) {
                throw new RuntimeException("Image not found");
            }

            Image logo = new Image(ImageDataFactory.create(is.readAllBytes()))
                    .scaleToFit(100, 100);

            logoCell.add(new Paragraph().add(logo).setTextAlignment(TextAlignment.LEFT));

        } catch (IOException e) {
            throw new RuntimeException("Failed to load logo image", e);
        }

        table.addCell(logoCell);

        Cell titleCell = new Cell()
                .setBorder(null)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setPaddingLeft(100);  

        titleCell.add(
            new Paragraph(titleText)
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(5)
        );

        Map<String, String> studentInfo = new LinkedHashMap<>();
        studentInfo.put("Student Name", "Something"); //TODO put the getters here
        studentInfo.put("Student ID", "Something");
        studentInfo.put("Program", "Something");

        studentInfo.forEach((key, value) ->
            titleCell.add(
                new Paragraph()
                    .add(new Text(key + ": ").setBold())
                    .add(new Text(value))
                    .setMargin(0)
                    .setTextAlignment(TextAlignment.LEFT) 
            )
        );

        table.addCell(titleCell);
        reportElements.add(table);
    }



    private void addBody(List<String> header, List<String> content) {
        helper.addStudentInfo(reportElements, helper.buildStudentInfo());
        reportElements.add(helper.buildTable(header, content));
    }


    
    private void addFooter() {
        reportElements.add(new Paragraph("Grading Scale"));
        reportElements.add(helper.buildGradingScale());
    }
                     
                      
    
    public void previewReport(JPanel targetPanel) {
        if (reportElements == null || reportElements.isEmpty()) return;

        byte[] pdfBytes = pdfService.createPDFPreview(reportElements);
        if (pdfBytes == null || pdfBytes.length == 0) return;

        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {
            PDFRenderer renderer = new PDFRenderer(document);
            BufferedImage image = renderer.renderImageWithDPI(0, 150);

            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel imagePanel = new JPanel(new BorderLayout());
            imagePanel.add(imageLabel, BorderLayout.CENTER);
            imagePanel.setBackground(Color.DARK_GRAY);

            JScrollPane scrollPane = new JScrollPane(imagePanel);
            scrollPane.setBorder(null);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            Runnable updateImage = () -> {
                int panelWidth = targetPanel.getWidth();
                if (panelWidth <= 0) return;

                double aspectRatio = (double) image.getHeight() / image.getWidth();
                int scaledWidth = panelWidth;
                int scaledHeight = (int) (scaledWidth * aspectRatio);

                java.awt.Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, java.awt.Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));

                imagePanel.setPreferredSize(new Dimension(scaledWidth, scaledHeight));
                imagePanel.revalidate();
            };

            targetPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
                @Override
                public void componentResized(java.awt.event.ComponentEvent e) {
                    updateImage.run();
                }
            });

            SwingUtilities.invokeLater(updateImage);

            targetPanel.removeAll();
            targetPanel.setLayout(new BorderLayout());
            targetPanel.add(scrollPane, BorderLayout.CENTER);
            targetPanel.revalidate();
            targetPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    public void exportReport(List<String> header, List<String> content) {
        if (reportElements == null || reportElements.isEmpty()) return;

        List<IBlockElement> rebuildReport = buildReport(header, content);
        
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Report As...");
        chooser.setSelectedFile(new File("StudentReport.pdf"));

        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String filename = file.getAbsolutePath();
            if (!filename.toLowerCase().endsWith(".pdf")) filename += ".pdf";

            pdfService.createPDFFile(filename, rebuildReport);

            try {
                Desktop.getDesktop().open(new File(filename));
            } catch (Exception ignored) {
            }
        }
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
        List<String> header = Arrays.asList("Grade", "Scale", "Division", "GPA"); //TODO the header of grade scaling

        List<String> content = Arrays.asList( //TODO might need to change
            "A*|80 - 100|Distinction|4.0",
            "A|75 - 79|Distinction|3.7",
            "B+|70 - 74|Merit|3.3",
            "B|65 - 69|Merit|3.0",
            "C+|60 - 64|Pass|2.7",
            "C|55 - 59|Pass|2.5",
            "C-|50 - 54|Pass|2.0",
            "D|40 - 49|Fail (Marginal)|0.0"
        );

        return buildTable(header, content);
    }

    Map<String, String> buildStudentInfo() {
        Map<String, String> studentInfo = new LinkedHashMap<>();
        studentInfo.put("Semester", "Something"); //TODO use the student getters otherwise it will display something
        studentInfo.put("Cumulative GPA (CGPA)", "Something");
        return studentInfo;
    }

    void addStudentInfo(List<IBlockElement> reportElements, Map<String, String> studentInfo) {
        for (Map.Entry<String, String> entry : studentInfo.entrySet()) {
            Text key = new Text(entry.getKey() + ": ").setBold();
            Text value = new Text(entry.getValue());

            Paragraph student = new Paragraph()
                    .add(key)
                    .add(value)
                    .setMarginTop(0)
                    .setMarginBottom(0);

            reportElements.add(student);
        }

        reportElements.add(new Paragraph().setMarginBottom(4));
    }
}




