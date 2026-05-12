package com.hospitalito.hospitalito.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Camas")
@Getter
@Setter
public class Cama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCama;

    @ManyToOne
    @JoinColumn(name = "IdCategoria", nullable = false)
    private Categoria categoria;

    @Column(nullable = false, length = 100)
    private String ubicacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCama estado;

    @OneToOne
    @JoinColumn(name = "IdHC", referencedColumnName = "IdHC")
    private Paciente paciente;

    @OneToMany(mappedBy = "cama")
    private List<Asignacion> asignaciones;

    public enum EstadoCama {
        Libre, Ocupada
    }
}