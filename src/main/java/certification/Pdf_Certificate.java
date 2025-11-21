/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package certification;

import java.io.File;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;


public class Pdf_Certificate {
     public static String createPDF(Certificate cert) {
                try (PDDocument doc = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            try (PDPageContentStream content = new PDPageContentStream(doc, page)) {

                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, 28);
                content.newLineAtOffset(130, 700);
                content.showText("Certificate of Completion");
                content.endText();

                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 16);
                content.newLineAtOffset(100, 620);
                content.showText("Certificate ID: " + cert.getCertificateID());
                content.newLineAtOffset(0, -25);
                content.showText("Student ID: " + cert.getStudentID());
                content.newLineAtOffset(0, -25);
                content.showText("Course ID: " + cert.getCourseID());
                content.newLineAtOffset(0, -25);
                content.showText("Issued Date: " + cert.getIssueDate());
                content.endText();
            }

            File folder = new File("certificates");
            if (!folder.exists()) folder.mkdir();

            String path = "certificates/" + cert.getCertificateID() + ".pdf";
            doc.save(path);

            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
