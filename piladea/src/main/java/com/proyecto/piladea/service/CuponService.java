package com.proyecto.piladea.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.piladea.dto.CuponDTO;
import com.proyecto.piladea.model.Cupon;
import com.proyecto.piladea.repository.CuponRepository;

import jakarta.transaction.Transactional;

@Service
public class CuponService {

    private final CuponRepository cuponRepository;

    public CuponService(CuponRepository cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    // // Lógica 1: Obtener mis cupones
    // public List<CuponDTO> obtenerCuponesDeUsuario(Long perfilId) {
    //     List<Cupon> cupones = cuponRepository.findByPerfilId(perfilId);

    //     // Convertimos la lista de Entidades a lista de DTOs
    //     return cupones.stream().map(c -> CuponResponseDTO.builder()
    //             .id(c.getId())
    //             .urlQR(c.getUrlQR())
    //             .canjeado(c.isCanjeado())
    //             .nombrePremio(c.getPremio().getPromocion())
    //             // Navegamos seguro: Cupon -> Premio -> Establecimiento -> Nombre
    //             .nombreTienda(c.getPremio().getEstablecimiento().getNombreEstablecimiento())
    //             .fecha(c.getFechaCreacion())
    //             .build()).toList();
    // }

    // Lógica 2: Canjear un cupón (Escenario real de negocio)
    // Usamos @Transactional: si algo falla, se deshacen todos los cambios de esta
    // función
    @Transactional
    public Cupon canjearCupon(String qrCode) {
        // A. Buscar el cupón
        Cupon cupon = cuponRepository.findByUrlQR(qrCode)
                .orElseThrow(() -> new RuntimeException("Cupón no encontrado"));

        // B. Validaciones de negocio
        if (cupon.isCanjeado()) {
            throw new RuntimeException("Este cupón ya fue utilizado.");
        }

        // C. Aplicar cambio
        cupon.setCanjeado(true);

        // D. Guardar y devolver
        return cuponRepository.save(cupon);
    }
}
