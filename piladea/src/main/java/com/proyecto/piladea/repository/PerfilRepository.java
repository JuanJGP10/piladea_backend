package com.proyecto.piladea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.piladea.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Perfil findByUsuarioEmail(String email);

}