package LifeManager.WebServer.repository;

import java.util.List;
import LifeManager.WebServer.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository<Pacient, Long> {
    List<Pacient> findByNumUtente(Long numUtente);
    List<Pacient> findByNameContainingIgnoreCase(String name);
}
