package com.intercambios.intercambios_api.controller;
import com.intercambios.intercambios_api.model.GrupoAlimento;
import com.intercambios.intercambios_api.service.GrupoAlimentoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/grupos")
public class GrupoAlimentoController {
    private final GrupoAlimentoService service;

    public GrupoAlimentoController(GrupoAlimentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<GrupoAlimento> listarTodos() {
        return service.listarTodos();
    }

}
