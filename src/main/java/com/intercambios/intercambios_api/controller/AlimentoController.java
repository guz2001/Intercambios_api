package com.intercambios.intercambios_api.controller;

import com.intercambios.intercambios_api.model.Alimento;
import com.intercambios.intercambios_api.service.AlimentoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para la gestión del catálogo de alimentos.
 *
 * <p>Expone endpoints de consulta bajo el prefijo {@code /api/alimentos}.
 * No realiza operaciones de escritura porque el catálogo es mantenido
 * directamente en la base de datos por el administrador del sistema.</p>
 *
 * <p>La lógica de negocio (validaciones, reglas de intercambiabilidad)
 * reside en {@link AlimentoService}, no en este controlador.</p>
 */
@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    private final AlimentoService service;

    public AlimentoController(AlimentoService service) {
        this.service = service;
    }

    /**
     * Devuelve el catálogo completo de alimentos.
     *
     * <p>{@code GET /api/alimentos}</p>
     *
     * @return lista de todos los alimentos con su subgrupo anidado
     */
    @GetMapping
    public List<Alimento> listarTodos() {
        return service.listarTodos();
    }

    /**
     * Devuelve los alimentos que pertenecen a un subgrupo específico.
     *
     * <p>{@code GET /api/alimentos/por-subgrupo/{subgrupoId}}</p>
     *
     * @param subgrupoId ID del subgrupo a filtrar
     * @return lista de alimentos del subgrupo; vacía si no hay ninguno
     */
    @GetMapping("/por-subgrupo/{subgrupoId}")
    public List<Alimento> listarPorSubgrupo(@PathVariable Integer subgrupoId) {
        return service.listarPorSubgrupo(subgrupoId);
    }

    /**
     * Devuelve los alimentos que pueden reemplazar al alimento dado.
     *
     * <p>{@code GET /api/alimentos/{id}/intercambios}</p>
     *
     * <p>Un alimento es intercambiable si pertenece al mismo subgrupo
     * alimentario, garantizando equivalencia calórica por porción.</p>
     *
     * @param id ID del alimento de referencia
     * @return lista de alimentos intercambiables (excluye al alimento de referencia)
     */
    @GetMapping("/{id}/intercambios")
    public List<Alimento> buscarIntercambios(@PathVariable Integer id) {
        return service.buscarIntercambios(id);
    }
}
