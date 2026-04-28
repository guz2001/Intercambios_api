package com.intercambios.intercambios_api.model;

import jakarta.persistence.*;

/**
 * Entidad que representa el nivel más alto de clasificación alimentaria.
 *
 * <p>Un grupo agrupa conjuntos de alimentos con características nutricionales
 * similares (ej. "Cereales y derivados", "Lácteos", "Frutas").
 * Cada grupo contiene uno o más {@link SubgrupoAlimento}.</p>
 *
 * <p>Mapea la tabla {@code grupo_alimento} en la base de datos.</p>
 */
@Entity
@Table(name = "grupo_alimento")
public class GrupoAlimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Nombre del grupo. Obligatorio y con límite de 100 caracteres. */
    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * Descripción extendida del grupo alimentario.
     *
     * <p><b>MEJORA:</b> Agregar {@code length} y {@code nullable = false}
     * si la descripción es requerida por el negocio. Actualmente es nullable
     * sin restricción de longitud.</p>
     */
    private String descripcion;

    public GrupoAlimento() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
