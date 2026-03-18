package com.intercambios.intercambios_api.model;
import jakarta.persistence.*;
@Entity
@Table(name = "subgrupo_alimento")
public class SubgrupoAlimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private GrupoAlimento grupo;

    @Column(nullable = false, length = 150)
    private String nombre;
    @Column(name = "poblacion", columnDefinition = "ENUM('ninos_adultos','menores_2_anos','adultos','ninos','general')")
    private String poblacion;
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
