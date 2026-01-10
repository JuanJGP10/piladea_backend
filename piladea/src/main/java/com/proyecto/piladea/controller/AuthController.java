package com.proyecto.piladea.controller;

import com.proyecto.piladea.dto.AuthResponse;
import com.proyecto.piladea.dto.LoginRequest;
import com.proyecto.piladea.dto.PerfilResumenDTO;
import com.proyecto.piladea.dto.RegisterRequest;
import com.proyecto.piladea.service.AuthService;
import com.proyecto.piladea.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PerfilService perfilService;

    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegisterRequest request) {
        try {
            authService.registrar(request);
            return ResponseEntity.ok(Collections.singletonMap("message", "Usuario registrado exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<PerfilResumenDTO> obtenerMiPerfilResumen(Authentication authentication) {
        return ResponseEntity.ok(perfilService.obtenerPerfilResumenPorEmail(authentication.getName()));
    }
}