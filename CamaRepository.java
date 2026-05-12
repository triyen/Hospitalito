package com.hospitalito.hospitalito.repository;

import com.hospitalito.hospitalito.model.Cama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamaRepository extends JpaRepository<Cama, Integer> {

}