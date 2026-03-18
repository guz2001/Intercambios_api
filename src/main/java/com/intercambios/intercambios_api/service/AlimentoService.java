package com.intercambios.intercambios_api.service;
import com.intercambios.intercambios_api.model.Alimento;
import com.intercambios.intercambios_api.repository.AlimentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlimentoService {
    private final AlimentoRepository repository;

    public AlimentoService(AlimentoRepository repository) {
        this.repository = repository;
    }

    public List<Alimento> listarTodos() {
        return repository.findAll();
    }

    public List<Alimento> listarPorSubgrupo(Integer subgrupoId) {
        return repository.findBySubgrupoId(subgrupoId);
    }

    public List<Alimento> buscarIntercambios(Integer alimentoId) {
        return repository.findIntercambios(alimentoId);
    }

}
