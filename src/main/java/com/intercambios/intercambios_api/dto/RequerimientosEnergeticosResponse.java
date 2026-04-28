package com.intercambios.intercambios_api.dto;

/**
 * DTO de respuesta para el cálculo del requerimiento energético diario.
 *
 * <p>Encapsula los dos valores que devuelve la fórmula de Harris-Benedict:</p>
 * <ul>
 *   <li>{@code tmb} – Tasa Metabólica Basal: calorías mínimas en reposo absoluto.</li>
 *   <li>{@code get} – Gasto Energético Total: calorías reales considerando la
 *       actividad física ({@code GET = TMB × factorActividad}).</li>
 * </ul>
 *
 * <p>Separar la respuesta en un DTO propio permite agregar campos futuros
 * (ej. distribución de macronutrientes) sin modificar la firma del servicio.</p>
 */
public class RequerimientosEnergeticosResponse {

    /** Tasa Metabólica Basal en kcal/día calculada con Harris-Benedict. */
    private double tmb;

    /** Gasto Energético Total en kcal/día (TMB × factor de actividad). */
    private double get;

    public RequerimientosEnergeticosResponse(double tmb, double get) {
        this.tmb = tmb;
        this.get = get;
    }

    public double getTmb() { return tmb; }
    public double getGet() { return get; }
}
