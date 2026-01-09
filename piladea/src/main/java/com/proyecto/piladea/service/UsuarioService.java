package com.proyecto.piladea.service;

import com.proyecto.piladea.repository.UsuarioRepository;

public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    
}
