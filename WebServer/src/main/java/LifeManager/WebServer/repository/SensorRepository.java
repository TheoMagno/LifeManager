package LifeManager.WebServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import LifeManager.WebServer.model.*;
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    
}