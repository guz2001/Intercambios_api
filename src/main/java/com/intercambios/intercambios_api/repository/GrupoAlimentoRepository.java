package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.GrupoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a datos para la entidad {@link GrupoAlimento}.
 *
 * <p>Hereda las operaciones CRUD estándar de {@link JpaRepository}.
 * No requiere consultas personalizadas porque los grupos se consultan
 * siempre de forma completa ({@code findAll}).</p>
 *
 * <p><b>MEJORA:</b> Si el catálogo de grupos crece, agregar paginación
 * usando {@code Page<GrupoAlimento> findAll(Pageable pageable)} para
 * evitar cargar toda la tabla en memoria en una sola petición.</p>
 */
@Repository
public interface GrupoAlimentoRepository extends JpaRepository<GrupoAlimento, Integer> {
}
