package com.intercambios.intercambios_api.model;

/**
 * Enumeración de los sexos biológicos utilizados en el cálculo
 * de requerimientos energéticos mediante la fórmula de Harris-Benedict.
 *
 * <p>Cada valor representa una ecuación diferente:</p>
 * <ul>
 *   <li>{@link #MASCULINO} – usa la ecuación masculina de Harris-Benedict</li>
 *   <li>{@link #FEMENINO}  – usa la ecuación femenina de Harris-Benedict</li>
 * </ul>
 *
 * <p><b>MEJORA:</b> A futuro se puede extender para incluir la categoría
 * {@code MENOR_2_ANOS}, que requiere una fórmula pediátrica distinta,
 * evitando así depender de la edad como discriminador en el servicio.</p>
 */
public enum Sexo {
    MASCULINO,
    FEMENINO
}
