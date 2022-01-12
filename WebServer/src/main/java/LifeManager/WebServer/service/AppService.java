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

    public Patient savePatient(Long medicalID, Patient patient) {
        patient.setDoctor(getByMedicalID(medicalID).get(0));
        return patients.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patients.findAll();
    }

    public String deletePatient(Long id) {
        patients.deleteById(id);
        return "Patient removed!!" + id;
    }

    public List<Patient> getByUtente(Long numUtente) {
        return patients.findByNumUtente(numUtente);
    }

    public List<Patient> getByPatientName(String name) {
        return patients.findByNameContainingIgnoreCase(name);
    }

    public List<Patient> getPatientByDoctorSpeciality(String speciality) {
        return patients.findByDoctorSpeciality(speciality);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patients.findById(id);
    }

    //Doctors
    @Autowired
    private DoctorRepository doctors;

    public Doctor saveDoctor(Doctor doctor) {
        return doctors.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctors.findAll();
    }

    public String deleteDoctor(Long id) {
        doctors.deleteById(id);
        return "Doctor removed!!" + id;
    }

    public List<Doctor> getByMedicalID(Long medicalID) {
        return doctors.findByMedicalID(medicalID);
    }

    public List<Doctor> getByDoctorName(String name) {
        return doctors.findByNameContainingIgnoreCase(name);
    }

    public List<String> getDoctorSpecializations() {
        return doctors.findSpecializations();
    }

    public List<Doctor> getDoctorBySpeciality(String speciality) {
        return doctors.findBySpeciality(speciality);
    }

    //Alarms
    @Autowired
    private AlarmRepository alarms;

    public List<Alarm> getAllAlarms() {
        return alarms.findAll();
    }

    //Sensors
    @Autowired
    private SensorRepository sensors;

    public List<Sensor> getAllSensors() {
        return sensors.findAll();
    }
}
