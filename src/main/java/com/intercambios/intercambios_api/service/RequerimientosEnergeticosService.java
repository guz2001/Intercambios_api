package com.intercambios.intercambios_api.service;

import org.springframework.stereotype.Service;

/**
 * Servicio para el cálculo del requerimiento energético diario de un paciente.
 *
 * <p>Implementará la fórmula de Harris-Benedict revisada (Roza &amp; Shizgal, 1984)
 * para calcular la Tasa Metabólica Basal (TMB) y el Gasto Energético Total (GET).</p>
 *
 * <p><b>PENDIENTE:</b> Este servicio está vacío. Debe implementarse con los
 * siguientes métodos:</p>
 * <pre>
 * // Calcula la TMB según sexo y retorna el GET = TMB × factorActividad
 * public double calcularGET(RequerimientosEnergeticos datos);
 *
 * // Sobreloading conveniente: acepta directamente una entidad Paciente
 * public double calcularGET(Paciente paciente);
 * </pre>
 *
 * <p>Fórmulas a implementar:</p>
 * <ul>
 *   <li><b>Hombre:</b> TMB = 88.362 + (13.397 × kg) + (4.799 × cm) − (5.677 × años)</li>
 *   <li><b>Mujer:</b>  TMB = 447.593 + (9.247 × kg) + (3.098 × cm) − (4.330 × años)</li>
 * </ul>
 */
@Service
public class RequerimientosEnergeticosService {
}
