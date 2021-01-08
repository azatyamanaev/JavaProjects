package ru.itis.documentsflow.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import ru.itis.documentsflow.models.UsersData;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class EnrollmentTemplateServiceImpl implements TemplateService{
    @Override
    public String createTemplate(UsersData usersData) {
        String dest = "/home/monitor/Desktop/storage/documents-flow-st/pdf";
        String name = usersData.getName();
        String lastName = usersData.getLastName();

        File dir = new File(dest + File.separator + name + " " + lastName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        dest = dir.getAbsolutePath() + File.separator + "EnrollmentPdf" + new Random().nextInt(1000000000);
        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset(250, 600);
            String text = "Inquiry";
            contentStream.showText(text);
            contentStream.endText();
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.newLineAtOffset(50, 550);
            text = "Given to " + name + " " + lastName + " that he is currently working in xxx organization.";
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();
            doc.save(dest);
            doc.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return dest;
    }
}
