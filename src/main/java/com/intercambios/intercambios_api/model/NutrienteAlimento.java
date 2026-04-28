package com.intercambios.intercambios_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que almacena la composición nutricional detallada de un {@link Alimento}.
 *
 * <p>Cada alimento tiene exactamente un perfil de nutrientes (relación {@code OneToOne}).
 * Los valores representan la cantidad de cada nutriente presente en la porción
 * estándar definida en {@link Alimento#getPorcionGramos()}.</p>
 *
 * <p>Los campos numéricos usan {@link BigDecimal} en lugar de {@code Double} para
 * evitar errores de punto flotante en cálculos nutricionales acumulados (ej. al
 * sumar los nutrientes de un plan alimentario completo). {@code BigDecimal} mapea
 * directamente a las columnas {@code DECIMAL} de MySQL sin pérdida de precisión.</p>
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
    private BigDecimal kcal;

    /** Contenido de proteína en gramos. */
    @Column(name = "proteina_g", columnDefinition = "DECIMAL(8,2)")
    private BigDecimal proteinaG;

    /** Grasa total en gramos (incluye saturadas, mono y poliinsaturadas). */
    @Column(name = "grasa_total_g", columnDefinition = "DECIMAL(8,2)")
    private BigDecimal grasaTotalG;

    /**
     * Ácidos grasos saturados (AGS) en gramos.
     * Precisión de 3 decimales por los valores muy pequeños en algunos alimentos.
     */
    @Column(name = "ags_g", columnDefinition = "DECIMAL(8,3)")
    private BigDecimal agsG;

    /** Carbohidratos disponibles (CHO) en gramos. */
    @Column(name = "cho_g", columnDefinition = "DECIMAL(8,2)")
    private BigDecimal choG;

    /** Fibra dietética total en gramos. */
    @Column(name = "fibra_g", columnDefinition = "DECIMAL(8,2)")
    private BigDecimal fibraG;

    /** Calcio en miligramos. */
    @Column(name = "calcio_mg", columnDefinition = "DECIMAL(8,2)")
    private BigDecimal calcioMg;

    /** Hierro en miligramos. Precisión de 3 decimales por valores inferiores a 1 mg. */
    @Column(name = "hierro_mg", columnDefinition = "DECIMAL(8,3)")
    private BigDecimal hierroMg;

    /** Sodio en miligramos. */
    @Column(name = "sodio_mg", columnDefinition = "DECIMAL(8,2)")
    private BigDecimal sodioMg;

    /** Potasio en miligramos. */
    @Column(name = "potasio_mg", columnDefinition = "DECIMAL(8,2)")
    private BigDecimal potasioMg;

    /** Vitamina C en miligramos. */
    @Column(name = "vitamina_c_mg", columnDefinition = "DECIMAL(8,2)")
    private BigDecimal vitaminaCMg;

    /** Vitamina B12 en microgramos (mcg). Precisión de 3 decimales por valores traza. */
    @Column(name = "vitamina_b12_mcg", columnDefinition = "DECIMAL(8,3)")
    private BigDecimal vitaminaB12Mcg;

    public NutrienteAlimento() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Alimento getAlimento() { return alimento; }
    public void setAlimento(Alimento alimento) { this.alimento = alimento; }

    public BigDecimal getKcal() { return kcal; }
    public void setKcal(BigDecimal kcal) { this.kcal = kcal; }

    public BigDecimal getProteinaG() { return proteinaG; }
    public void setProteinaG(BigDecimal proteinaG) { this.proteinaG = proteinaG; }

    public BigDecimal getGrasaTotalG() { return grasaTotalG; }
    public void setGrasaTotalG(BigDecimal grasaTotalG) { this.grasaTotalG = grasaTotalG; }

    public BigDecimal getAgsG() { return agsG; }
    public void setAgsG(BigDecimal agsG) { this.agsG = agsG; }

    public BigDecimal getChoG() { return choG; }
    public void setChoG(BigDecimal choG) { this.choG = choG; }

    public BigDecimal getFibraG() { return fibraG; }
    public void setFibraG(BigDecimal fibraG) { this.fibraG = fibraG; }

    public BigDecimal getCalcioMg() { return calcioMg; }
    public void setCalcioMg(BigDecimal calcioMg) { this.calcioMg = calcioMg; }

    public BigDecimal getHierroMg() { return hierroMg; }
    public void setHierroMg(BigDecimal hierroMg) { this.hierroMg = hierroMg; }

    public BigDecimal getSodioMg() { return sodioMg; }
    public void setSodioMg(BigDecimal sodioMg) { this.sodioMg = sodioMg; }

    public BigDecimal getPotasioMg() { return potasioMg; }
    public void setPotasioMg(BigDecimal potasioMg) { this.potasioMg = potasioMg; }

    public BigDecimal getVitaminaCMg() { return vitaminaCMg; }
    public void setVitaminaCMg(BigDecimal vitaminaCMg) { this.vitaminaCMg = vitaminaCMg; }

    public BigDecimal getVitaminaB12Mcg() { return vitaminaB12Mcg; }
    public void setVitaminaB12Mcg(BigDecimal vitaminaB12Mcg) { this.vitaminaB12Mcg = vitaminaB12Mcg; }
}
