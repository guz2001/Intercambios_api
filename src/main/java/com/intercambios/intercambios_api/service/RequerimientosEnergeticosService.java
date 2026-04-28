package com.intercambios.intercambios_api.service;

import com.intercambios.intercambios_api.dto.RequerimientosEnergeticosResponse;
import com.intercambios.intercambios_api.model.RequerimientosEnergeticos;
import com.intercambios.intercambios_api.model.Sexo;
import org.springframework.stereotype.Service;

/**
 * Servicio que implementa el cálculo del requerimiento energético diario
 * de un paciente mediante la fórmula de Harris-Benedict revisada
 * (Roza &amp; Shizgal, 1984).
 *
 * <p>Las fórmulas según sexo son:
 * <ul>
 *   <li><b>Hombre:</b> TMB = 88.362 + (13.397 × kg) + (4.799 × cm) − (5.677 × años)</li>
 *   <li><b>Mujer:</b>  TMB = 447.593 + (9.247 × kg) + (3.098 × cm) − (4.330 × años)</li>
 * </ul>
 * El Gasto Energético Total (GET) se obtiene multiplicando la TMB
 * por el factor de actividad física del paciente.</p>
 */
@Service
public class RequerimientosEnergeticosService {

    /**
     * Calcula la Tasa Metabólica Basal (TMB) y el Gasto Energético Total (GET)
     * a partir de los datos antropométricos del paciente.
     *
     * <p>Selecciona la ecuación de Harris-Benedict correspondiente al sexo
     * indicado en {@link RequerimientosEnergeticos#getSexo()} y aplica
     * el factor de actividad para obtener el GET real.</p>
     *
     * @param datos parámetros antropométricos: peso (kg), altura (cm), edad (años),
     *              sexo y factor de actividad
     * @return DTO con la TMB y el GET en kcal/día, redondeados a 2 decimales
     */
    public RequerimientosEnergeticosResponse calcularGET(RequerimientosEnergeticos datos) {
        double tmb;

        if (datos.getSexo() == Sexo.MASCULINO) {
            tmb = 88.362
                    + (13.397 * datos.getPeso())
                    + (4.799  * datos.getAltura())
                    - (5.677  * datos.getEdad());
        } else {
            tmb = 447.593
                    + (9.247 * datos.getPeso())
                    + (3.098 * datos.getAltura())
                    - (4.330 * datos.getEdad());
        }

        double get = tmb * datos.getFactorActividad();

        // Redondear a 2 decimales para presentación clínica
        tmb = Math.round(tmb * 100.0) / 100.0;
        get = Math.round(get * 100.0) / 100.0;

        return new RequerimientosEnergeticosResponse(tmb, get);
    }
}
