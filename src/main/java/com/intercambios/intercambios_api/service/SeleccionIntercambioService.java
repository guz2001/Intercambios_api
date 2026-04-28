package com.intercambios.intercambios_api.service;

import com.intercambios.intercambios_api.dto.SeleccionIntercambioRequest;
import com.intercambios.intercambios_api.model.Alimento;
import com.intercambios.intercambios_api.model.Paciente;
import com.intercambios.intercambios_api.model.SeleccionIntercambio;
import com.intercambios.intercambios_api.repository.AlimentoRepository;
import com.intercambios.intercambios_api.repository.PacienteRepository;
import com.intercambios.intercambios_api.repository.SeleccionIntercambioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio de lógica de negocio para registrar y consultar las selecciones
 * de intercambio alimentario de los pacientes.
 *
 * <p>Una selección de intercambio es la decisión de un paciente de reemplazar
 * {@code alimentoOrigen} por {@code alimentoReemplazo}. Este servicio valida que
 * ambos alimentos pertenezcan al mismo subgrupo antes de persistir el registro.</p>
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
     * <p>{@code @Transactional} garantiza que las lecturas de validación y la
     * escritura final ocurran en una sola transacción. Si cualquier paso falla,
     * toda la operación se revierte (rollback automático).</p>
     *
     * <p>El flujo de validación es:
     * <ol>
     *   <li>Verificar que el alimento origen exista.</li>
     *   <li>Verificar que el alimento reemplazo exista.</li>
     *   <li>Verificar que ambos pertenezcan al mismo subgrupo (intercambiables).</li>
     *   <li>Si se proporcionó {@code pacienteId}, verificar que el paciente exista.</li>
     *   <li>Asignar la marca de tiempo y persistir.</li>
     * </ol>
     * </p>
     *
     * @param request DTO con los IDs del alimento origen, reemplazo y paciente (opcional)
     * @return la entidad {@link SeleccionIntercambio} persistida con su ID asignado
     * @throws IllegalArgumentException si algún ID no existe o los alimentos
     *                                  no son intercambiables
     */
    @Transactional
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
    @Transactional(readOnly = true)
    public List<SeleccionIntercambio> listarTodas() {
        return seleccionRepository.findAll();
    }

    /**
     * Devuelve las selecciones de un paciente ordenadas de más reciente a más antigua.
     *
     * @param pacienteId ID del paciente
     * @return lista de selecciones del paciente en orden cronológico descendente
     */
    @Transactional(readOnly = true)
    public List<SeleccionIntercambio> listarPorPaciente(Integer pacienteId) {
        return seleccionRepository.findByPacienteIdOrderByFechaSeleccionDesc(pacienteId);
    }

    /**
     * Elimina una selección de intercambio por su ID.
     *
     * @param id ID de la selección a eliminar
     */
    @Transactional
    public void eliminar(Integer id) {
        seleccionRepository.deleteById(id);
    }
}
