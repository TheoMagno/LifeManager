package LifeManager.WebServer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository<Pacient, Long> {
    
}
