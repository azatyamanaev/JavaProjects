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
        File file = new File("/home/monitor/Documents/university/ex_pdf.pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDPage page = doc.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.beginText();
            contentStream.newLineAtOffset(20, 500);
            System.out.println("s");
            String text = "hello";
            contentStream.showText(text);
            System.out.println("s");
            contentStream.endText();
            System.out.println("s");
            contentStream.close();
            doc.save(dest);
            doc.close();
            System.out.println("ssss");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return dest;
    }
}
