package com.intercambios.intercambios_api.service;
import com.intercambios.intercambios_api.model.GrupoAlimento;
import com.intercambios.intercambios_api.repository.GrupoAlimentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

    @Service
    public class GrupoAlimentoService {

        private final GrupoAlimentoRepository repository;

        public GrupoAlimentoService(GrupoAlimentoRepository repository) {
            this.repository = repository;
        }

        public List<GrupoAlimento> listarTodos() {
            return repository.findAll();
        }
    }

