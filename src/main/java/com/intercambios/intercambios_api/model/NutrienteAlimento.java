package com.intercambios.intercambios_api.model;
import jakarta.persistence.*;
@Entity
@Table(name = "nutriente_alimento")
public class NutrienteAlimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "alimento_id", nullable = false, unique = true)
    private Alimento alimento;

    @Column(columnDefinition = "DECIMAL(8,2)")
    private Double kcal;

    @Column(name = "proteina_g", columnDefinition = "DECIMAL(8,2)")
    private Double proteinaG;

    @Column(name = "grasa_total_g", columnDefinition = "DECIMAL(8,2)")
    private Double grasaTotalG;

    @Column(name = "ags_g", columnDefinition = "DECIMAL(8,3)")
    private Double agsG;

    @Column(name = "cho_g", columnDefinition = "DECIMAL(8,2)")
    private Double choG;

    @Column(name = "fibra_g", columnDefinition = "DECIMAL(8,2)")
    private Double fibraG;

    @Column(name = "calcio_mg", columnDefinition = "DECIMAL(8,2)")
    private Double calcioMg;

    @Column(name = "hierro_mg", columnDefinition = "DECIMAL(8,3)")
    private Double hierroMg;

    @Column(name = "sodio_mg", columnDefinition = "DECIMAL(8,2)")
    private Double sodioMg;

    @Column(name = "potasio_mg", columnDefinition = "DECIMAL(8,2)")
    private Double potasioMg;

    @Column(name = "vitamina_c_mg", columnDefinition = "DECIMAL(8,2)")
    private Double vitaminaCMg;

    @Column(name = "vitamina_b12_mcg", columnDefinition = "DECIMAL(8,3)")
    private Double vitaminaB12Mcg;

    public NutrienteAlimento() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Alimento getAlimento() { return alimento; }
    public void setAlimento(Alimento alimento) { this.alimento = alimento; }

    public Double getKcal() { return kcal; }
    public void setKcal(Double kcal) { this.kcal = kcal; }

    public Double getProteinaG() { return proteinaG; }
    public void setProteinaG(Double proteinaG) { this.proteinaG = proteinaG; }

    public Double getGrasaTotalG() { return grasaTotalG; }
    public void setGrasaTotalG(Double grasaTotalG) { this.grasaTotalG = grasaTotalG; }

    public Double getAgsG() { return agsG; }
    public void setAgsG(Double agsG) { this.agsG = agsG; }

    public Double getChoG() { return choG; }
    public void setChoG(Double choG) { this.choG = choG; }

    public Double getFibraG() { return fibraG; }
    public void setFibraG(Double fibraG) { this.fibraG = fibraG; }

    public Double getCalcioMg() { return calcioMg; }
    public void setCalcioMg(Double calcioMg) { this.calcioMg = calcioMg; }

    public Double getHierroMg() { return hierroMg; }
    public void setHierroMg(Double hierroMg) { this.hierroMg = hierroMg; }

    public Double getSodioMg() { return sodioMg; }
    public void setSodioMg(Double sodioMg) { this.sodioMg = sodioMg; }

    public Double getPotasioMg() { return potasioMg; }
    public void setPotasioMg(Double potasioMg) { this.potasioMg = potasioMg; }

    public Double getVitaminaCMg() { return vitaminaCMg; }
    public void setVitaminaCMg(Double vitaminaCMg) { this.vitaminaCMg = vitaminaCMg; }

    public Double getVitaminaB12Mcg() { return vitaminaB12Mcg; }
    public void setVitaminaB12Mcg(Double vitaminaB12Mcg) { this.vitaminaB12Mcg = vitaminaB12Mcg; }
}
