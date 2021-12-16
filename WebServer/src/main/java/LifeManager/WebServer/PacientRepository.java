package LifeManager.WebServer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository<Pacient, Long> {
    List<Pacient> findByNumUtente(Long numUtente);
    List<Pacient> findByNameContainingIgnoreCase(String name);
}
