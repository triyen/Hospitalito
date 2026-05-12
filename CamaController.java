package com.hospitalito.hospitalito.controller;

import com.hospitalito.hospitalito.model.Cama;
import com.hospitalito.hospitalito.service.CamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/camas") // La URL base para todo lo relacionado con camas
public class CamaController {

    @Autowired
    private CamaService camaService;

    @GetMapping
    public List<Cama> getAllCamas() {
        return camaService.obtenerTodasLasCamas();
    }

    @PostMapping
    public Cama createCama(@RequestBody Cama cama) {
        return camaService.agregarCama(cama);
    }

    @PutMapping("/{idCama}/asignar/{idPaciente}")
    public ResponseEntity<Cama> asignarCama(@PathVariable Integer idCama, @PathVariable Integer idPaciente) {
        try {
            Cama camaAsignada = camaService.asignarPaciente(idCama, idPaciente);
            return ResponseEntity.ok(camaAsignada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Manejo simple de errores
        }
    }
}