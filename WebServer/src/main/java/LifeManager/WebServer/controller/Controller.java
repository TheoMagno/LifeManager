package LifeManager.WebServer.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import LifeManager.WebServer.service.*;
import LifeManager.WebServer.model.*;


@RestController
@CrossOrigin
public class Controller {
    //Doctors
    @Autowired
    private AppService service;

    @Autowired
    private WSService wsservice;
 
    @PostMapping("/send-message")
    public void sendMessage(@RequestBody FrontEndMessage message){
        System.out.println("In Send Message");
        System.out.println(message.getType());
        FrontEndMessage mess = new FrontEndMessage(message.getId(),message.getType(),message.getValue());
        System.out.println(mess);
        wsservice.notifyFrontend(mess);
    }
   
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public FrontEndMessage greeting( int id,FrontEndMessage message) {
       // simulated delay
       return message;
    }

    //Post a Doctor requesting a Doctor object.
    @PostMapping("/doctors")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        return service.saveDoctor(doctor);
    }

    //Gets Doctors; there are 3 optional Paramaters: medicalID, name and specialization. The first one returns Doctors with medical number equals to medicalID; the second one returns Doctors with name equals to name; the third one returns Doctors with specialization equals to speciality.
    @GetMapping("/doctors")
    public List<Doctor> findDoctors(@RequestParam(required = false) Long medicalID, @RequestParam(required=false) String name, @RequestParam(required = false) String speciality) {
        if (medicalID != null) {
            return service.getByMedicalID(medicalID);
        }
        else if (name != null) {
            return service.getByDoctorName(name);
        }
        else if (speciality != null) {
            return service.getDoctorBySpeciality(speciality);
        }
        return service.getAllDoctors();
    }

    //Deletes Doctor by id.
    @DeleteMapping("/doctors/{medicalID}")
    public String deleteDoctor(@PathVariable(value = "medicalID") Long medicalID) {
        return service.deleteDoctor(medicalID);
    }

    //Gets the list of specializations.
    @GetMapping("/doctors/types")
    public List<String> getSpecialization() {
        return service.getDoctorSpecializations();
    }

    //Patients
    //Posts a Patient and associates them to a Doctor, requires a medicalID and a Patient object.
    @PostMapping("/doctors/{id}/patients")
    public Patient addPatient(@PathVariable(value = "id") Long medicalID, @Valid @RequestBody Patient patient) {
        return service.savePatient(medicalID, patient);
    }

    //Gets Patients; there are 3 optional Parameters: numUtente, name and speciality. The first returns Patients with the health service number equals to numUtente; the second one returns Patients with name similar to name; the third one returns Patients whose Doctor's specializations is equal to speciality.
    @GetMapping("/patients")
    public List<Patient> findPatients(@RequestParam(required = false) Long numUtente, @RequestParam(required=false) String name, @RequestParam(required = false) String speciality) {
        if (numUtente != null) {
            return service.getByUtente(numUtente);
        }
        else if (name != null) {
            return service.getByPatientName(name);
        }
        else if (speciality != null) {
            return service.getPatientByDoctorSpeciality(speciality);
        }
        return service.getAllPatients();
    }

    //Deletes Patient by id.
    @DeleteMapping("/patients/{numUtente}")
    public String deletePatient(@PathVariable(value = "numUtente") Long numUtente) {
        return service.deletePatient(numUtente);
    }

    //Gets Patient by id.
    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatient(@PathVariable(value = "id") Long id) {
        return service.getPatientById(id);
    }

    /*Sensors*/
    //Gets all Sensors.
    @GetMapping("/sensors")
    public List<Sensor> findSensors() {
        return service.getAllSensors();
    }

    //Adds a Sensor.
    public Sensor addSensor(int type){
        return service.saveSensor(type);
    }


    //Alarms
    //Gets all Alarms.
    @GetMapping("/alarms")
    public List<Alarm> findAlarms() {
        return service.getAllAlarms();
    }

    //Adds an Alarm.
    public Alarm addAlarm(Alarm alarm) {
        return service.saveAlarm(alarm);
    }

    //Login
    //Request used for the login. Requires an "username" (medicalID or numUtente) and a password; if there is an user with these credentials returns true, otherwise returns false.
    @GetMapping("/login")
    public boolean login(@RequestParam(required = false) Long medicalID, @RequestParam(required = false) Long numUtente, @RequestParam(required = true) String password) {
        if (medicalID != null) {
            try {
                Doctor d = service.getByMedicalID(medicalID).get(0);
                if (d.getPassword().equals(password)) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }
        else if (numUtente != null) {
            try {
                Patient p = service.getByUtente(numUtente).get(0);
                if (p.getPassword().equals(password)) {
                    return true;
                }
                return false;   
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
