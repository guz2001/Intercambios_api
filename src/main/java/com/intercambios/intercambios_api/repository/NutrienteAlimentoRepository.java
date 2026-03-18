package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.NutrienteAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface NutrienteAlimentoRepository extends JpaRepository<NutrienteAlimento, Integer> {

    Optional<NutrienteAlimento> findByAlimentoId(Integer alimentoId);
}
