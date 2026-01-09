package com.proyecto.piladea.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name = "trayectos")
public class Trayecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // SOLUCIÓN C: Coordenadas numéricas
    // Corregido: "origenDistancia" probablemente debía ser "origenLongitud"
    @Column(nullable = false)
    private Double origenLatitud;

    @Column(nullable = false)
    private Double origenLongitud;

    @Column(nullable = false)
    private Double destinoLatitud;

    @Column(nullable = false)
    private Double destinoLongitud;

    @Column(nullable = false)
    private Double distancia; // La distancia calculada en KM o Metros

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @ManyToMany(mappedBy = "trayectosGuardados", cascade = CascadeType.ALL)
    private Set<Perfil> perfiles;

    @ManyToOne
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;
}
