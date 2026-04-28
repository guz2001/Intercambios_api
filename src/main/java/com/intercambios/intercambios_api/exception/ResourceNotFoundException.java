package com.intercambios.intercambios_api.exception;

/**
 * Excepción que indica que un recurso solicitado no existe en la base de datos.
 * Se mapea a {@code 404 Not Found} en {@link GlobalExceptionHandler}.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
