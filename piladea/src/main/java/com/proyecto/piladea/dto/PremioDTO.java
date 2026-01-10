package com.proyecto.piladea.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PremioDTO {

    private Long id;
    private int bicicoins;
    private String promocion;
    private String categoria;
    private String nombreEstablecimiento;

}
