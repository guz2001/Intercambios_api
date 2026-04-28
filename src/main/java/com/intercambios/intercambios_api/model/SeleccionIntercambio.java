package com.intercambios.intercambios_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que registra la selección de un intercambio alimentario realizada
 * por un paciente o el nutricionista en su nombre.
 *
 * <p>Una selección de intercambio indica que el paciente decidió reemplazar
 * {@code alimentoOrigen} por {@code alimentoReemplazo}. Ambos alimentos deben
 * pertenecer al mismo {@link SubgrupoAlimento}, garantizando equivalencia calórica.</p>
 *
 * <p>El campo {@code paciente} es opcional: permite registrar selecciones
 * anónimas o de sesión sin que el paciente esté dado de alta en el sistema.</p>
 *
 * <p>Mapea la tabla {@code seleccion_intercambio}.</p>
 *
 * <p><b>MEJORA:</b> Si se requiere un historial por sesión de consulta,
 * agregar una relación con una futura entidad {@code Consulta} o {@code PlanAlimentario},
 * que agrupe múltiples selecciones de una misma cita clínica.</p>
 */
@Entity
@Table(name = "seleccion_intercambio")
public class SeleccionIntercambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Alimento original que el paciente desea sustituir.
     * Determina el subgrupo de referencia para validar el intercambio.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "alimento_origen_id", nullable = false)
    private Alimento alimentoOrigen;

    /**
     * Alimento elegido como reemplazo.
     * Debe pertenecer al mismo {@link SubgrupoAlimento} que {@code alimentoOrigen}.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "alimento_reemplazo_id", nullable = false)
    private Alimento alimentoReemplazo;

    /**
     * Paciente que realizó la selección. Campo opcional.
     * Si es {@code null}, la selección se considera anónima o de demostración.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    /**
     * Marca temporal en que se registró la selección.
     * Se asigna automáticamente en {@code SeleccionIntercambioService#guardar}.
     */
    @Column(name = "fecha_seleccion", nullable = false)
    private LocalDateTime fechaSeleccion;

    public SeleccionIntercambio() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Alimento getAlimentoOrigen() { return alimentoOrigen; }
    public void setAlimentoOrigen(Alimento alimentoOrigen) { this.alimentoOrigen = alimentoOrigen; }

    public Alimento getAlimentoReemplazo() { return alimentoReemplazo; }
    public void setAlimentoReemplazo(Alimento alimentoReemplazo) { this.alimentoReemplazo = alimentoReemplazo; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public LocalDateTime getFechaSeleccion() { return fechaSeleccion; }
    public void setFechaSeleccion(LocalDateTime fechaSeleccion) { this.fechaSeleccion = fechaSeleccion; }
}
