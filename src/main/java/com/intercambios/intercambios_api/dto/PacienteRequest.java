package com.intercambios.intercambios_api.dto;

import com.intercambios.intercambios_api.model.Sexo;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para crear o actualizar un paciente.
 *
 * <p>Separa la capa de presentación del modelo JPA: el cliente envía este
 * objeto y el servicio construye la entidad {@link com.intercambios.intercambios_api.model.Paciente}.</p>
 */
public class PacienteRequest {

    @NotBlank(message = "El nombre del paciente es obligatorio")
    private String nombre;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 1,  message = "La edad mínima es 1 año")
    @Max(value = 120, message = "La edad máxima es 120 años")
    private Integer edad;

    @NotNull(message = "El peso es obligatorio")
    @DecimalMin(value = "1.0",  message = "El peso mínimo es 1 kg")
    @DecimalMax(value = "500.0", message = "El peso máximo es 500 kg")
    private Double peso;

    @NotNull(message = "La altura (talla) es obligatoria")
    @DecimalMin(value = "30.0",  message = "La altura mínima es 30 cm")
    @DecimalMax(value = "250.0", message = "La altura máxima es 250 cm")
    private Double altura;

    @NotNull(message = "El sexo es obligatorio (MASCULINO | FEMENINO)")
    private Sexo sexo;

    @NotNull(message = "El factor de actividad es obligatorio")
    @DecimalMin(value = "1.0", message = "El factor de actividad mínimo es 1.0 (sedentario)")
    @DecimalMax(value = "2.5", message = "El factor de actividad máximo es 2.5")
    private Double factorActividad;

    public PacienteRequest() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    public Double getFactorActividad() { return factorActividad; }
    public void setFactorActividad(Double factorActividad) { this.factorActividad = factorActividad; }
}
