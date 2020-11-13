package ru.itis.documentsflow.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.documentsflow.models.UsersData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    private final static String DATA_EXCHANGE = "data_exchange";
    private final static String TYPE_FANOUT = "fanout";

    private final static String CONFIRMATION_QUEUE = "confirmation_queue";
    private final static String CONFIRMATION_ROUTING_KEY = "confirmation";
    private final static String CONFIRMATION_EXCHANGE = "confirmation_exchange";
    private final static String TYPE_DIRECT = "direct";

    private final static String SERVICE_EXCHANGE = "service_exchange";
    private final static String TYPE_TOPIC = "topic";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        ObjectMapper mapper = new ObjectMapper();

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(DATA_EXCHANGE, TYPE_FANOUT);

            channel.exchangeDeclare(CONFIRMATION_EXCHANGE, TYPE_DIRECT);
            channel.queueBind(CONFIRMATION_QUEUE, CONFIRMATION_EXCHANGE, CONFIRMATION_ROUTING_KEY);

            channel.exchangeDeclare(SERVICE_EXCHANGE, TYPE_TOPIC);

            File fileFan = new File("datafanout.txt");
            File fileDir = new File("datadirect.txt");
            File fileTop = new File("datatopic.txt");
            Scanner scanner = new Scanner(System.in);
            int num = 0;
            BufferedReader reader = null;
            String currentLine;
            while (num != -1) {
                System.out.println("Choose type of exchange: 1 - fanout, 2 - direct, 3 - topic");
                num = scanner.nextInt();
                switch (num) {
                    case (1):
                        reader = new BufferedReader(new FileReader(fileFan));
                        while ((currentLine = reader.readLine()) != null) {
                            String[] str = currentLine.split(" ");
                            String attachmentPath = str[str.length - 1];
                            File f = new File(attachmentPath);
                            UsersData data = UsersData.builder()
                                    .file(f)
                                    .name(str[0])
                                    .lastName(str[1])
                                    .build();

                            channel.basicPublish(DATA_EXCHANGE, "", null, mapper.writeValueAsBytes(data));
                        }
                        break;
                    case (2):
                        reader = new BufferedReader(new FileReader(fileDir));
                        while ((currentLine = reader.readLine()) != null) {
                            String currentRouting = currentLine.substring(currentLine.lastIndexOf(".") + 1);

                            channel.basicPublish(CONFIRMATION_EXCHANGE, currentRouting, null, currentLine.getBytes());
                        }
                        break;
                    case (3):
                        reader = new BufferedReader(new FileReader(fileTop));
                        while ((currentLine = reader.readLine()) != null) {
                            String currentRouting = currentLine.substring(currentLine.lastIndexOf(" ") + 1);

                            channel.basicPublish(SERVICE_EXCHANGE, currentRouting, null, currentLine.getBytes());
                        }
                        break;
                }
            }


        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
