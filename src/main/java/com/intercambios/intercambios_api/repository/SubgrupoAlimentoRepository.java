package com.intercambios.intercambios_api.repository;

import com.intercambios.intercambios_api.model.SubgrupoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubgrupoAlimentoRepository extends JpaRepository<SubgrupoAlimento, Integer> {

    List<SubgrupoAlimento> findByGrupoId(Integer grupoId);
}