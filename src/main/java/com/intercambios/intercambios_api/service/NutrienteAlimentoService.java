package com.intercambios.intercambios_api.service;

import com.intercambios.intercambios_api.model.NutrienteAlimento;
import com.intercambios.intercambios_api.repository.NutrienteAlimentoRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Servicio de lógica de negocio para la entidad {@link NutrienteAlimento}.
 *
 * <p>Provee acceso al perfil nutricional detallado de un alimento.
 * La relación alimento-nutriente es {@code OneToOne}, por lo que el resultado
 * se envuelve en {@code Optional} para manejar el caso en que el perfil
 * aún no haya sido cargado.</p>
 */
@Service
public class NutrienteAlimentoService {

    private final NutrienteAlimentoRepository repository;

    public NutrienteAlimentoService(NutrienteAlimentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Busca el perfil nutricional de un alimento por su ID.
     *
     * <p>El controlador usa este {@code Optional} para responder
     * {@code 200 OK} si existe o {@code 404 Not Found} si no.</p>
     *
     * @param alimentoId ID del alimento
     * @return {@code Optional} con los nutrientes si existen, vacío si no
     */
    public Optional<NutrienteAlimento> buscarPorAlimento(Integer alimentoId) {
        return repository.findByAlimentoId(alimentoId);
    }
}
