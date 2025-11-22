package certification;

import java.io.File;
import java.awt.Color;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.util.Matrix;

public class create_pdf_certification {

    public static String createPDF(Certificate cert) {
        try (PDDocument doc = new PDDocument()) {

            PDRectangle landscape = new PDRectangle(900, 380);
            PDPage page = new PDPage(landscape);
            doc.addPage(page);

            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();

            try (PDPageContentStream content = new PDPageContentStream(doc, page)) {

                String title = "Certificate of Completion";
                float titleFontSize = 28;
                float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(title) / 1000 * titleFontSize;

                content.beginText();
                content.setFont(PDType1Font.HELVETICA_BOLD, titleFontSize);
                content.newLineAtOffset((pageWidth - titleWidth) / 2, pageHeight - 110);
                content.showText(title);
                content.endText();

                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 16);
                content.newLineAtOffset(pageWidth / 4, pageHeight - 200);

                content.showText("Certificate ID: " + cert.getCertificateID());
                content.newLineAtOffset(0, -25);

                content.showText("Student ID: " + cert.getStudentID());
                content.newLineAtOffset(0, -25);

                content.showText("Course ID: " + cert.getCourseID());
                content.newLineAtOffset(0, -25);

                content.showText("Issued Date: " + cert.getIssueDate());
                content.endText();
            }


            try (PDPageContentStream wm = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true)) {

                PDExtendedGraphicsState gs = new PDExtendedGraphicsState();
                gs.setNonStrokingAlphaConstant(0.19f);
                gs.setStrokingAlphaConstant(0.19f);
                wm.setGraphicsStateParameters(gs);

                wm.beginText();
                wm.setFont(PDType1Font.HELVETICA_BOLD, 90);
                wm.setNonStrokingColor(Color.LIGHT_GRAY);

                wm.setTextMatrix(Matrix.getRotateInstance(Math.toRadians(45), pageWidth / 3, pageHeight / 14));
                wm.showText("SkillForge");
                wm.endText();
            }


            try (PDPageContentStream sig = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true)) {

                PDImageXObject signature = PDImageXObject.createFromFile("signet.png", doc);

                float sigWidth = 150;
                float sigHeight = 90;
                float x = pageWidth - sigWidth - 50;
                float y = 25;

                sig.drawImage(signature, x, y, sigWidth, sigHeight);
            }


            try (PDPageContentStream border = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true)) {

                border.setStrokingColor(212 / 255f, 175 / 255f, 55 / 255f);

                border.setLineWidth(4f);
                float margin = 10;
                border.addRect(margin, margin, pageWidth - 2 * margin, pageHeight - 2 * margin);
                border.stroke();

                border.setLineWidth(2f);
                float innerMargin = margin + 6;
                border.addRect(innerMargin, innerMargin, pageWidth - 2 * innerMargin, pageHeight - 2 * innerMargin);
                border.stroke();
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
