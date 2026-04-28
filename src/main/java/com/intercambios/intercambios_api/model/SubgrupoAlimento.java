package com.intercambios.intercambios_api.model;

import com.intercambios.intercambios_api.converter.PoblacionConverter;
import jakarta.persistence.*;

/**
 * Entidad que representa una subdivisión dentro de un {@link GrupoAlimento}.
 *
 * <p>Un subgrupo reúne alimentos intercambiables entre sí, es decir, que
 * aportan una cantidad calórica equivalente por porción. El campo
 * {@code kcalPromedio} refleja ese valor compartido y es la base del
 * sistema de intercambios.</p>
 *
 * <p>El campo {@code poblacion} indica el grupo etario al que aplica
 * el subgrupo, ya que los valores de intercambio difieren según la edad.
 * Utiliza el enum {@link Poblacion} con un {@link PoblacionConverter} que
 * mapea automáticamente entre las constantes Java y los valores de la
 * columna ENUM de MySQL.</p>
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
     * Grupo de población al que aplica este subgrupo alimentario.
     *
     * <p>El {@link PoblacionConverter} (registrado con {@code autoApply = true})
     * traduce automáticamente entre el enum Java y los valores lowercase
     * de la columna ENUM en MySQL, sin necesidad de modificar el esquema.</p>
     */
    @Column(name = "poblacion", columnDefinition = "ENUM('ninos_adultos','menores_2_anos','adultos','ninos','general')")
    private Poblacion poblacion;

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

    public Poblacion getPoblacion() { return poblacion; }
    public void setPoblacion(Poblacion poblacion) { this.poblacion = poblacion; }

    public Double getKcalPromedio() { return kcalPromedio; }
    public void setKcalPromedio(Double kcalPromedio) { this.kcalPromedio = kcalPromedio; }
}
