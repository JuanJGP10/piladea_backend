package com.proyecto.piladea.service;

import com.proyecto.piladea.repository.PremioRespository;

public class PremioService {
    
    private final PremioRespository premioRespository;

    public PremioService(PremioRespository premioRespository) {
        this.premioRespository = premioRespository;
    }

    
}
