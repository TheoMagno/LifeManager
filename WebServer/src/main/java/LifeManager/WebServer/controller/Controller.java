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

import LifeManager.WebServer.service.*;
import LifeManager.WebServer.model.*;


@RestController
@CrossOrigin
public class Controller {
    //Doctors
    @Autowired
    private AppService service;

    @PostMapping("/doctors")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        return service.saveDoctor(doctor);
    }

    @GetMapping("/doctors")
    public List<Doctor> findDoctors(@RequestParam(required=false) Long medicalID, @RequestParam(required=false) String name, @RequestParam(required = false) String speciality) {
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

    @DeleteMapping("/doctors/{id}")
    public String deleteDoctor(@PathVariable(value = "id") Long id) {
        return service.deleteDoctor(id);
    }

    @GetMapping("/doctors/types")
    public List<String> getSpecialization() {
        return service.getDoctorSpecializations();
    }

    //Patients
    @PostMapping("/doctors/{id}/patients")
    public Patient addPatient(@PathVariable(value = "id") Long medicalID, @Valid @RequestBody Patient patient) {
        return service.savePatient(medicalID, patient);
    }

    @GetMapping("/patients")
    public List<Patient> findPatients(@RequestParam(required=false) Long numUtente, @RequestParam(required=false) String name, @RequestParam(required = false) String speciality) {
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

    @DeleteMapping("/patients/{id}")
    public String deletePatient(@PathVariable(value = "id") Long id) {
        return service.deletePatient(id);
    }

    @GetMapping("/patients/{id}")
    public Optional<Patient> getPatient(@PathVariable(value = "id") Long id) {
        return service.getPatientById(id);
    }

    //Sensors
    @GetMapping("/sensors")
    public List<Sensor> findSensors() {
        return service.getAllSensors();
    }

    //Alarms
    @GetMapping("/alarms")
    public List<Alarm> findAlarms() {
        return service.getAllAlarms();
    }
}
