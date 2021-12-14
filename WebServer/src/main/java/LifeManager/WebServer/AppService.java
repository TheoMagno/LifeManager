package LifeManager.WebServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppService {
    //Pacients
    @Autowired
    private PacientRepository pacients;

    public Pacient savePacient(Pacient pacient) {
        return pacients.save(pacient);
    }

    public List<Pacient> getAllPacients() {
        return pacients.findAll();
    }

    public String deletePacient(Long id) {
        pacients.deleteById(id);
        return "Pacient removed!!" + id;
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
}
