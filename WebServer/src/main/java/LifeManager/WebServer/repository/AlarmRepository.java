package LifeManager.WebServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import LifeManager.WebServer.model.*;
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    
}