package com.intercambios.intercambios_api.converter;

import com.intercambios.intercambios_api.model.Poblacion;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Convertidor JPA que mapea el enum {@link Poblacion} a la cadena almacenada
 * en la columna ENUM de MySQL y viceversa.
 *
 * <p>{@code autoApply = true} indica a Hibernate que aplique este convertidor
 * automáticamente a todos los campos de tipo {@link Poblacion} sin necesidad
 * de anotar cada campo individualmente con {@code @Convert}.</p>
 *
 * <p>Esto resuelve el desajuste entre los nombres en minúsculas de la base de datos
 * (ej. {@code "ninos_adultos"}) y las constantes en mayúsculas del enum Java
 * (ej. {@code NINOS_ADULTOS}), que {@code @Enumerated(EnumType.STRING)} no puede
 * manejar sin modificar el esquema existente.</p>
 */
@Converter(autoApply = true)
public class PoblacionConverter implements AttributeConverter<Poblacion, String> {

    /**
     * Convierte el enum Java al string que se escribe en la columna de la BD.
     *
     * @param poblacion valor del enum (puede ser null si el campo es opcional)
     * @return la cadena en minúsculas que espera la columna ENUM, o null
     */
    @Override
    public String convertToDatabaseColumn(Poblacion poblacion) {
        return poblacion == null ? null : poblacion.getValor();
    }

    /**
     * Convierte la cadena leída de la BD al valor del enum correspondiente.
     *
     * @param dbData cadena almacenada en la columna ENUM
     * @return el valor del enum, o null si el campo estaba vacío
     */
    @Override
    public Poblacion convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Poblacion.fromValor(dbData);
    }
}
