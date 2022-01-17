package LifeManager.WebServer;

import LifeManager.WebServer.model.FrontEndMessage;
import LifeManager.WebServer.model.Patient;
import LifeManager.WebServer.model.Sensor;
import LifeManager.WebServer.controller.*;
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
    private HashMap<Integer, Queue> oxygenhm = new HashMap<Integer, Queue>();
    private HashMap<Integer, Queue> heartbeat = new HashMap<Integer, Queue>();
    private HashMap<Integer, Queue> blood_pressure = new HashMap<Integer, Queue>();

    @Autowired
    public SensorRepository sensorRepository;

    @Autowired
    public Controller controller;

    @RabbitListener(queues = "heartbeat")
    public void receive_heart_beat(String in) throws InterruptedException, ResourceNotFoundException {
       
        JSONObject jo = new JSONObject(in);
        double heart_beat = jo.getDouble("heartbeat");

        int type = jo.getInt("type");

        int s_id = jo.getInt("id");
        //System.out.println(s_id);
        //System.out.println(heartbeat.containsKey(s_id));
        //System.out.println("---------------------------------------------");
        if(heartbeat.containsKey(s_id)){
            
            Queue q = heartbeat.get(s_id);
            if (q.size() > 30) {
                q.poll();
            }
            q.add(heart_beat);
            //heartbeat.replace(s_id,q);
           
            //heartbeat will contain a key value pair defined as sensor_id : sensorqueue
            //q will contain the last 30 values from the sensor
        }
        else{
            Queue queue = new LinkedList<String>();
            //System.out.println("heart "+s_id);
            System.out.println("I should only appear once.");
            queue.add(heart_beat);
            heartbeat.put(s_id,queue);
            FrontEndMessage mess = new FrontEndMessage(s_id, type, String.valueOf(type)); //criar uma instancia para frontend message
            
            controller.greeting(s_id , mess); //Sends message to greeting method in controller
            //controller.addSensor(type);
            
        }
    }

    @RabbitListener(queues = "sugar_level")
    public void receive_sugar_level(String in) throws InterruptedException, ResourceNotFoundException {
        JSONObject jo = new JSONObject(in);
        double sugar_level = jo.getDouble("sugar");

        int type = jo.getInt("type");

        int s_id = jo.getInt("id");
        
       
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
            //controller.addSensor(type);
            //System.out.println("sugar "+s_id);
            //sugar will contain the most recent value from the sensor
            //q will contain the last 30 values from the sensor
        }
    }

    @RabbitListener(queues = "blood_pressure")
    public void receive_blood_pressure(String in) throws InterruptedException, ResourceNotFoundException {
        JSONObject jo = new JSONObject(in);
        double systolic = jo.getDouble("systolic");
        double diastolic = jo.getDouble("diastolic");

        int type = jo.getInt("type");

        int s_id = jo.getInt("id");
        
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
            blood_pressure.put(s_id,queue);
            //controller.addSensor(type);
            //System.out.println("BP  "+s_id);
            //blood_pressure will contain the most recent value from the sensor
            //q will contain the last 30 values from the sensor
        }
    }

    @RabbitListener(queues = "temperature")
    public void receive_body_temp(String in) throws InterruptedException, ResourceNotFoundException {
        JSONObject jo = new JSONObject(in);
        double body_temp = jo.getDouble("temperature");

        int type = jo.getInt("type");

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
            //controller.addSensor(type);
            //System.out.println("temp " + s_id);
            //temperature will contain the most recent value from the sensor
            //q will contain the last 30 values from the sensor
        }
    }

    @RabbitListener(queues = "oxygen_level")
    public void receive_oxygen_level(String in) throws InterruptedException, ResourceNotFoundException {
        JSONObject jo = new JSONObject(in);
        double oxygen = jo.getDouble("oxygen");
        
        int type = jo.getInt("type");
        int s_id = jo.getInt("id");
        if(oxygenhm.containsKey(s_id)){
            Queue q = oxygenhm.get(s_id);
            if (q.size() > 30) {
                q.poll();
            }
            q.add( oxygen );
            oxygenhm.replace(s_id,q);
        }
        else{
            Queue queue = new LinkedList<String>();
            queue.add( oxygen );
            oxygenhm.put(s_id,queue);
            //controller.addSensor(type);
            //System.out.println("O2 " +  s_id );
        }
    }

    public Queue getBlood_pressure(Integer id) {
        //Given a sensor id retrieve blood pressure queue
        return blood_pressure.get(id);
    }

    public Queue getHeartbeat(Integer id) {
        return heartbeat.get(id);
    }

    public Queue getTemperature(Integer id) {
        return temperature.get(id);
    }

    public Queue getOxygen(Integer id) {
        return oxygenhm.get(id);
    }

    public Queue getSugar(Integer id) {
        return sugar.get(id);
    }
}