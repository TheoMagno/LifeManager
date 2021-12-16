package LifeManager.WebServer;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    //Doctors
    @Autowired
    private AppService service;

    @PostMapping("/doctors")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        return service.saveDoctor(doctor);
    }

    @GetMapping("/doctors")
    public List<Doctor> findDoctors(@RequestParam(required=false) Long medicalID, @RequestParam(required=false) String name) {
        if (medicalID != null) {
            return service.getByMedicalID(medicalID);
        }
        else if (name != null) {
            return service.getByDoctorName(name);
        }
        return service.getAllDoctors();
    }

    @DeleteMapping("/doctors/{id}")
    public String deleteDoctor(@PathVariable(value = "id") Long id) {
        return service.deleteDoctor(id);
    }

    //Pacients
    @PostMapping("/pacients/{id}")
    public Pacient addPacient(@PathVariable(value = "id") Long medicalID, @Valid @RequestBody Pacient pacient) {
        return service.savePacient(medicalID, pacient);
    }

    @GetMapping("/pacients")
    public List<Pacient> findPacients(@RequestParam(required=false) Long numUtente, @RequestParam(required=false) String name) {
        if (numUtente != null) {
            return service.getByUtente(numUtente);
        }
        else if (name != null) {
            return service.getByPacientName(name);
        }
        return service.getAllPacients();
    }

    @DeleteMapping("/pacients/{id}")
    public String deletePacien(@PathVariable(value = "id") Long id) {
        return service.deletePacient(id);
    }
}
