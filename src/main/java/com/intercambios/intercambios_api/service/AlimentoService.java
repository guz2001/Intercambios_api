package com.intercambios.intercambios_api.service;

import com.intercambios.intercambios_api.model.Alimento;
import com.intercambios.intercambios_api.repository.AlimentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio de lógica de negocio para la entidad {@link Alimento}.
 *
 * <p>Actúa como intermediario entre {@code AlimentoController} y
 * {@code AlimentoRepository}, centralizando las reglas de negocio
 * relacionadas con alimentos y dejando el controlador libre de lógica.</p>
 *
 * <p>La inyección de dependencias se realiza por constructor (buena práctica:
 * facilita las pruebas unitarias al permitir pasar mocks sin necesidad de
 * un contexto de Spring).</p>
 */
@Service
public class AlimentoService {

    private final AlimentoRepository repository;

    public AlimentoService(AlimentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Devuelve el catálogo completo de alimentos.
     *
     * <p><b>MEJORA:</b> Si el catálogo crece, implementar paginación
     * ({@code Page<Alimento>}) para no cargar miles de registros en memoria.</p>
     *
     * @return lista con todos los alimentos registrados
     */
    public List<Alimento> listarTodos() {
        return repository.findAll();
    }

    /**
     * Devuelve los alimentos que pertenecen a un subgrupo alimentario específico.
     *
     * @param subgrupoId ID del subgrupo a filtrar
     * @return lista de alimentos del subgrupo; vacía si no hay ninguno
     */
    public List<Alimento> listarPorSubgrupo(Integer subgrupoId) {
        return repository.findBySubgrupoId(subgrupoId);
    }

    /**
     * Devuelve los alimentos que pueden intercambiarse con el alimento dado.
     *
     * <p>Dos alimentos son intercambiables si pertenecen al mismo subgrupo,
     * lo que garantiza equivalencia calórica por porción.</p>
     *
     * @param alimentoId ID del alimento de referencia
     * @return lista de alimentos intercambiables; vacía si el alimento
     *         no existe o es el único en su subgrupo
     */
    public List<Alimento> buscarIntercambios(Integer alimentoId) {
        return repository.findIntercambios(alimentoId);
    }
}
