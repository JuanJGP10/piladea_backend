package com.proyecto.piladea.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.piladea.dto.CuponResponseDTO;
import com.proyecto.piladea.model.Cupon;
import com.proyecto.piladea.service.CuponService;

import lombok.Data;

@RestController
@RequestMapping("/api/cupones")
public class CuponController {

    private final CuponService cuponService;

    public CuponController(CuponService cuponService) {
        this.cuponService = cuponService;
    }

    // GET: Ver mis cupones
    // URL: http://localhost:8080/api/cupones/usuario/5
    @GetMapping("/usuario/{perfilId}")
    public ResponseEntity<List<CuponResponseDTO>> listarMisCupones(@PathVariable Long perfilId) {
        List<CuponResponseDTO> cupones = cuponService.obtenerCuponesDeUsuario(perfilId);
        if (cupones.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 si está vacío
        }
        return ResponseEntity.ok(cupones); // Devuelve 200 y la lista
    }

    

    // En el Controller
    @PostMapping("/canjear")
    public ResponseEntity<?> canjear(@RequestBody CanjeRequest request) {
        try {
            // Accedemos con request.getQr()
            Cupon cuponCanjeado = cuponService.canjearCupon(request.getQr());
            return ResponseEntity.ok("Cupón canjeado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Clase auxiliar pequeña (puedes ponerla dentro del mismo archivo o en dto)
    @Data
    class CanjeRequest {
        private String qr;
    }

}
