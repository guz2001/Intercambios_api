package com.intercambios.intercambios_api.repository;
import com.intercambios.intercambios_api.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {
    List<Alimento> findBySubgrupoId(Integer subgrupoId);

    @Query("SELECT a FROM Alimento a WHERE a.subgrupo.id = " +
            "(SELECT a2.subgrupo.id FROM Alimento a2 WHERE a2.id = :alimentoId) " +
            "AND a.id != :alimentoId")
    List<Alimento> findIntercambios(@Param("alimentoId") Integer alimentoId);
}
