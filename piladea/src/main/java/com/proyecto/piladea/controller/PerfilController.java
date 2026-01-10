package com.proyecto.piladea.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.proyecto.piladea.dto.PerfilResumenDTO;
import com.proyecto.piladea.service.PerfilService;

@RestController
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    

}
