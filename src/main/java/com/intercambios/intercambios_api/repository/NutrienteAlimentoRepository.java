package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.NutrienteAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio de acceso a datos para la entidad {@link NutrienteAlimento}.
 *
 * <p>Dado que la relación entre alimento y sus nutrientes es {@code OneToOne},
 * se usa {@code Optional} para representar correctamente el caso en que
 * un alimento aún no tenga perfil nutricional cargado.</p>
 */
@Repository
public interface NutrienteAlimentoRepository extends JpaRepository<NutrienteAlimento, Integer> {

    /**
     * Busca el perfil nutricional asociado a un alimento.
     *
     * @param alimentoId ID del alimento
     * @return {@code Optional} con los nutrientes si existen, vacío si no
     */
    Optional<NutrienteAlimento> findByAlimentoId(Integer alimentoId);
}
