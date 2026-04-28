package com.intercambios.intercambios_api.controller;

import com.intercambios.intercambios_api.dto.SeleccionIntercambioRequest;
import com.intercambios.intercambios_api.model.SeleccionIntercambio;
import com.intercambios.intercambios_api.service.SeleccionIntercambioService;
import jakarta.validation.Valid;
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
 * <p>El manejo de errores ({@code IllegalArgumentException} y errores de
 * validación {@code @Valid}) está centralizado en
 * {@code GlobalExceptionHandler}, eliminando la necesidad de bloques
 * {@code try/catch} en este controlador.</p>
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
     * <p>{@code @Valid} activa la validación de las anotaciones {@code @NotNull}
     * del DTO antes de que el cuerpo llegue al servicio. Si la validación falla,
     * {@code GlobalExceptionHandler} responde con {@code 400 Bad Request}.</p>
     *
     * @param request DTO con los IDs del alimento origen, reemplazo y paciente (opcional)
     * @return {@code 200 OK} con la selección persistida
     */
    @PostMapping
    public ResponseEntity<SeleccionIntercambio> guardar(@Valid @RequestBody SeleccionIntercambioRequest request) {
        return ResponseEntity.ok(service.guardar(request));
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
     * Devuelve el historial de intercambios de un paciente específico,
     * ordenado de más reciente a más antiguo.
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
