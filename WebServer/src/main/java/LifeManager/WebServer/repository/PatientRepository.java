package LifeManager.WebServer.repository;

import java.util.List;
import java.util.Optional;

import LifeManager.WebServer.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNumUtente(Long numUtente);
    List<Patient> findByNameContainingIgnoreCase(String name);
    List<Patient> findByDoctorSpeciality(String speciality);
    Optional<Patient> findById(Long id);
}
