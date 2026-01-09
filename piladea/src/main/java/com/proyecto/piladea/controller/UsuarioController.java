package com.proyecto.piladea.controller;

import com.proyecto.piladea.service.UsuarioService;

public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
