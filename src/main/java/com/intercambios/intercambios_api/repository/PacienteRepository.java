package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para la entidad {@link Paciente}.
 *
 * <p>Actualmente sólo se usa para validar la existencia del paciente
 * al registrar una {@code SeleccionIntercambio}. Cuando se implemente
 * el CRUD completo de pacientes, este repositorio ya estará disponible.</p>
 *
 * <p><b>MEJORA:</b> Agregar búsqueda por nombre para soporte de autocompletado
 * en la interfaz: {@code List<Paciente> findByNombreContainingIgnoreCase(String nombre)}.</p>
 */
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
