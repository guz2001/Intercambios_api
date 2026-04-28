package com.intercambios.intercambios_api.controller;

import com.intercambios.intercambios_api.dto.RequerimientosEnergeticosResponse;
import com.intercambios.intercambios_api.model.RequerimientosEnergeticos;
import com.intercambios.intercambios_api.service.RequerimientosEnergeticosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para el cálculo del requerimiento energético diario de un paciente.
 *
 * <p>Expone el endpoint de cálculo bajo {@code /api/requerimientos}.
 * Delega toda la lógica matemática a {@link RequerimientosEnergeticosService},
 * siguiendo el principio de que los controladores sólo deben orquestar
 * peticiones y respuestas.</p>
 */
@RestController
@RequestMapping("/api/requerimientos")
public class RequerimientosEnergeticosController {

    private final RequerimientosEnergeticosService service;

    public RequerimientosEnergeticosController(RequerimientosEnergeticosService service) {
        this.service = service;
    }

    /**
     * Calcula la Tasa Metabólica Basal (TMB) y el Gasto Energético Total (GET)
     * a partir de los datos antropométricos del paciente.
     *
     * <p>{@code POST /api/requerimientos/calcular}</p>
     *
     * <p>Ejemplo de cuerpo JSON:
     * <pre>
     * {
     *   "peso": 70.0,
     *   "altura": 175.0,
     *   "edad": 30,
     *   "sexo": "MASCULINO",
     *   "factorActividad": 1.55
     * }
     * </pre>
     * </p>
     *
     * <p>Ejemplo de respuesta:
     * <pre>
     * {
     *   "tmb": 1724.28,
     *   "get": 2682.63
     * }
     * </pre>
     * </p>
     *
     * @param datos parámetros antropométricos del paciente
     * @return {@code 200 OK} con la TMB y el GET calculados en kcal/día
     */
    @PostMapping("/calcular")
    public ResponseEntity<RequerimientosEnergeticosResponse> calcular(
            @RequestBody RequerimientosEnergeticos datos) {
        return ResponseEntity.ok(service.calcularGET(datos));
    }
}
