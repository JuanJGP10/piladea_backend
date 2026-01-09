package com.proyecto.piladea.dto;

import com.proyecto.piladea.model.Rol;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Rol rol; // ESTANDAR o ESTABLECIMIENTO
    
    // Datos para Perfil Estandar
    private String nombre;
    private String apellido;
    
    // Datos para Perfil Establecimiento
    private String nombreComercial;
    private String direccion;
    private String telefono;
}