package LifeManager.WebServer.repository;

import LifeManager.WebServer.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    List<Doctor> findByMedicalID(Long medicalID);
    List<Doctor> findByNameContainingIgnoreCase(String name);
    @Query(value = "select distinct speciality from doctor", nativeQuery = true)
    List<String> findSpecializations();
    List<Doctor> findBySpeciality(String speciality);
}
