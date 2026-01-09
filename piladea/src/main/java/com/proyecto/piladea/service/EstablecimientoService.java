package com.proyecto.piladea.service;

import com.proyecto.piladea.repository.EstablecimientoRepository;

public class EstablecimientoService {
    
    private final EstablecimientoRepository establecimientoRepository;

    public EstablecimientoService(EstablecimientoRepository establecimientoRepository) {
        this.establecimientoRepository = establecimientoRepository;
    }

    
}
