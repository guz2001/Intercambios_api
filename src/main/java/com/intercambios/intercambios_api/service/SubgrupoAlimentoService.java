package com.intercambios.intercambios_api.service;
import com.intercambios.intercambios_api.model.SubgrupoAlimento;
import com.intercambios.intercambios_api.repository.SubgrupoAlimentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SubgrupoAlimentoService {
    private final SubgrupoAlimentoRepository repository;

    public SubgrupoAlimentoService(SubgrupoAlimentoRepository repository) {
        this.repository = repository;
    }

    public List<SubgrupoAlimento> listarTodos() {
        return repository.findAll();
    }

    public List<SubgrupoAlimento> listarPorGrupo(Integer grupoId) {
        return repository.findByGrupoId(grupoId);
    }

}
