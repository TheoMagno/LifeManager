package LifeManager.WebServer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Queue;

public class MessageBroker {

    private static final String EXCHANGE_NAME = "logs";
    private final Queue blood_pressure = new LinkedList<String>();

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        factory.setPort(15678);

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.queueBind("blood_pressure", EXCHANGE_NAME, "");

        Queue systolic_queue = new LinkedList<String>();
        Queue diastolic_queue = new LinkedList<String>();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            JSONObject jo = new JSONObject(message);
            double systolic = jo.getDouble("systolic");
            if(systolic_queue.size() > 30){
                systolic_queue.poll();}
            systolic_queue.add(systolic);
            double diastolic = jo.getDouble("diastolic");
            if(diastolic_queue.size() > 30){
                diastolic_queue.poll();}
            diastolic_queue.add(diastolic);

        };
        channel.basicConsume("blood_pressure", true, deliverCallback, consumerTag -> { });

        System.out.println(diastolic_queue.peek());
    }
}