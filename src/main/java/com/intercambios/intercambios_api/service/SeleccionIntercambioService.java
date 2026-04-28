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
 * Servicio de logica de negocio para registrar y consultar las selecciones
 * de intercambio alimentario de los pacientes.
 *
 * <p>Una seleccion de intercambio es la decision de un paciente de reemplazar
 * alimentoOrigen por alimentoReemplazo. Este servicio valida que ambos alimentos
 * pertenezcan al mismo subgrupo antes de persistir el registro.</p>
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
     * Valida y persiste una nueva seleccion de intercambio.
     *
     * <p>Flujo: verifica que origen y reemplazo existan, que sean del mismo subgrupo,
     * y opcionalmente que el paciente exista si se proporciona pacienteId.</p>
     *
     * @param request DTO con los IDs del alimento origen, reemplazo y paciente (opcional)
     * @return la entidad SeleccionIntercambio persistida con su ID asignado
     * @throws IllegalArgumentException si algun ID no existe o los alimentos no son intercambiables
     */
    @Transactional
    public SeleccionIntercambio guardar(SeleccionIntercambioRequest request) {
        Alimento origen = alimentoRepository.findById(request.getAlimentoOrigenId())
                .orElseThrow(() -> new IllegalArgumentException("Alimento origen no encontrado"));

        Alimento reemplazo = alimentoRepository.findById(request.getAlimentoReemplazoId())
                .orElseThrow(() -> new IllegalArgumentException("Alimento reemplazo no encontrado"));

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

    /** Devuelve el historial completo de selecciones de intercambio. */
    @Transactional(readOnly = true)
    public List<SeleccionIntercambio> listarTodas() {
        return seleccionRepository.findAll();
    }

    /** Devuelve las selecciones de un paciente ordenadas de mas reciente a mas antigua. */
    @Transactional(readOnly = true)
    public List<SeleccionIntercambio> listarPorPaciente(Integer pacienteId) {
        return seleccionRepository.findByPacienteIdOrderByFechaSeleccionDesc(pacienteId);
    }

    /** Elimina una seleccion de intercambio por su ID. */
    @Transactional
    public void eliminar(Integer id) {
        seleccionRepository.deleteById(id);
    }
}
