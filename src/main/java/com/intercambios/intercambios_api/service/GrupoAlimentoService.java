package com.intercambios.intercambios_api.service;

import com.intercambios.intercambios_api.model.GrupoAlimento;
import com.intercambios.intercambios_api.repository.GrupoAlimentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio de lógica de negocio para la entidad {@link GrupoAlimento}.
 *
 * <p>Gestiona el catálogo de grupos alimentarios que conforman el primer nivel
 * de la jerarquía del sistema de intercambios:
 * Grupo → Subgrupo → Alimento.</p>
 */
@Service
public class GrupoAlimentoService {

    private final GrupoAlimentoRepository repository;

    public GrupoAlimentoService(GrupoAlimentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Devuelve el listado completo de grupos alimentarios.
     *
     * <p>Se espera que el número de grupos sea pequeño y estable
     * (catálogo de referencia), por lo que no se requiere paginación.</p>
     *
     * @return lista de todos los grupos; vacía si no hay registros
     */
    public List<GrupoAlimento> listarTodos() {
        return repository.findAll();
    }
}
