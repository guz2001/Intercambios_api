package com.intercambios.intercambios_api.dto;

import jakarta.validation.constraints.NotNull;

/**
 * DTO de entrada para registrar una selección de intercambio alimentario.
 *
 * <p>Desacopla el cuerpo de la petición HTTP de la entidad JPA
 * {@code SeleccionIntercambio}. Los campos obligatorios están anotados
 * con {@code @NotNull} para que Spring rechace automáticamente peticiones
 * incompletas con {@code 400 Bad Request} antes de llegar al servicio,
 * siempre que el controlador use {@code @Valid} en el parámetro.</p>
 *
 * <p>El manejo del error de validación está centralizado en
 * {@code GlobalExceptionHandler#handleValidation}.</p>
 */
public class SeleccionIntercambioRequest {

    /**
     * ID del alimento que el paciente desea reemplazar. Obligatorio.
     */
    @NotNull(message = "El ID del alimento origen es obligatorio")
    private Integer alimentoOrigenId;

    /**
     * ID del alimento elegido como sustituto. Obligatorio.
     * Debe pertenecer al mismo subgrupo que {@code alimentoOrigenId}.
     */
    @NotNull(message = "El ID del alimento reemplazo es obligatorio")
    private Integer alimentoReemplazoId;

    /**
     * ID del paciente que realiza la selección. Opcional.
     * Si se omite, la selección se guarda sin asociar a un paciente.
     */
    private Integer pacienteId;

    public Integer getAlimentoOrigenId() { return alimentoOrigenId; }
    public void setAlimentoOrigenId(Integer alimentoOrigenId) { this.alimentoOrigenId = alimentoOrigenId; }

    public Integer getAlimentoReemplazoId() { return alimentoReemplazoId; }
    public void setAlimentoReemplazoId(Integer alimentoReemplazoId) { this.alimentoReemplazoId = alimentoReemplazoId; }

    public Integer getPacienteId() { return pacienteId; }
    public void setPacienteId(Integer pacienteId) { this.pacienteId = pacienteId; }
}
