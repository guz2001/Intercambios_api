package com.intercambios.intercambios_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase principal de la aplicación Intercambios API.
 *
 * <p>Expone una API REST para la gestión del sistema de listas de intercambios
 * alimentarios, incluyendo grupos, subgrupos, alimentos, nutrientes, cálculo
 * de requerimientos energéticos y selecciones de intercambio por paciente.</p>
 *
 * <p>La configuración CORS se lee desde {@code application.properties} mediante
 * la propiedad {@code cors.allowed-origin}, que a su vez puede provenir de la
 * variable de entorno {@code CORS_ALLOWED_ORIGIN}. Esto permite un origen
 * diferente por ambiente (desarrollo, pruebas, producción) sin modificar el código.</p>
 */
@SpringBootApplication
public class Application {

    /**
     * Origen HTTP permitido para las peticiones CORS.
     *
     * <p>Se inyecta desde {@code application.properties}:
     * {@code cors.allowed-origin=${CORS_ALLOWED_ORIGIN:http://localhost:4200}}</p>
     *
     * <p>En producción, establecer la variable de entorno
     * {@code CORS_ALLOWED_ORIGIN} con la URL exacta del frontend desplegado.</p>
     */
    @Value("${cors.allowed-origin}")
    private String corsAllowedOrigin;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configura la política CORS global para todos los endpoints bajo {@code /api/**}.
     *
     * <p>El origen permitido se obtiene de la propiedad inyectada {@code corsAllowedOrigin},
     * evitando el uso de {@code "*"} que aceptaría cualquier dominio y representaría
     * un riesgo de seguridad en producción.</p>
     *
     * @return configurador de CORS registrado como bean de Spring
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(corsAllowedOrigin)
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
