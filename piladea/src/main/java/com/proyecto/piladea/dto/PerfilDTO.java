package com.proyecto.piladea.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerfilDTO {
    private Long id;
    private String nombre;
    private String rutaIMagen;
    private Integer bicicoins;
    private Integer distanciaTotal;
    private List<TrayectoDTO> trayectosGuardados;
    private List<CuponDTO> cupones;
    private List<PremioDTO> premios;
}
