package com.proyecto.piladea.controller;

import com.proyecto.piladea.dto.AuthResponse;
import com.proyecto.piladea.dto.LoginRequest;
import com.proyecto.piladea.dto.RegisterRequest;
import com.proyecto.piladea.service.AuthService;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

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
        AuthResponse token = authService.login(request);
        return ResponseEntity.ok(token);
    }
}