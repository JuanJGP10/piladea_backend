package com.proyecto.piladea.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class CuponResponseDTO {
    private Long id;
    private String urlQR;
    private boolean canjeado;
    private String nombrePremio;
    private String nombreTienda; // Solo el nombre, no todo el objeto tienda
    private LocalDate fecha;
}