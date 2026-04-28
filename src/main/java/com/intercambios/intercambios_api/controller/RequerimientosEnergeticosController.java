package com.intercambios.intercambios_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para el cálculo del requerimiento energético de un paciente.
 *
 * <p>Expondrá el cálculo del Gasto Energético Total (GET) a partir de los datos
 * antropométricos del paciente utilizando la fórmula de Harris-Benedict.</p>
 *
 * <p><b>PENDIENTE:</b> Este controlador está vacío. Implementar el siguiente endpoint:</p>
 * <pre>
 * POST /api/requerimientos/calcular
 * Body: { peso, altura, edad, sexo, factorActividad }
 * Response: { tmb, get }
 * </pre>
 *
 * <p>Delegar el cálculo al método {@code RequerimientosEnergeticosService#calcularGET}
 * una vez que éste sea implementado.</p>
 */
@RestController
@RequestMapping("/api/requerimientos")
public class RequerimientosEnergeticosController {
}
