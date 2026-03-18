package com.intercambios.intercambios_api.service;
import com.intercambios.intercambios_api.model.NutrienteAlimento;
import com.intercambios.intercambios_api.repository.NutrienteAlimentoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class NutrienteAlimentoService {

    private final NutrienteAlimentoRepository repository;

    public NutrienteAlimentoService(NutrienteAlimentoRepository repository) {
        this.repository = repository;
    }

    public Optional<NutrienteAlimento> buscarPorAlimento(Integer alimentoId) {
        return repository.findByAlimentoId(alimentoId);
    }

}
