package com.intercambios.intercambios_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

/**
 * Manejador global de excepciones para todos los controladores REST.
 *
 * <p>{@code @RestControllerAdvice} centraliza el manejo de errores en un solo lugar,
 * eliminando la necesidad de bloques {@code try/catch} en cada controlador y
 * garantizando un formato de respuesta de error consistente en toda la API.</p>
 *
 * <p>Flujo: controlador → servicio lanza excepción → Spring intercepta aquí
 * → se retorna la respuesta HTTP con el código y mensaje apropiados.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja errores de validación de negocio lanzados por los servicios.
     *
     * <p>Se activa cuando el servicio lanza {@code IllegalArgumentException},
     * por ejemplo al recibir IDs inexistentes o alimentos no intercambiables.</p>
     *
     * @param ex excepción con el mensaje descriptivo del error de negocio
     * @return {@code 400 Bad Request} con el mensaje de error como cuerpo
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Maneja errores de validación de campos del cuerpo JSON (anotaciones {@code @NotNull},
     * {@code @NotBlank}, etc.).
     *
     * <p>Se activa cuando {@code @Valid} en el controlador rechaza un cuerpo de petición
     * con campos obligatorios ausentes. Reúne todos los mensajes de error en una sola
     * cadena separada por comas para que el cliente sepa exactamente qué campos fallaron.</p>
     *
     * @param ex excepción con el detalle de cada campo inválido
     * @return {@code 400 Bad Request} con la lista de errores de validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException ex) {
        String errores = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(errores);
    }
}
