package com.proyecto.piladea.service;

import com.proyecto.piladea.repository.PerfilRepository;

public class PerfilService {
    
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    
}
