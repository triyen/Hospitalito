// java
package com.hospitalito.hospitalito.service;

import com.hospitalito.hospitalito.model.Cama;
import com.hospitalito.hospitalito.model.Paciente;
import com.hospitalito.hospitalito.model.Categoria;
import com.hospitalito.hospitalito.repository.CamaRepository;
import com.hospitalito.hospitalito.service.PacienteService;
import com.hospitalito.hospitalito.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CamaService {

    @Autowired
    private CamaRepository camaRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private CategoriaService categoriaService;

    public List<Cama> obtenerTodasLasCamas() {
        return camaRepository.findAll();
    }

    // Firma modificada para recibir solo el objeto Cama (coincide con el controlador)
    public Cama agregarCama(Cama cama) {
        Integer idCategoria = null;
        if (cama.getCategoria() != null) {
            idCategoria = cama.getCategoria().getIdCategoria();
        }
        if (idCategoria == null) {
            throw new RuntimeException("Se requiere idCategoria en la propiedad categoria de la cama.");
        }
        Categoria categoria = categoriaService.obtenerCategoriaPorId(idCategoria);
        cama.setCategoria(categoria);
        cama.setEstado(Cama.EstadoCama.Libre);
        return camaRepository.save(cama);
    }

    public Cama asignarPaciente(Integer idCama, Integer idPaciente) {

        Cama cama = this.obtenerCamaPorId(idCama);
        Paciente paciente = pacienteService.obtenerPacientePorId(idPaciente);

        if (cama.getEstado() != Cama.EstadoCama.Libre) {
            throw new RuntimeException("La cama " + idCama + " ya está ocupada por el paciente " + cama.getPaciente().getApellido());
        }

        cama.setPaciente(paciente);
        cama.setEstado(Cama.EstadoCama.Ocupada);

        return camaRepository.save(cama);
    }

    public Cama obtenerCamaPorId(Integer id) {
        return camaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cama no encontrada con id: " + id));
    }

    public Cama liberarCama(Integer idCama) {
        Cama cama = this.obtenerCamaPorId(idCama);
        if (cama.getEstado() == Cama.EstadoCama.Libre) {
            throw new RuntimeException("La cama " + idCama + " ya se encuentra libre.");
        }

        cama.setPaciente(null);
        cama.setEstado(Cama.EstadoCama.Libre);
        return camaRepository.save(cama);
    }
}
