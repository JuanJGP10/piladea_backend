package com.proyecto.piladea.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerfilResumenDTO {
    private Long id;
    private String nombre;
    private String rutaImagen;
    private Integer bicicoins;
    private Integer distanciaTotal;
}
