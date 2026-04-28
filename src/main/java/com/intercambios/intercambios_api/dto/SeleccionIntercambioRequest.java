package com.intercambios.intercambios_api.dto;

/**
 * DTO de entrada para registrar una selección de intercambio alimentario.
 *
 * <p>Desacopla el cuerpo de la petición HTTP de la entidad JPA
 * {@code SeleccionIntercambio}, siguiendo el principio de separación
 * de responsabilidades: el controlador recibe este DTO y el servicio
 * lo convierte en la entidad persistida.</p>
 *
 * <p><b>MEJORA:</b> Agregar la dependencia {@code spring-boot-starter-validation}
 * al {@code pom.xml} y anotar los campos con {@code @NotNull} para que Spring
 * rechace automáticamente peticiones incompletas con {@code 400 Bad Request},
 * sin necesidad de validación manual en el servicio:
 * <pre>
 *   {@literal @}NotNull(message = "El alimento origen es obligatorio")
 *   private Integer alimentoOrigenId;
 * </pre>
 * Y en el controlador añadir {@code @Valid} antes del {@code @RequestBody}.</p>
 */
public class SeleccionIntercambioRequest {

    /**
     * ID del alimento que el paciente desea reemplazar.
     * Obligatorio: el servicio lanza {@code IllegalArgumentException} si es nulo o inexistente.
     */
    private Integer alimentoOrigenId;

    /**
     * ID del alimento elegido como sustituto.
     * Debe pertenecer al mismo subgrupo que {@code alimentoOrigenId}.
     */
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
