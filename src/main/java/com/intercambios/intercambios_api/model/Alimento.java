package com.intercambios.intercambios_api.model;
import jakarta.persistence.*;
@Entity
@Table(name = "alimento")
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subgrupo_id", nullable = false)
    private SubgrupoAlimento subgrupo;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(columnDefinition = "DECIMAL(8,2)")
    private Double porcionGramos;

    @Column(length = 100)
    private String unidadMedida;

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
