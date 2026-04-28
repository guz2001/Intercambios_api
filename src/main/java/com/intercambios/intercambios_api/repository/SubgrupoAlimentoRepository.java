package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.SubgrupoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad {@link SubgrupoAlimento}.
 *
 * <p>Extiende {@link JpaRepository} y agrega la consulta derivada
 * {@link #findByGrupoId} para filtrar subgrupos por su grupo padre,
 * soportando la navegación jerárquica grupo → subgrupo → alimento.</p>
 */
@Repository
public interface SubgrupoAlimentoRepository extends JpaRepository<SubgrupoAlimento, Integer> {

    /**
     * Devuelve todos los subgrupos que pertenecen a un grupo alimentario.
     *
     * @param grupoId ID del grupo padre
     * @return lista de subgrupos del grupo; vacía si el grupo no existe o no tiene subgrupos
     */
    List<SubgrupoAlimento> findByGrupoId(Integer grupoId);
}
