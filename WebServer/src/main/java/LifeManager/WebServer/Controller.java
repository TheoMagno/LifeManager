package LifeManager.WebServer;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List<Doctor> findDoctors() {
        return service.getAllDoctors();
    }

    @DeleteMapping("/doctors/{id}")
    public String deleteDoctor(@PathVariable(value = "id") Long id) {
        return service.deleteDoctor(id);
    }

    //Pacients
    @PostMapping("/pacients")
    public Pacient addPacient(@Valid @RequestBody Pacient pacient) {
        return service.savePacient(pacient);
    }

    @GetMapping("/doctors")
    public List<Pacient> findPacients() {
        return service.getAllPacients();
    }

    @DeleteMapping("/doctors/{id}")
    public String deletePacien(@PathVariable(value = "id") Long id) {
        return service.deletePacient(id);
    }
}
