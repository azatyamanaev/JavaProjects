package ru.itis.documentsflow.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NotConfirmedServiceConsumer {
    private final static String NOT_CONFIRMED_ROUTING_KEY = "files.notConfirmed";
    private final static String SERVICE_EXCHANGE = "service_exchange";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, SERVICE_EXCHANGE, NOT_CONFIRMED_ROUTING_KEY);
            channel.basicConsume(queueName, false, (consumerTag, message) -> {
                String path = new String(message.getBody());

                try {
                    System.out.println("You are not entitled for receiving xxx service because your document is not confirmed");
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
