package com.intercambios.intercambios_api.controller;

import com.intercambios.intercambios_api.model.SubgrupoAlimento;
import com.intercambios.intercambios_api.service.SubgrupoAlimentoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para el catálogo de subgrupos alimentarios.
 *
 * <p>Expone el segundo nivel de la jerarquía alimentaria bajo {@code /api/subgrupos}.
 * Soporta la consulta global y la filtrada por grupo padre para
 * construir selectores dependientes en el frontend.</p>
 */
@RestController
@RequestMapping("/api/subgrupos")
public class SubgrupoAlimentoController {

    private final SubgrupoAlimentoService service;

    public SubgrupoAlimentoController(SubgrupoAlimentoService service) {
        this.service = service;
    }

    /**
     * Devuelve el catálogo completo de subgrupos alimentarios.
     *
     * <p>{@code GET /api/subgrupos}</p>
     *
     * @return lista de todos los subgrupos con su grupo padre anidado
     */
    @GetMapping
    public List<SubgrupoAlimento> listarTodos() {
        return service.listarTodos();
    }

    /**
     * Devuelve los subgrupos que pertenecen a un grupo alimentario específico.
     *
     * <p>{@code GET /api/subgrupos/por-grupo/{grupoId}}</p>
     *
     * <p>Usado para cargar dinámicamente las opciones del segundo selector
     * cuando el usuario elige un grupo en el frontend.</p>
     *
     * @param grupoId ID del grupo padre
     * @return lista de subgrupos del grupo; vacía si el grupo no tiene subgrupos
     */
    @GetMapping("/por-grupo/{grupoId}")
    public List<SubgrupoAlimento> listarPorGrupo(@PathVariable Integer grupoId) {
        return service.listarPorGrupo(grupoId);
    }
}
