package com.proyecto.piladea.service;

import com.proyecto.piladea.dto.PerfilResumenDTO;
import com.proyecto.piladea.model.Perfil;
import com.proyecto.piladea.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilResumenDTO obtenerPerfilResumenPorEmail(String email) {
        // Buscamos por la relación con el usuario
        Perfil perfil = perfilRepository.findByUsuarioEmail(email);
        
        // Protección contra NullPointerException si el registro falló
        if (perfil == null) {
            return PerfilResumenDTO.builder()
                    .id(-1L)
                    .nombre("Usuario (Sin Perfil)")
                    .bicicoins(0)
                    .build();
        }

        return PerfilResumenDTO.builder()
                .id(perfil.getId())
                .nombre(perfil.getNombre())
                .rutaImagen(perfil.getRutaImagen())
                .bicicoins(perfil.getBicicoins())
                .distanciaTotal(perfil.getDistanciaTotal())
                .build();
    }
}