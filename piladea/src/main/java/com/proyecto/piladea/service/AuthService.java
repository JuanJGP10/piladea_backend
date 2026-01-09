package com.proyecto.piladea.service;

import com.proyecto.piladea.dto.AuthResponse;
import com.proyecto.piladea.dto.LoginRequest;
import com.proyecto.piladea.dto.RegisterRequest;
import com.proyecto.piladea.model.*;
import com.proyecto.piladea.repository.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AuthService {

    private UsuarioRepository usuarioRepository;
    private PerfilRepository perfilRepository;
    private PerfilEstablecimientoRepository perfilEstablecimientoRepository;
    private PasswordEncoder passwordEncoder;
    private PerfilAdminRepository perfilAdminRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository, PerfilRepository perfilRepository, PerfilEstablecimientoRepository perfilEstablecimientoRepository, PasswordEncoder passwordEncoder, PerfilAdminRepository perfilAdminRepository, JwtService jwtService, AuthenticationManager authenticationManager){
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.perfilEstablecimientoRepository = perfilEstablecimientoRepository;
        this.passwordEncoder = passwordEncoder;
        this.perfilAdminRepository = perfilAdminRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }   

    public AuthResponse login(LoginRequest request) {
        // 1. Esto hace la magia: valida email y password automáticamente.
        // Si la contraseña es incorrecta, lanza una excepción.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. Si pasamos la línea anterior, el usuario es correcto. Lo buscamos.
        Usuario user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow();
        
        // 3. Generamos el token
        String jwtToken = jwtService.generateToken(user);

        // 4. Devolvemos el token
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Transactional
    public void registrar(RegisterRequest request) {
        // 1. Validar si el email ya existe
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // 2. Crear el Usuario base (Cuenta de acceso)
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        // IMPORTANTE: Nunca guardar pass en texto plano. Se encripta aquí.
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(request.getRol());
        usuario.setActivo(true);
        
        usuario = usuarioRepository.save(usuario); // Guardamos para tener ID

        // 3. Crear el Perfil específico según el Rol
        if (request.getRol() == Rol.ESTANDAR) {
            Perfil perfil = new Perfil();
            perfil.setUsuario(usuario); // Vinculación OneToOne
            perfil.setNombre(request.getNombre());
            perfil.setApellido(request.getApellido());
            perfil.setFechaCreacion(LocalDate.now());
            perfil.setBicicoins(0);
            perfilRepository.save(perfil);

        } else if (request.getRol() == Rol.ESTABLECIMIENTO) {
            PerfilEstablecimiento tienda = new PerfilEstablecimiento();
            tienda.setUsuario(usuario); // Vinculación OneToOne
            tienda.setNombreComercial(request.getNombreComercial());
            tienda.setDireccion(request.getDireccion());
            tienda.setTelefono(request.getTelefono());
            perfilEstablecimientoRepository.save(tienda);
        } else {
            PerfilAdmin admin = new PerfilAdmin();
            admin.setUsuario(usuario);
            perfilAdminRepository.save(admin);
        }
    }
}