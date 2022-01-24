package LifeManager.WebServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import LifeManager.WebServer.model.*;
import LifeManager.WebServer.repository.*;
@Service
public class AppService {
    //Patients
    @Autowired
    private PatientRepository patients;

    //Saves Patient and sets Doctor
    public Patient savePatient(Long medicalID, Patient patient) {
        patient.setDoctor(getByMedicalID(medicalID).get(0));
        return patients.save(patient);
    }

    //Gets a list of all Patients
    public List<Patient> getAllPatients() {
        return patients.findAll();
    }

    //Deletes Patient by id
    public String deletePatient(Long id) {
        patients.deleteById(id);
        return "Patient removed!!" + id;
    }

    //Gets Patient by numUtente
    public List<Patient> getByUtente(Long numUtente) {
        return patients.findByNumUtente(numUtente);
    }

    //Gets a list of Patients by name
    public List<Patient> getByPatientName(String name) {
        return patients.findByNameContainingIgnoreCase(name);
    }

    //Gets a list of Patients by Doctor specialization
    public List<Patient> getPatientByDoctorSpeciality(String speciality) {
        return patients.findByDoctorSpeciality(speciality);
    }

    //Gets a Patient by id
    public Optional<Patient> getPatientById(Long id) {
        return patients.findById(id);
    }

    //Doctors
    @Autowired
    private DoctorRepository doctors;

    //Saves doctor
    public Doctor saveDoctor(Doctor doctor) {
        return doctors.save(doctor);
    }

    //Gets a list do Doctors
    public List<Doctor> getAllDoctors() {
        return doctors.findAll();
    }

    //Deletes Doctor by medicalId
    public String deleteDoctor(Long medicalId) {
        Doctor d = getByMedicalID(medicalId).get(0);
        doctors.deleteById(d.getId());
        return "Doctor removed!!" + medicalId;
    }

    //Gets Doctor by medicalId
    public List<Doctor> getByMedicalID(Long medicalID) {
        return doctors.findByMedicalID(medicalID);
    }

    //Gets Doctor by name
    public List<Doctor> getByDoctorName(String name) {
        return doctors.findByNameContainingIgnoreCase(name);
    }

    //Gets a list of all Doctor specializations
    public List<String> getDoctorSpecializations() {
        return doctors.findSpecializations();
    }

    //Gets Doctor by specialization
    public List<Doctor> getDoctorBySpeciality(String speciality) {
        return doctors.findBySpeciality(speciality);
    }

    //Alarms
    @Autowired
    private AlarmRepository alarms;

    //Gets all Alarms
    public List<Alarm> getAllAlarms() {
        return alarms.findAll();
    }

    //Saves an Alarm
    public Alarm saveAlarm(Alarm alarm) {
        return alarms.save(alarm);
    }

    //Sensors
    @Autowired
    private SensorRepository sensors;

    //Gets a list of all Sensors
    public List<Sensor> getAllSensors() {
        return sensors.findAll();
    }

    //Saves a Sensor
    public Sensor saveSensor(int type){ //guardamos instãncias de sensores
        Sensor nSensor = new Sensor(0, type); //0 means turned off
        return sensors.save(nSensor);
    }
}
