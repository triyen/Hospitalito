package com.hospitalito.hospitalito.service;

import com.hospitalito.hospitalito.repository.AsignacionRepository;
import com.hospitalito.hospitalito.model.Asignacion;
import com.hospitalito.hospitalito.model.Cama;
import com.hospitalito.hospitalito.model.Enfermera;
import com.hospitalito.hospitalito.service.CamaService;
import com.hospitalito.hospitalito.service.EnfermeraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Autowired
    private CamaService camaService;

    @Autowired
    private EnfermeraService enfermeraService;

    public Asignacion asociarEnfermeraACama(Integer idCama, Integer idEnfermera) {
        Cama cama = camaService.obtenerCamaPorId(idCama);
        Enfermera enfermera = enfermeraService.obtenerEnfermeraPorId(idEnfermera);


        boolean yaExiste = asignacionRepository.existsByCamaIdCamaAndEnfermeraIdEnfermera(idCama, idEnfermera);
        if (yaExiste) {
            throw new RuntimeException("La enfermera " + enfermera.getApellido() + " ya está asignada a la cama " + idCama);
        }

        long enfermerasActuales = asignacionRepository.countByCamaIdCama(idCama);
        int maxEnfermeras = cama.getCategoria().getMaxCantEnfermeras();

        if (enfermerasActuales >= maxEnfermeras) {
            throw new RuntimeException("La cama " + idCama + " (Categoría: " + cama.getCategoria().getDescripcion()
                    + ") ya alcanzó su límite de " + maxEnfermeras + " enfermeras.");
        }



        Asignacion nuevaAsignacion = new Asignacion();
        nuevaAsignacion.setCama(cama);
        nuevaAsignacion.setEnfermera(enfermera);

        return asignacionRepository.save(nuevaAsignacion);
    }
}