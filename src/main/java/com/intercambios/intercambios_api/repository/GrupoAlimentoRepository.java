package com.intercambios.intercambios_api.repository;
import com.intercambios.intercambios_api.model.GrupoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface GrupoAlimentoRepository extends JpaRepository<GrupoAlimento, Integer> {

    }

