package com.proyecto.piladea.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "establecimientos")
public class Establecimiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_establecimiento", nullable = false)
    private String nombreEstablecimiento;

    private String urlImagen;

    // SOLUCIÓN C: Tipos de datos numéricos para coordenadas
    private Double latitud;
    private Double longitud;

    // SOLUCIÓN A: Relación con el dueño
    @ManyToOne
    @JoinColumn(name = "perfil_establecimiento_id", nullable = false)
    private PerfilEstablecimiento propietario;

    @OneToMany(mappedBy = "establecimiento")
    @ToString.Exclude
    private Set<Premio> premios;
    
    @ManyToMany
    @JoinTable(
        name = "establecimiento_premio",
        joinColumns = @JoinColumn(name = "establecimiento_id"),
        inverseJoinColumns = @JoinColumn(name = "premio_id")
    )
    @ToString.Exclude
    private Set<Premio> premiosDisponibles;
    
    private LocalDate fechaCreacion;
}