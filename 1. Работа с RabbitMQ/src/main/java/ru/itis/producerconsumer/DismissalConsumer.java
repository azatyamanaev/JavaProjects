package ru.itis.producerconsumer;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class DismissalConsumer {
    private final static String EXCHANGE_NAME = "data";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String PATH = "/home/monitor/Desktop/pdf/";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();

            channel.queueBind(queue, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                String string = new String(message.getBody());
                String data[] = string.split(" ");
                String name = data[0];
                String lastName = data[1];
                String series = data[2];
                String number = data[3];
                String age = data[4];
                String date = data[5];
                Random random = new Random();
                Integer num = (Integer.valueOf(series) + Integer.valueOf(number)) * random.nextInt(1000000);
                try {
                    String line = "Remand Application from " + name + " " + lastName;
                    List<String> text = new ArrayList<String>();
                    text.add("");
                    text.add("                                                                   from " + name + " " + lastName);
                    text.add("");
                    text.add("");
                    text.add("");
                    text.add("");
                    text.add("");
                    text.add("");
                    text.add("");
                    text.add("                             Application for dismissal                             ");
                    text.add("");
                    text.add("");
                    text.add("    Please, dismiss me ...");
                    text.add("I attach my passport data: " + "series - " + series + ", number - " + number + ", date - " + date);
                    PdfDocument pdf = new PdfDocument(new PdfWriter(PATH + "DismissalApplication" + num));
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
                    System.out.println("Generated pdf DismissalApplication" + num);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
