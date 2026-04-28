package com.intercambios.intercambios_api.controller;

import com.intercambios.intercambios_api.model.NutrienteAlimento;
import com.intercambios.intercambios_api.service.NutrienteAlimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para consultar el perfil nutricional de los alimentos.
 *
 * <p>Expone el detalle de macronutrientes y micronutrientes de un alimento
 * bajo {@code /api/nutrientes}. Usa {@link ResponseEntity} para diferenciar
 * entre un alimento con perfil cargado ({@code 200 OK}) y uno sin él
 * ({@code 404 Not Found}).</p>
 */
@RestController
@RequestMapping("/api/nutrientes")
public class NutrienteAlimentoController {

    private final NutrienteAlimentoService service;

    public NutrienteAlimentoController(NutrienteAlimentoService service) {
        this.service = service;
    }

    /**
     * Devuelve el perfil nutricional de un alimento por su ID.
     *
     * <p>{@code GET /api/nutrientes/alimento/{alimentoId}}</p>
     *
     * <p>El encadenamiento {@code Optional.map(...).orElse(...)} evita un
     * bloque {@code if/else} explícito y retorna el código HTTP correcto
     * de forma declarativa.</p>
     *
     * @param alimentoId ID del alimento
     * @return {@code 200 OK} con los nutrientes si existen,
     *         {@code 404 Not Found} si el alimento no tiene perfil nutricional
     */
    @GetMapping("/alimento/{alimentoId}")
    public ResponseEntity<NutrienteAlimento> buscarPorAlimento(@PathVariable Integer alimentoId) {
        return service.buscarPorAlimento(alimentoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
