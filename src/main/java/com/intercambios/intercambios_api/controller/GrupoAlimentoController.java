package com.intercambios.intercambios_api.controller;

import com.intercambios.intercambios_api.model.GrupoAlimento;
import com.intercambios.intercambios_api.service.GrupoAlimentoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para el catálogo de grupos alimentarios.
 *
 * <p>Expone el primer nivel de la jerarquía alimentaria bajo {@code /api/grupos}.
 * Los grupos son el punto de entrada para navegar hacia subgrupos y alimentos.</p>
 */
@RestController
@RequestMapping("/api/grupos")
public class GrupoAlimentoController {

    private final GrupoAlimentoService service;

    public GrupoAlimentoController(GrupoAlimentoService service) {
        this.service = service;
    }

    /**
     * Devuelve el catálogo completo de grupos alimentarios.
     *
     * <p>{@code GET /api/grupos}</p>
     *
     * @return lista de todos los grupos con su nombre y descripción
     */
    @GetMapping
    public List<GrupoAlimento> listarTodos() {
        return service.listarTodos();
    }
}
