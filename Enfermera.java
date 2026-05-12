// java
package com.hospitalito.hospitalito.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Enfermeras")
@Getter
@Setter
public class Enfermera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnfermera;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(unique = true, nullable = false, length = 20)
    private String dni;

    @Column(unique = true, nullable = false, length = 50)
    private String matricula;

    @OneToMany(mappedBy = "enfermera")
    private List<Asignacion> asignaciones;
}
