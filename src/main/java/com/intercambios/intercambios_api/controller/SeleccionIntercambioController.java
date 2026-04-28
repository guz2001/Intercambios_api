package com.intercambios.intercambios_api.controller;

import com.intercambios.intercambios_api.dto.SeleccionIntercambioRequest;
import com.intercambios.intercambios_api.model.SeleccionIntercambio;
import com.intercambios.intercambios_api.service.SeleccionIntercambioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para registrar y consultar las selecciones de intercambio
 * alimentario realizadas por los pacientes.
 *
 * <p>Expone operaciones CRUD bajo {@code /api/selecciones}. Las validaciones
 * de negocio (existencia de alimentos, equivalencia de subgrupo) se delegan
 * completamente a {@link SeleccionIntercambioService}.</p>
 *
 * <p><b>MEJORA:</b> Centralizar el manejo de errores en una clase anotada con
 * {@code @ControllerAdvice} para evitar el bloque {@code try/catch} en cada
 * controlador y retornar respuestas de error con formato JSON consistente
 * en toda la API.</p>
 */
@RestController
@RequestMapping("/api/selecciones")
public class SeleccionIntercambioController {

    private final SeleccionIntercambioService service;

    public SeleccionIntercambioController(SeleccionIntercambioService service) {
        this.service = service;
    }

    /**
     * Registra una nueva selección de intercambio alimentario.
     *
     * <p>{@code POST /api/selecciones}</p>
     *
     * @param request DTO con los IDs del alimento origen, reemplazo y paciente (opcional)
     * @return {@code 200 OK} con la selección persistida,
     *         {@code 400 Bad Request} con mensaje de error si la validación falla
     */
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody SeleccionIntercambioRequest request) {
        try {
            SeleccionIntercambio guardada = service.guardar(request);
            return ResponseEntity.ok(guardada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Devuelve el historial completo de selecciones de intercambio.
     *
     * <p>{@code GET /api/selecciones}</p>
     *
     * @return lista de todas las selecciones registradas en el sistema
     */
    @GetMapping
    public List<SeleccionIntercambio> listarTodas() {
        return service.listarTodas();
    }

    /**
     * Devuelve el historial de intercambios seleccionados por un paciente específico.
     *
     * <p>{@code GET /api/selecciones/paciente/{pacienteId}}</p>
     *
     * @param pacienteId ID del paciente
     * @return lista de selecciones del paciente; vacía si no tiene registros
     */
    @GetMapping("/paciente/{pacienteId}")
    public List<SeleccionIntercambio> listarPorPaciente(@PathVariable Integer pacienteId) {
        return service.listarPorPaciente(pacienteId);
    }

    /**
     * Elimina una selección de intercambio por su ID.
     *
     * <p>{@code DELETE /api/selecciones/{id}}</p>
     *
     * @param id ID de la selección a eliminar
     * @return {@code 204 No Content} si se eliminó correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
