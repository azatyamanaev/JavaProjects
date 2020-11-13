package ru.itis.documentsflow.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.itis.documentsflow.models.UsersData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeoutException;

public class ScanConsumer {
    private final static String EXCHANGE_NAME = "data_exchange";
    private final static String EXCHANGE_TYPE = "fanout";
    private final static String PATH = "/home/monitor/Desktop/storage/documents-flow-st/scan";

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
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                UsersData usersData = mapper.readValue(message.getBody(), UsersData.class);
                String name = usersData.getName();
                String lastName = usersData.getLastName();
                File userFile = usersData.getFile();
                File dir = new File(PATH + File.separator + name + " " + lastName);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                try {
                    Path source = userFile.toPath();
                    Files.copy(source, new FileOutputStream(dir.getAbsolutePath() + File.separator + userFile.getName()));
                    System.out.println("Saved scan for user " + name + " " + lastName);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };

            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
