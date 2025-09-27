package Rabbit_MQ_Handler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AddToMQ {
    private final static String QUEUE_NAME = "JOBS_QUEUE";
    private final static ConnectionFactory factory = new ConnectionFactory();

    @SuppressWarnings("CallToPrintStackTrace")
    public static Boolean AddJobToMQ(String job_name){
        factory.setHost("RABBITMQ1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "1"+job_name;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        catch (IOException | TimeoutException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
