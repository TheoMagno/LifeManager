package LifeManager.WebServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import LifeManager.WebServer.model.*;
import LifeManager.WebServer.repository.*;
@Service
public class AppService {
    //Pacients
    @Autowired
    private PacientRepository pacients;

    public Pacient savePacient(Long medicalID, Pacient pacient) {
        pacient.setDoctor(getByMedicalID(medicalID).get(0));
        return pacients.save(pacient);
    }

    public List<Pacient> getAllPacients() {
        return pacients.findAll();
    }

    public String deletePacient(Long id) {
        pacients.deleteById(id);
        return "Pacient removed!!" + id;
    }

    public List<Pacient> getByUtente(Long numUtente) {
        return pacients.findByNumUtente(numUtente);
    }

    public List<Pacient> getByPacientName(String name) {
        return pacients.findByNameContainingIgnoreCase(name);
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
}
