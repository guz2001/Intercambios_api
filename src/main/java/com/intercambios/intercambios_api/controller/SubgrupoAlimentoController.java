package com.intercambios.intercambios_api.controller;
import com.intercambios.intercambios_api.model.SubgrupoAlimento;
import com.intercambios.intercambios_api.service.SubgrupoAlimentoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/subgrupos")
public class SubgrupoAlimentoController {

        private final SubgrupoAlimentoService service;

        public SubgrupoAlimentoController(SubgrupoAlimentoService service) {
            this.service = service;
        }

        @GetMapping
        public List<SubgrupoAlimento> listarTodos() {
            return service.listarTodos();
        }

        @GetMapping("/por-grupo/{grupoId}")
        public List<SubgrupoAlimento> listarPorGrupo(@PathVariable Integer grupoId) {
            return service.listarPorGrupo(grupoId);
        }

}
