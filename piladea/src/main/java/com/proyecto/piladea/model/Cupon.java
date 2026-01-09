package com.proyecto.piladea.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cupones")
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    @Column(nullable = false, length = 255)
    private String urlQR;

    @ManyToOne
    @JoinColumn(name = "premio_id", nullable = false)
    private Premio premio;
    
    // SOLUCIÓN B: Eliminada la redundancia de PerfilEstablecimiento.
    // La propiedad del cupón se deriva del Premio y su Establecimiento.

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil; // El usuario que canjea el cupón

    @Column(nullable = false)
    private boolean canjeado = false;

    @Column(nullable = false)
    private LocalDate fechaCreacion;
}