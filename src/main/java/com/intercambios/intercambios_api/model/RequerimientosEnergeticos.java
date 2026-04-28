package com.intercambios.intercambios_api.model;

/**
 * Modelo (no persistido) que encapsula los parámetros necesarios para calcular
 * el requerimiento energético diario de un paciente.
 *
 * <p>Esta clase no es una entidad JPA: actúa como objeto de transferencia
 * entre el controlador y {@code RequerimientosEnergeticosService}.</p>
 *
 * <p>El campo {@code sexo} ahora usa el enum {@link Sexo} (en lugar de
 * {@code String}) para garantizar que sólo se acepten valores válidos
 * y alinear este modelo con la entidad {@link Paciente}.</p>
 *
 * <p>El constructor sin argumentos es requerido por Jackson para
 * deserializar el cuerpo JSON de la petición HTTP.</p>
 */
public class RequerimientosEnergeticos {

    /** Peso corporal del paciente en kilogramos. */
    private double peso;

    /** Talla del paciente en centímetros. */
    private double altura;

    /** Edad del paciente en años completos. */
    private int edad;

    /**
     * Sexo biológico del paciente.
     * Determina cuál ecuación de Harris-Benedict aplica el servicio.
     */
    private Sexo sexo;

    /**
     * Factor de actividad física que multiplica la TMB para obtener el GET.
     * Ver valores estándar en {@link Paciente#getFactorActividad()}.
     */
    private double factorActividad;

    /** Constructor sin argumentos requerido por Jackson para deserialización JSON. */
    public RequerimientosEnergeticos() {}

    public RequerimientosEnergeticos(double peso, double altura, int edad,
                                     Sexo sexo, double factorActividad) {
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

    public Sexo getSexo() { return sexo; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }

    public double getFactorActividad() { return factorActividad; }
    public void setFactorActividad(double factorActividad) { this.factorActividad = factorActividad; }
}
