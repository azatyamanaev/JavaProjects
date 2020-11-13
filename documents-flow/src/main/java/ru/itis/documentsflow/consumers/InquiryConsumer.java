package ru.itis.documentsflow.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.itis.documentsflow.models.UsersData;
import ru.itis.documentsflow.services.*;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class InquiryConsumer {
    private final static String EXCHANGE_NAME = "data_exchange";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            ObjectMapper mapper = new ObjectMapper();
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();

            channel.queueBind(queue, EXCHANGE_NAME, "");

            Properties properties = setProperties();

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                UsersData usersData = mapper.readValue(message.getBody(), UsersData.class);
                String name = usersData.getName();
                String lastName = usersData.getLastName();
                TemplateService template = new EnrollmentTemplateServiceImpl();
                String filename = template.createTemplate(usersData);
                EmailService emailService = new EmailServiceImpl();
                emailService.sendInquiry(filename, properties);
                try {
                    System.out.println("Created and send to email inquiry for user " + name + " " + lastName);
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

    public static Properties setProperties() {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "smtp.yandex.ru");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "8080");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return properties;
    }

}
