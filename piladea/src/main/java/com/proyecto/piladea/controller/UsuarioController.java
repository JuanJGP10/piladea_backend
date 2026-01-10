package com.proyecto.piladea.controller;

import org.springframework.web.bind.annotation.RestController;

import com.proyecto.piladea.service.UsuarioService;

@RestController
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
