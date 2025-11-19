/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courserecoverysystem.service;

/**
 *
 * @author seany
 */
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;

import java.io.FileOutputStream;
import java.util.List;


// TOUCH AND I WILL END YOU, YOUR FAMILY, YOUR DOG, AND EVERYTHING YOU LOVE IN LIFE

public class PDFService {
        public void createPDFFile(String filename, List<IBlockElement> reportElements) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            PdfWriter writer = new PdfWriter(fos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            for (IBlockElement element : reportElements) {
                document.add(element);
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] createPDFPreview(List<IBlockElement> reportElements) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            for (IBlockElement element : reportElements) {
                document.add(element);
            }

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
