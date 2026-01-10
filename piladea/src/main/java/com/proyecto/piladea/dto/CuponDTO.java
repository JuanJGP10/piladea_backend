package com.proyecto.piladea.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CuponDTO {
    private Long id;
    private String urlQR;
    private boolean canjeado;
    private String nombrePremio;
    private String nombreTienda; // Solo el nombre, no todo el objeto tienda
}
