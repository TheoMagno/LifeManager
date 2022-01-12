package LifeManager.WebServer;

import LifeManager.WebServer.model.Patient;
import LifeManager.WebServer.model.Sensor;
import LifeManager.WebServer.repository.SensorRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class MessageBroker {
    
    private HashMap<Integer, Queue> temperature = new HashMap<Integer, Queue>();
    private HashMap<Integer, Queue> sugar = new HashMap<Integer, Queue>();
    private HashMap<Integer, Queue> oxygen = new HashMap<Integer, Queue>();
    private HashMap<Integer, Queue> heartbeat = new HashMap<Integer, Queue>();
    private HashMap<Integer, Queue> blood_pressure = new HashMap<Integer, Queue>();

    @Autowired
    public SensorRepository sensorRepository;

    @RabbitListener(queues = "heartbeat")
    public void receive_heart_beat(String in) throws InterruptedException, ResourceNotFoundException {
       
        JSONObject jo = new JSONObject(in);
        double heart_beat = jo.getDouble("heartbeat");

        System.out.println("HEART" + heart_beat);

        int s_id = jo.getInt("id");
        if(heartbeat.containsKey(s_id)){
            Queue q = heartbeat.get(s_id);
            if (q.size() > 30) {
                q.poll();
            }
            q.add(heart_beat);
            sugar.replace(s_id,q);
        }
        else{
            Queue queue = new LinkedList<String>();
            queue.add(heart_beat);
            sugar.put(s_id,queue);
        }
    }

    @RabbitListener(queues = "sugar_level")
    public void receive_sugar_level(String in) throws InterruptedException, ResourceNotFoundException {
        JSONObject jo = new JSONObject(in);
        double sugar_level = jo.getDouble("sugar");
        int s_id = jo.getInt("id");
        System.out.println("SUGAR" + sugar_level);
        if(sugar.containsKey(s_id)){
            Queue q = sugar.get(s_id);
            if (q.size() > 30) {
                q.poll();
            }
            q.add(sugar_level);
            sugar.replace(s_id,q);
        }
        else{
            Queue queue = new LinkedList<String>();
            queue.add(sugar_level);
            sugar.put(s_id,queue);
        }
    }

    @RabbitListener(queues = "blood_pressure")
    public void receive_blood_pressure(String in) throws InterruptedException, ResourceNotFoundException {
        JSONObject jo = new JSONObject(in);
        double systolic = jo.getDouble("systolic");
        double diastolic = jo.getDouble("diastolic");
        int s_id = jo.getInt("id");
        System.out.println("BP_SYS" + systolic);
        System.out.println("BP_DIAS" + diastolic);
        if(blood_pressure.containsKey(s_id)){
            Queue q = blood_pressure.get(s_id);
            if (q.size() > 30) {
                q.poll();
            }
            double[] bp = {systolic,diastolic};
            q.add(bp);
            blood_pressure.replace(s_id,q);
        }
        else{
            Queue queue = new LinkedList<String[]>();
            double[] bp = {systolic,diastolic};
            queue.add(bp);
            temperature.put(s_id,queue);
        }
    }

    @RabbitListener(queues = "temperature")
    public void receive_body_temp(String in) throws InterruptedException, ResourceNotFoundException {
        JSONObject jo = new JSONObject(in);
        double body_temp = jo.getDouble("temperature");
        System.out.println("temp " + body_temp);
        int s_id = jo.getInt("id");
        if(temperature.containsKey(s_id)){
            Queue q = temperature.get(s_id);
            if (q.size() > 30) {
                q.poll();
            }
            q.add(body_temp);
            temperature.replace(s_id,q);
        }
        else{
            Queue queue = new LinkedList<String>();
            queue.add(body_temp);
            temperature.put(s_id,queue);
        }
    }

    @RabbitListener(queues = "oxygen_level")
    public void receive_oxygen_level(String in) throws InterruptedException, ResourceNotFoundException {
        
    }

    public  Queue getBlood_pressure(Integer id) {
        //Given a sensor id retrieve blood pressure
        return blood_pressure.get(id);
    }

    public Queue getHeartbeat(Integer id) {
        return heartbeat.get(id);
    }

    public Queue getTemperature(Integer id) {
        return temperature.get(id);
    }

    public Queue getOxygen(Integer id) {
        return oxygen.get(id);
    }

    public Queue getSugar(Integer id) {
        return sugar.get(id);
    }
}