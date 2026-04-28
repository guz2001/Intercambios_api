package com.intercambios.intercambios_api.service;

import com.intercambios.intercambios_api.model.SubgrupoAlimento;
import com.intercambios.intercambios_api.repository.SubgrupoAlimentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio de lógica de negocio para la entidad {@link SubgrupoAlimento}.
 *
 * <p>Los subgrupos son el nivel intermedio de la jerarquía alimentaria
 * (Grupo → <b>Subgrupo</b> → Alimento) y definen qué alimentos son
 * intercambiables entre sí. Este servicio soporta tanto la consulta
 * global como la filtrada por grupo padre.</p>
 */
@Service
public class SubgrupoAlimentoService {

    private final SubgrupoAlimentoRepository repository;

    public SubgrupoAlimentoService(SubgrupoAlimentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Devuelve el listado completo de subgrupos alimentarios.
     *
     * @return lista de todos los subgrupos; vacía si no hay registros
     */
    public List<SubgrupoAlimento> listarTodos() {
        return repository.findAll();
    }

    /**
     * Devuelve los subgrupos que pertenecen a un grupo alimentario específico.
     *
     * <p>Usado por el frontend para cargar los subgrupos de forma dinámica
     * después de que el usuario selecciona un grupo.</p>
     *
     * @param grupoId ID del grupo padre
     * @return lista de subgrupos del grupo; vacía si no existe o no tiene subgrupos
     */
    public List<SubgrupoAlimento> listarPorGrupo(Integer grupoId) {
        return repository.findByGrupoId(grupoId);
    }
}
