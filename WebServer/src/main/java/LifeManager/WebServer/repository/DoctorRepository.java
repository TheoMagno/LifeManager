package LifeManager.WebServer.repository;

import LifeManager.WebServer.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    List<Doctor> findByMedicalID(Long medicalID);
    List<Doctor> findByNameContainingIgnoreCase(String name);
}
