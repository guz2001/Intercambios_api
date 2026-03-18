package com.intercambios.intercambios_api.controller;
import com.intercambios.intercambios_api.model.NutrienteAlimento;
import com.intercambios.intercambios_api.service.NutrienteAlimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/nutrientes")
public class NutrienteAlimentoController {
    private final NutrienteAlimentoService service;

    public NutrienteAlimentoController(NutrienteAlimentoService service) {
        this.service = service;
    }

    @GetMapping("/alimento/{alimentoId}")
    public ResponseEntity<NutrienteAlimento> buscarPorAlimento(@PathVariable Integer alimentoId) {
        return service.buscarPorAlimento(alimentoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

