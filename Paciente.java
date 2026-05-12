package com.hospitalito.hospitalito.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "Pacientes")
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHC; // Id de Historia Clínica

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(unique = true, nullable = false, length = 20)
    private String dni;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(length = 100) // nullable = true por defecto
    private String obraSocial;

    @OneToOne(mappedBy = "paciente")
    private Cama cama;
}