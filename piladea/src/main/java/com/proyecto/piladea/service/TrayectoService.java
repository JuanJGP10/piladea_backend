package com.proyecto.piladea.service;

import com.proyecto.piladea.repository.TrayectoRepository;

public class TrayectoService {
    
    private final TrayectoRepository trayectoRepository;

    public TrayectoService(TrayectoRepository trayectoRepository) {
        this.trayectoRepository = trayectoRepository;
    }

    
}
