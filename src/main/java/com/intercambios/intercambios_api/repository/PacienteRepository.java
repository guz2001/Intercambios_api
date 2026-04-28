package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad {@link Paciente}.
 *
 * <p>Proporciona operaciones CRUD estándar heredadas de {@link JpaRepository}
 * y agrega búsqueda por nombre para soportar autocompletado en el frontend.</p>
 */
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    /**
     * Busca pacientes cuyo nombre contenga el texto dado (insensible a mayúsculas).
     *
     * <p>Spring Data JPA traduce {@code ContainingIgnoreCase} a
     * {@code LIKE '%nombre%'} con {@code LOWER()} en SQL, permitiendo
     * búsquedas parciales sin importar si el usuario escribe en mayúsculas o minúsculas.</p>
     *
     * @param nombre fragmento del nombre a buscar
     * @return lista de pacientes cuyo nombre contiene el fragmento; vacía si no hay coincidencias
     */
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
}
