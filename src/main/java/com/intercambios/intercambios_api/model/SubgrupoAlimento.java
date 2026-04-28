package com.intercambios.intercambios_api.model;

import jakarta.persistence.*;

/**
 * Entidad que representa una subdivisión dentro dahe un {@link GrupoAlimento}.
 *
 * <p>Un subgrupo reúne alimentos intercambiables entre sí, es decir, que
 * aportan una cantidad calórica equivalente por porción. El campo
 * {@code kcalPromedio} refleja ese valor compartido y es la base del
 * sistema de intercambios.</p>
 *
 * <p>El campo {@code poblacion} indica el grupo etario al que aplica
 * el subgrupo, ya que los valores de intercambio difieren según la edad.</p>
 *
 * <p>Mapea la tabla {@code subgrupo_alimento}.</p>
 */
@Entity
@Table(name = "subgrupo_alimento")
public class SubgrupoAlimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Grupo padre al que pertenece este subgrupo. No puede ser nulo. */
    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private GrupoAlimento grupo;

    /** Nombre descriptivo del subgrupo. Obligatorio, máximo 150 caracteres. */
    @Column(nullable = false, length = 150)
    private String nombre;

    /**
     * Población objetivo del subgrupo.
     *
     * <p>Valores válidos según la base de datos:
     * {@code ninos_adultos}, {@code menores_2_anos}, {@code adultos},
     * {@code ninos}, {@code general}.</p>
     *
     * <p><b>MEJORA:</b> Este campo debería ser un {@code enum} de Java
     * (similar a {@link Sexo}) en lugar de un {@code String} libre.
     * Usar un enum previene valores inválidos en tiempo de compilación
     * y elimina la necesidad de definir el ENUM directamente en SQL.</p>
     */
    @Column(name = "poblacion", columnDefinition = "ENUM('ninos_adultos','menores_2_anos','adultos','ninos','general')")
    private String poblacion;

    /**
     * Kilocalorías promedio por porción estándar para este subgrupo.
     * Es el valor de referencia que hace intercambiables a los alimentos del grupo.
     */
    @Column(name = "kcal_promedio", columnDefinition = "DECIMAL(6,2)")
    private Double kcalPromedio;

    public SubgrupoAlimento() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public GrupoAlimento getGrupo() { return grupo; }
    public void setGrupo(GrupoAlimento grupo) { this.grupo = grupo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPoblacion() { return poblacion; }
    public void setPoblacion(String poblacion) { this.poblacion = poblacion; }

    public Double getKcalPromedio() { return kcalPromedio; }
    public void setKcalPromedio(Double kcalPromedio) { this.kcalPromedio = kcalPromedio; }
}
