package com.intercambios.intercambios_api.service;

import com.intercambios.intercambios_api.dto.SeleccionIntercambioRequest;
import com.intercambios.intercambios_api.model.Alimento;
import com.intercambios.intercambios_api.model.Paciente;
import com.intercambios.intercambios_api.model.SeleccionIntercambio;
import com.intercambios.intercambios_api.repository.AlimentoRepository;
import com.intercambios.intercambios_api.repository.PacienteRepository;
import com.intercambios.intercambios_api.repository.SeleccionIntercambioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio de lógica de negocio para registrar y consultar las selecciones
 * de intercambio alimentario de los pacientes.
 *
 * <p>Una selección de intercambio es la decisión de un paciente de reemplazar
 * un alimento ({@code alimentoOrigen}) por otro equivalente ({@code alimentoReemplazo})
 * dentro del mismo subgrupo alimentario. Este servicio valida esa equivalencia
 * antes de persistir el registro.</p>
 *
 * <p>La inyección de dependencias se realiza por constructor para facilitar
 * las pruebas unitarias con mocks sin necesidad del contexto de Spring.</p>
 */
@Service
public class SeleccionIntercambioService {

    private final SeleccionIntercambioRepository seleccionRepository;
    private final AlimentoRepository alimentoRepository;
    private final PacienteRepository pacienteRepository;

    public SeleccionIntercambioService(SeleccionIntercambioRepository seleccionRepository,
                                       AlimentoRepository alimentoRepository,
                                       PacienteRepository pacienteRepository) {
        this.seleccionRepository = seleccionRepository;
        this.alimentoRepository = alimentoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Valida y persiste una nueva selección de intercambio.
     *
     * <p>El flujo de validación es:
     * <ol>
     *   <li>Verificar que el alimento origen exista.</li>
     *   <li>Verificar que el alimento reemplazo exista.</li>
     *   <li>Verificar que ambos alimentos pertenezcan al mismo subgrupo
     *       (condición de intercambiabilidad).</li>
     *   <li>Si se proporcionó {@code pacienteId}, verificar que el paciente exista.</li>
     *   <li>Asignar la marca de tiempo actual y persistir.</li>
     * </ol>
     * </p>
     *
     * @param request DTO con los IDs del alimento origen, reemplazo y paciente (opcional)
     * @return la entidad {@link SeleccionIntercambio} persistida con su ID asignado
     * @throws IllegalArgumentException si algún ID no existe o los alimentos
     *                                  no son intercambiables
     *
     * <p><b>MEJORA:</b> Anotar el método con {@code @Transactional} para garantizar
     * que todas las lecturas de validación y la escritura ocurran en una sola
     * transacción de base de datos, evitando lecturas sucias.</p>
     */
    public SeleccionIntercambio guardar(SeleccionIntercambioRequest request) {
        Alimento origen = alimentoRepository.findById(request.getAlimentoOrigenId())
                .orElseThrow(() -> new IllegalArgumentException("Alimento origen no encontrado"));

        Alimento reemplazo = alimentoRepository.findById(request.getAlimentoReemplazoId())
                .orElseThrow(() -> new IllegalArgumentException("Alimento reemplazo no encontrado"));

        // Los alimentos sólo son intercambiables si pertenecen al mismo subgrupo
        if (!origen.getSubgrupo().getId().equals(reemplazo.getSubgrupo().getId())) {
            throw new IllegalArgumentException(
                    "Los alimentos no son intercambiables: pertenecen a subgrupos distintos");
        }

        SeleccionIntercambio seleccion = new SeleccionIntercambio();
        seleccion.setAlimentoOrigen(origen);
        seleccion.setAlimentoReemplazo(reemplazo);
        seleccion.setFechaSeleccion(LocalDateTime.now());

        if (request.getPacienteId() != null) {
            Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                    .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));
            seleccion.setPaciente(paciente);
        }

        return seleccionRepository.save(seleccion);
    }

    /**
     * Devuelve el historial completo de selecciones de intercambio.
     *
     * @return lista de todas las selecciones registradas
     */
    public List<SeleccionIntercambio> listarTodas() {
        return seleccionRepository.findAll();
    }

    /**
     * Devuelve todas las selecciones de intercambio registradas para un paciente.
     *
     * @param pacienteId ID del paciente
     * @return lista de selecciones del paciente; vacía si no tiene registros
     */
    public List<SeleccionIntercambio> listarPorPaciente(Integer pacienteId) {
        return seleccionRepository.findByPacienteId(pacienteId);
    }

    /**
     * Elimina una selección de intercambio por su ID.
     *
     * <p><b>MEJORA:</b> Verificar que el ID exista antes de eliminar y lanzar
     * una excepción descriptiva si no se encuentra, en lugar de que JPA
     * falle silenciosamente.</p>
     *
     * @param id ID de la selección a eliminar
     */
    public void eliminar(Integer id) {
        seleccionRepository.deleteById(id);
    }
}
