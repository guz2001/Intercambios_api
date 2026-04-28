package com.intercambios.intercambios_api.model;

/**
 * Enumeración de los grupos de población objetivo de un {@link SubgrupoAlimento}.
 *
 * <p>Cada valor encapsula la cadena exacta almacenada en la columna ENUM de MySQL,
 * evitando valores inválidos en tiempo de compilación y eliminando la necesidad
 * de manejar strings libres en la capa de negocio.</p>
 *
 * <p>La conversión entre el valor Java y la cadena de la base de datos
 * la realiza {@code PoblacionConverter} de forma transparente.</p>
 */
public enum Poblacion {

    /** Aplica a niños y adultos de forma combinada. */
    NINOS_ADULTOS("ninos_adultos"),

    /** Aplica exclusivamente a menores de 2 años, con fórmulas pediátricas especiales. */
    MENORES_2_ANOS("menores_2_anos"),

    /** Aplica únicamente a adultos. */
    ADULTOS("adultos"),

    /** Aplica únicamente a niños mayores de 2 años. */
    NINOS("ninos"),

    /** Aplica a toda la población sin distinción de edad. */
    GENERAL("general");

    /** Valor exacto almacenado en la columna ENUM de MySQL. */
    private final String valor;

    Poblacion(String valor) {
        this.valor = valor;
    }

    /** @return cadena almacenada en base de datos para este valor del enum */
    public String getValor() {
        return valor;
    }

    /**
     * Obtiene el enum correspondiente a la cadena almacenada en la base de datos.
     *
     * @param valor cadena leída desde la columna ENUM
     * @return el valor del enum que corresponde
     * @throws IllegalArgumentException si la cadena no coincide con ningún valor conocido
     */
    public static Poblacion fromValor(String valor) {
        for (Poblacion p : values()) {
            if (p.valor.equals(valor)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Valor de población no reconocido: " + valor);
    }
}
