package com.proyecto.piladea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.piladea.model.Trayecto;

public interface TrayectoRepository extends JpaRepository<Trayecto, Long>{
    
}