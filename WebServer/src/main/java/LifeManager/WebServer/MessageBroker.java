package LifeManager.WebServer;

import LifeManager.WebServer.model.Pacient;
import LifeManager.WebServer.model.Sensor;
import LifeManager.WebServer.repository.SensorRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

@Component
public class MessageBroker {

    @Autowired
    public SensorRepository sensorRepository;

    @RabbitListener(queues = "heartbeat")
    public void receive_heart_beat(String in) throws InterruptedException, ResourceNotFoundException {
        Queue heart_beat_queue = new LinkedList<String>();
        JSONObject jo = new JSONObject(in);
        double heart_beat = jo.getDouble("heartbeat");
        if (heart_beat_queue.size() > 30) {
            heart_beat_queue.poll();
        }
        heart_beat_queue.add(heart_beat);
        System.out.print(heart_beat);
        System.out.println(" was added to  heartbeat queue");
    }

    @RabbitListener(queues = "sugar_level")
    public void receive_sugar_level(String in) throws InterruptedException, ResourceNotFoundException {
        Queue sugar_level_queue = new LinkedList<String>();
        JSONObject jo = new JSONObject(in);
        double sugar_level = jo.getDouble("sugar");
        Integer id = jo.getInt("id");
        if (sugar_level_queue.size() > 30) {
            sugar_level_queue.poll();
        }
        sugar_level_queue.add(sugar_level);
        System.out.print(sugar_level);
        System.out.println(" was added to  sugar level queue");
        Optional<Sensor> op = sensorRepository.findById(Long.valueOf(id));
        if (op.isPresent()) {
            Pacient p = op.get().getPatient();
        }

    }

    @RabbitListener(queues = "blood_pressure")
    public void receive_blood_pressure(String in) throws InterruptedException, ResourceNotFoundException {
        Queue systolic_queue = new LinkedList<String>();
        Queue diastolic_queue = new LinkedList<String>();
        JSONObject jo = new JSONObject(in);
        double systolic = jo.getDouble("systolic");
        if (systolic_queue.size() > 30) {
            systolic_queue.poll();
        }
        systolic_queue.add(systolic);
        System.out.print(systolic);
        System.out.println(" was added to  systolic queue");
        double diastolic = jo.getDouble("diastolic");
        if (diastolic_queue.size() > 30) {
            diastolic_queue.poll();
        }
        diastolic_queue.add(diastolic);
        System.out.print(diastolic);
        System.out.println(" was added to  diastolic queue");
    }


    @RabbitListener(queues = "temperature")
    public void receive_body_temp(String in) throws InterruptedException, ResourceNotFoundException {
        Queue body_temp_queue = new LinkedList<String>();
        JSONObject jo = new JSONObject(in);
        double body_temp = jo.getDouble("temperature");
        if (body_temp_queue.size() > 30) {
            body_temp_queue.poll();
        }
        body_temp_queue.add(body_temp);
        System.out.print(body_temp);
        System.out.println(" was added to  body temperature queue");
    }

    @RabbitListener(queues = "oxygen_level")
    public void receive_oxygen_level(String in) throws InterruptedException, ResourceNotFoundException {
        Queue oxygen_level_queue = new LinkedList<String>();
        JSONObject jo = new JSONObject(in);
        double oxygen_level = jo.getDouble("temperature");
        if (oxygen_level_queue.size() > 30) {
            oxygen_level_queue.poll();
        }
        oxygen_level_queue.add(oxygen_level);
    }

}