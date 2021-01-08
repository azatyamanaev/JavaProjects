package ru.itis.documentsflow.services;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailServiceImpl implements EmailService{

    static {

    }

    @Override
    public void sendInquiry(String filename, Properties properties) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("azatyamanaev", "Tuckjedtemyaux0");
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("azatyamanaev@yandex.ru"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("azatyamanaev@yandex.ru"));
            message.setSubject("Request for inquiry");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText("Here is your inquiry");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            mimeBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            mimeBodyPart.setDataHandler(new DataHandler(source));
            String[] str = filename.split("/");
            mimeBodyPart.setFileName(str[str.length - 1]);
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
