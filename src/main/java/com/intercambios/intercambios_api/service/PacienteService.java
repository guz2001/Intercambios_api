package com.intercambios.intercambios_api.service;

import com.intercambios.intercambios_api.dto.PacienteRequest;
import com.intercambios.intercambios_api.exception.ResourceNotFoundException;
import com.intercambios.intercambios_api.model.Paciente;
import com.intercambios.intercambios_api.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio con la lógica de negocio para la gestión de pacientes.
 *
 * <p>Un nutricionista registra un paciente con sus datos antropométricos
 * (peso, talla, edad, sexo, factor de actividad) para poder calcular
 * su requerimiento energético y asociarle selecciones de intercambio.</p>
 */
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Paciente obtenerPorId(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado con ID: " + id));
    }

    @Transactional
    public Paciente crear(PacienteRequest req) {
        Paciente paciente = construirDesdeRequest(new Paciente(), req);
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public Paciente actualizar(Integer id, PacienteRequest req) {
        Paciente existente = obtenerPorId(id);
        return pacienteRepository.save(construirDesdeRequest(existente, req));
    }

    @Transactional
    public void eliminar(Integer id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    private Paciente construirDesdeRequest(Paciente paciente, PacienteRequest req) {
        paciente.setNombre(req.getNombre());
        paciente.setEdad(req.getEdad());
        paciente.setPeso(req.getPeso());
        paciente.setAltura(req.getAltura());
        paciente.setSexo(req.getSexo());
        paciente.setFactorActividad(req.getFactorActividad());
        return paciente;
    }
}
