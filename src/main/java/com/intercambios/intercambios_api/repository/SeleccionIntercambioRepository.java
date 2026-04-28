package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.SeleccionIntercambio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad {@link SeleccionIntercambio}.
 *
 * <p>Gestiona la persistencia del historial de intercambios alimentarios
 * seleccionados por los pacientes.</p>
 */
public interface SeleccionIntercambioRepository extends JpaRepository<SeleccionIntercambio, Integer> {

    /**
     * Devuelve las selecciones de un paciente ordenadas de más reciente a más antigua.
     *
     * <p>El sufijo {@code OrderByFechaSeleccionDesc} en el nombre del método instruye
     * a Spring Data JPA para agregar automáticamente {@code ORDER BY fecha_seleccion DESC}
     * a la consulta SQL generada, sin necesidad de escribir una query explícita.</p>
     *
     * @param pacienteId ID del paciente
     * @return lista de selecciones del paciente, las más recientes primero;
     *         vacía si el paciente no tiene selecciones registradas
     */
    List<SeleccionIntercambio> findByPacienteIdOrderByFechaSeleccionDesc(Integer pacienteId);
}
