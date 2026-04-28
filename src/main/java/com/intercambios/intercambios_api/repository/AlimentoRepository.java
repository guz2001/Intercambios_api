package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad {@link Alimento}.
 *
 * <p>Extiende {@link JpaRepository} heredando operaciones CRUD estándar
 * ({@code findAll}, {@code findById}, {@code save}, {@code deleteById}, etc.)
 * y agrega consultas específicas del dominio de intercambios.</p>
 */
@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {

    /**
     * Devuelve todos los alimentos que pertenecen a un subgrupo específico.
     *
     * <p>Spring Data deriva la consulta SQL automáticamente del nombre del método
     * mediante la convención {@code findBy[Campo]}, navegando la relación
     * {@code alimento.subgrupo.id}.</p>
     *
     * @param subgrupoId ID del subgrupo alimentario a filtrar
     * @return lista de alimentos del subgrupo; vacía si no existe ninguno
     */
    List<Alimento> findBySubgrupoId(Integer subgrupoId);

    /**
     * Devuelve los alimentos que pueden intercambiarse con el alimento dado.
     *
     * <p>Dos alimentos son intercambiables cuando pertenecen al mismo
     * {@code SubgrupoAlimento}, ya que eso garantiza equivalencia calórica
     * por porción. La subconsulta JPQL obtiene el subgrupo del alimento
     * de referencia y filtra los demás alimentos de ese mismo subgrupo.</p>
     *
     * @param alimentoId ID del alimento de referencia (excluido del resultado)
     * @return lista de alimentos intercambiables; vacía si el alimento no existe
     *         o si es el único en su subgrupo
     */
    @Query("SELECT a FROM Alimento a WHERE a.subgrupo.id = " +
            "(SELECT a2.subgrupo.id FROM Alimento a2 WHERE a2.id = :alimentoId) " +
            "AND a.id != :alimentoId")
    List<Alimento> findIntercambios(@Param("alimentoId") Integer alimentoId);
}
