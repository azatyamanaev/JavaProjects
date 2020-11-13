package ru.itis.documentsflow.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class ConfirmationConsumer {
    private final static String CONFIRMATION_QUEUE = "confirmation_queue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.basicConsume(CONFIRMATION_QUEUE, false, (consumerTag, message) -> {
                String path = new String(message.getBody());
                File file = new File(path);
                String confirmed = "no";
                System.out.println("Is it the correct file?(y/n)");
                Scanner scanner = new Scanner(System.in);
                confirmed = scanner.nextLine();
                while (!confirmed.equals("y") && !confirmed.equals("n")) {
                    System.out.println("You need to write 'y' or 'n'");
                    confirmed = scanner.nextLine();
                }
                try {
                    if (confirmed.equals("y")) {
                        System.out.println("Document " + file.getName() + " is correct");
                    } else {
                        System.out.println("Document " + file.getName() + " is not correct");
                    }
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }

            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
