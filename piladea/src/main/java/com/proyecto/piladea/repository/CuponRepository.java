package com.proyecto.piladea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.piladea.model.Cupon;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Long>{
    
    // 1. Buscar todos los cupones de un usuario específico
    // Spring crea el SQL automáticamente al leer "findByPerfilId"
    List<Cupon> findByPerfilId(Long perfilId);

    // 2. Buscar un cupón por su código QR (útil para el escáner)
    Optional<Cupon> findByUrlQR(String urlQR);

    // 3. Buscar cupones no canjeados de un usuario
    List<Cupon> findByPerfilIdAndCanjeadoFalse(Long perfilId);
    
}