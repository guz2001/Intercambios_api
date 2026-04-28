package com.intercambios.intercambios_api.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un alimento concreto dentro del sistema de intercambios.
 *
 * <p>Cada alimento pertenece a un {@link SubgrupoAlimento}, lo que define su
 * equivalencia calórica y los alimentos con los que puede intercambiarse.
 * La porción en gramos ({@code porcionGramos}) corresponde al peso estándar
 * que aporta las kilocalorías del subgrupo.</p>
 *
 * <p>Mapea la tabla {@code alimento}.</p>
 */
@Entity
@Table(name = "alimento")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Subgrupo alimentario al que pertenece este alimento.
     * Determina con qué otros alimentos puede intercambiarse.
     */
    @ManyToOne
    @JoinColumn(name = "subgrupo_id", nullable = false)
    private SubgrupoAlimento subgrupo;

    /** Nombre del alimento. Obligatorio, máximo 200 caracteres. */
    @Column(nullable = false, length = 200)
    private String nombre;

    /**
     * Peso en gramos de la porción estándar del alimento.
     *
     * <p><b>MEJORA:</b> Considerar {@code BigDecimal} en lugar de {@code Double}
     * para evitar errores de punto flotante en cálculos nutricionales acumulados.</p>
     */
    @Column(columnDefinition = "DECIMAL(8,2)")
    private Double porcionGramos;

    /**
     * Unidad de medida casera equivalente a la porción estándar
     * (ej. "taza", "cucharada", "pieza").
     */
    @Column(length = 100)
    private String unidadMedida;

    /**
     * Descripción detallada de la medida casera para facilitar
     * la comprensión del paciente (ej. "1 taza mediana bien llena").
     */
    @Column(length = 200)
    private String descripcionMedida;

    public Alimento() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public SubgrupoAlimento getSubgrupo() { return subgrupo; }
    public void setSubgrupo(SubgrupoAlimento subgrupo) { this.subgrupo = subgrupo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPorcionGramos() { return porcionGramos; }
    public void setPorcionGramos(Double porcionGramos) { this.porcionGramos = porcionGramos; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public String getDescripcionMedida() { return descripcionMedida; }
    public void setDescripcionMedida(String descripcionMedida) { this.descripcionMedida = descripcionMedida; }
}
