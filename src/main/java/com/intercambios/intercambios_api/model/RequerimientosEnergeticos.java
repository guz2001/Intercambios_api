package com.intercambios.intercambios_api.model;

/**
 * Modelo (no persistido) que encapsula los parámetros necesarios para calcular
 * el requerimiento energético diario de un paciente.
 *
 * <p>Esta clase no es una entidad JPA: sirve como objeto de transferencia
 * entre el controlador y el servicio de cálculo energético.</p>
 *
 * <p>El cálculo se basa en la fórmula de Harris-Benedict revisada (Roza & Shizgal, 1984):
 * <ul>
 *   <li><b>Hombre:</b> TMB = 88.362 + (13.397 × peso) + (4.799 × altura) − (5.677 × edad)</li>
 *   <li><b>Mujer:</b>  TMB = 447.593 + (9.247 × peso) + (3.098 × altura) − (4.330 × edad)</li>
 * </ul>
 * GET = TMB × factorActividad</p>
 *
 * <p><b>MEJORA (crítica):</b> El campo {@code sexo} es un {@code String} libre,
 * pero ya existe el enum {@link Sexo} en este mismo paquete. Cambiar el tipo
 * a {@link Sexo} para evitar valores inválidos y alinear este modelo con
 * la entidad {@link Paciente}.</p>
 *
 * <p><b>MEJORA:</b> El cálculo de la TMB y el GET debería implementarse
 * directamente en {@code RequerimientosEnergeticosService}, recibiendo
 * esta clase como parámetro y devolviendo el resultado como un DTO de respuesta.</p>
 */
public class RequerimientosEnergeticos {

    /** Peso corporal del paciente en kilogramos. */
    private double peso;

    /** Talla del paciente en centímetros. */
    private double altura;

    /** Edad del paciente en años completos. */
    private int edad;

    /**
     * Sexo del paciente como cadena de texto.
     *
     * <p><b>MEJORA:</b> Reemplazar por {@link Sexo} para garantizar
     * que sólo se acepten valores {@code MASCULINO} o {@code FEMENINO}.</p>
     */
    private String sexo;

    /**
     * Factor de actividad física que multiplica la TMB para obtener el GET.
     * Ver valores estándar en {@link Paciente#getFactorActividad()}.
     */
    private double factorActividad;

    public RequerimientosEnergeticos(double peso, double altura, int edad,
                                     String sexo, double factorActividad) {
        this.peso = peso;
        this.altura = altura;
        this.edad = edad;
        this.sexo = sexo;
        this.factorActividad = factorActividad;
    }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public double getFactorActividad() { return factorActividad; }
    public void setFactorActividad(double factorActividad) { this.factorActividad = factorActividad; }
}
