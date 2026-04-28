package com.intercambios.intercambios_api.controller;

import com.intercambios.intercambios_api.dto.PacienteRequest;
import com.intercambios.intercambios_api.model.Paciente;
import com.intercambios.intercambios_api.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de pacientes.
 *
 * <p>Expone el CRUD completo de pacientes en {@code /api/pacientes}.
 * Los nutricionistas registran aquí a sus pacientes antes de asociarles
 * selecciones de intercambio o calcular su requerimiento energético.</p>
 */
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /** Lista todos los pacientes registrados. */
    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    /** Obtiene un paciente por su ID. Devuelve 404 si no existe. */
    @GetMapping("/{id}")
    public Paciente obtenerPorId(@PathVariable Integer id) {
        return pacienteService.obtenerPorId(id);
    }

    /** Crea un nuevo paciente. Devuelve 201 Created con el paciente guardado. */
    @PostMapping
    public ResponseEntity<Paciente> crear(@Valid @RequestBody PacienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crear(request));
    }

    /** Actualiza los datos de un paciente existente. */
    @PutMapping("/{id}")
    public Paciente actualizar(@PathVariable Integer id, @Valid @RequestBody PacienteRequest request) {
        return pacienteService.actualizar(id, request);
    }

    /** Elimina un paciente. Devuelve 204 No Content si se eliminó correctamente. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
