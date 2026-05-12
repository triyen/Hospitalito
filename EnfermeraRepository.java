package com.hospitalito.hospitalito.repository;

import com.hospitalito.hospitalito.model.Enfermera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermeraRepository extends JpaRepository<Enfermera, Integer> {
}