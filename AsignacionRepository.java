package com.hospitalito.hospitalito.repository;

import com.hospitalito.hospitalito.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Integer> {
    List<Asignacion> findByEnfermeraIdEnfermera(Integer idEnfermera);
    long countByCamaIdCama(Integer idCama);
    boolean existsByCamaIdCamaAndEnfermeraIdEnfermera(Integer idCama, Integer idEnfermera);
}