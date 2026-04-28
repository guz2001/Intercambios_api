package com.intercambios.intercambios_api.model;

import jakarta.persistence.*;

/**
 * Entidad que almacena la composición nutricional detallada de un {@link Alimento}.
 *
 * <p>Cada alimento tiene exactamente un perfil de nutrientes (relación {@code OneToOne}).
 * Los valores representan la cantidad de cada nutriente presente en la porción
 * estándar definida en {@link Alimento#getPorcionGramos()}.</p>
 *
 * <p>Las unidades de medida son:
 * <ul>
 *   <li>Energía: kcal</li>
 *   <li>Macronutrientes: gramos (g)</li>
 *   <li>Minerales: miligramos (mg)</li>
 *   <li>Vitamina B12: microgramos (mcg)</li>
 * </ul>
 * </p>
 *
 * <p>Mapea la tabla {@code nutriente_alimento}.</p>
 *
 * <p><b>MEJORA:</b> Los campos numéricos usan {@code Double}, que puede
 * introducir errores de redondeo en sumas acumuladas. Para mayor precisión
 * en reportes dietéticos, considerar {@code BigDecimal}.</p>
 */
@Entity
@Table(name = "nutriente_alimento")
public class NutrienteAlimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Alimento al que corresponde este perfil nutricional.
     * La restricción {@code unique = true} garantiza la relación 1-a-1.
     */
    @OneToOne
    @JoinColumn(name = "alimento_id", nullable = false, unique = true)
    private Alimento alimento;

    /** Energía total de la porción en kilocalorías. */
    @Column(columnDefinition = "DECIMAL(8,2)")
    private Double kcal;

    /** Contenido de proteína en gramos. */
    @Column(name = "proteina_g", columnDefinition = "DECIMAL(8,2)")
    private Double proteinaG;

    /** Grasa total en gramos (incluye saturadas, mono y poliinsaturadas). */
    @Column(name = "grasa_total_g", columnDefinition = "DECIMAL(8,2)")
    private Double grasaTotalG;

    /**
     * Ácidos grasos saturados (AGS) en gramos.
     * Precisión de 3 decimales por los valores muy pequeños en algunos alimentos.
     */
    @Column(name = "ags_g", columnDefinition = "DECIMAL(8,3)")
    private Double agsG;

    /** Carbohidratos disponibles (CHO) en gramos. */
    @Column(name = "cho_g", columnDefinition = "DECIMAL(8,2)")
    private Double choG;

    /** Fibra dietética total en gramos. */
    @Column(name = "fibra_g", columnDefinition = "DECIMAL(8,2)")
    private Double fibraG;

    /** Calcio en miligramos. */
    @Column(name = "calcio_mg", columnDefinition = "DECIMAL(8,2)")
    private Double calcioMg;

    /** Hierro en miligramos. Precisión de 3 decimales por valores inferiores a 1 mg. */
    @Column(name = "hierro_mg", columnDefinition = "DECIMAL(8,3)")
    private Double hierroMg;

    /** Sodio en miligramos. */
    @Column(name = "sodio_mg", columnDefinition = "DECIMAL(8,2)")
    private Double sodioMg;

    /** Potasio en miligramos. */
    @Column(name = "potasio_mg", columnDefinition = "DECIMAL(8,2)")
    private Double potasioMg;

    /** Vitamina C en miligramos. */
    @Column(name = "vitamina_c_mg", columnDefinition = "DECIMAL(8,2)")
    private Double vitaminaCMg;

    /** Vitamina B12 en microgramos (mcg). Precisión de 3 decimales por valores traza. */
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
