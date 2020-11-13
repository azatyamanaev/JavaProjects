package ru.itis.documentsflow.services;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import ru.itis.documentsflow.models.UsersData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class InquiryTemplateServiceImpl implements TemplateService {
    public String createTemplate(UsersData usersData) {
        String dest = "/home/monitor/Desktop/storage/documents-flow-st/pdf";
        String name = usersData.getName();
        String lastName = usersData.getLastName();

        File dir = new File(dest + File.separator + name + " " + lastName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        Random random = new Random();
        dest = dir.getAbsolutePath() + File.separator + "InquiryPdf" + random.nextInt(1000000000);
        try {
            java.util.List<String> text = new ArrayList<String>();
            text.add("");
            text.add("");
            text.add("");
            text.add("");
            text.add("");
            text.add("");
            text.add("");
            text.add("");
            text.add("                                         Inquiry");
            text.add("");
            text.add("");
            text.add("    Given to " + name + " " + lastName + " that he is currently working in xxx organization.");
            //text.add("I attach my passport data: " + "series - " + series + ", number - " + number + ", date - " + date);
            PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
            PageSize ps = PageSize.A4.rotate();
            PdfPage page = pdf.addNewPage(ps);
            PdfCanvas canvas = new PdfCanvas(page);
            canvas.concatMatrix(1, 0, 0, 1, 0, ps.getHeight());
            canvas.beginText()
                    .setFontAndSize(PdfFontFactory.createFont(FontConstants.COURIER_BOLD), 14)
                    .setLeading(14 * 1.2f)
                    .moveText(70, -40);
            for (String s : text) {
                canvas.newlineShowText(s);
            }
            canvas.endText();
            pdf.close();
            return dest;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
}
