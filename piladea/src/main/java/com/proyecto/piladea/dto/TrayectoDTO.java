package com.proyecto.piladea.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrayectoDTO {
    private Long id;
    private Double origenLatitud;
    private Double origenLongitud;
    private Double destinoLatitud;
    private Double destinoLongitud;
    private Double distancia;
}
