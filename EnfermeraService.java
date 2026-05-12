package com.hospitalito.hospitalito.service;
import com.hospitalito.hospitalito.model.Asignacion;
import com.hospitalito.hospitalito.model.Cama;
import com.hospitalito.hospitalito.model.Enfermera;
import com.hospitalito.hospitalito.repository.AsignacionRepository;
import com.hospitalito.hospitalito.repository.EnfermeraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnfermeraService {

    @Autowired
    private EnfermeraRepository enfermeraRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    public List<Enfermera> obtenerTodasLasEnfermeras() {
        return enfermeraRepository.findAll();
    }

    public Enfermera agregarEnfermera(Enfermera enfermera) {

        return enfermeraRepository.save(enfermera);
    }

    public List<Cama> obtenerCamasPorEnfermera(Integer idEnfermera) {
        List<Asignacion> asignaciones = asignacionRepository.findByEnfermeraIdEnfermera(idEnfermera);


        return asignaciones.stream()
                .map(Asignacion::getCama)
                .distinct()
                .collect(Collectors.toList());
    }

    // Método de ayuda para otros servicios
    public Enfermera obtenerEnfermeraPorId(Integer id) {
        return enfermeraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enfermera no encontrada con id: " + id));
    }
}