package com.proyecto.piladea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.piladea.model.Premio;

public interface PremioRespository extends JpaRepository<Premio, Long>{
    
}