package com.intercambios.intercambios_api.model;

import jakarta.persistence.*;

/**
 * Entidad que representa a un paciente registrado en el sistema.
 *
 * <p>Los datos almacenados son los necesarios para calcular el requerimiento
 * energético diario mediante la fórmula de Harris-Benedict:
 * <ul>
 *   <li><b>Hombre:</b> TMB = 66.5 + (13.75 × peso) + (5.003 × altura) − (6.775 × edad)</li>
 *   <li><b>Mujer:</b>  TMB = 655.1 + (9.563 × peso) + (1.850 × altura) − (4.676 × edad)</li>
 * </ul>
 * El resultado se multiplica por {@code factorActividad} para obtener el
 * gasto energético total (GET).</p>
 *
 * <p>Mapea la tabla {@code paciente}.</p>
 *
 * <p><b>MEJORA:</b> Esta entidad carece de servicio y controlador propios.
 * Implementar {@code PacienteService} y {@code PacienteController} para exponer
 * el CRUD de pacientes y el cálculo de su GET vía {@code RequerimientosEnergeticosService}.</p>
 */
@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Nombre completo del paciente. Obligatorio, máximo 200 caracteres. */
    @Column(nullable = false, length = 200)
    private String nombre;

    /** Edad en años completos. Obligatorio. */
    @Column(nullable = false)
    private Integer edad;

    /** Peso corporal en kilogramos. Obligatorio. */
    @Column(nullable = false)
    private Double peso;

    /**
     * Talla en centímetros. Obligatorio.
     *
     * <p><b>MEJORA:</b> Renombrar a {@code talla} en la capa de presentación
     * para alinearse con la terminología clínica estándar en nutrición.</p>
     */
    @Column(nullable = false)
    private Double altura;

    /**
     * Factor de actividad física (FAF) que multiplica la tasa metabólica basal.
     *
     * <p>Valores estándar:
     * <ul>
     *   <li>1.2  – sedentario</li>
     *   <li>1.375 – ligeramente activo</li>
     *   <li>1.55  – moderadamente activo</li>
     *   <li>1.725 – muy activo</li>
     *   <li>1.9   – extremadamente activo</li>
     * </ul></p>
     */
    @Column(name = "factor_actividad", nullable = false)
    private Double factorActividad;

    /**
     * Sexo biológico del paciente, determinante para seleccionar
     * la ecuación correcta de Harris-Benedict.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    public Paciente() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Double getFactorActividad() { return factorActividad; }
    public void setFactorActividad(Double factorActividad) { this.factorActividad = factorActividad; }

    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }
}
