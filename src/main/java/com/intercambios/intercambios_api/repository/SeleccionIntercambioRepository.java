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
     * Devuelve todas las selecciones de intercambio registradas para un paciente.
     *
     * <p>Permite construir el historial de sustituciones alimentarias de
     * una consulta o plan nutricional.</p>
     *
     * @param pacienteId ID del paciente
     * @return lista de selecciones del paciente ordenadas por defecto por ID;
     *         vacía si el paciente no tiene selecciones registradas
     *
     * <p><b>MEJORA:</b> Agregar {@code OrderBy} para ordenar por fecha descendente
     * y mostrar siempre las selecciones más recientes primero:
     * {@code findByPacienteIdOrderByFechaSeleccionDesc}.</p>
     */
    List<SeleccionIntercambio> findByPacienteId(Integer pacienteId);
}
