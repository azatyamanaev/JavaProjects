package ru.itis.documentsflow.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.documentsflow.models.Statistics;
import ru.itis.documentsflow.models.UsersData;
import ru.itis.documentsflow.services.StatisticsService;
import ru.itis.documentsflow.services.StatisticsServiceImpl;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class StatisticsConsumer {
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

            JdbcTemplate jdbcTemplate = createJdbcTemplate();

            DeliverCallback deliverCallback = (consumerTag, message) -> {

                UsersData usersData = mapper.readValue(message.getBody(), UsersData.class);
                String name = usersData.getName();
                String lastName = usersData.getLastName();
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                Statistics statistics = Statistics.builder().username(name + " " + lastName).date(timestamp.toString()).build();
                StatisticsService statisticsService = new StatisticsServiceImpl(jdbcTemplate);
                statisticsService.saveStat(statistics);
                try {
                    System.out.println("Saved statistics for user " + name + " " + lastName);
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

    public static JdbcTemplate createJdbcTemplate() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String url = properties.getProperty("db.url");
        String driver = properties.getProperty("db.driver");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new HikariDataSource(config));
        return jdbcTemplate;
    }
}
