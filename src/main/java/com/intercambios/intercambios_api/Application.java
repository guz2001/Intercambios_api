package com.intercambios.intercambios_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase principal de la aplicación Intercambios API.
 *
 * <p>Expone una API REST para la gestión del sistema de listas de intercambios
 * alimentarios, incluyendo grupos, subgrupos, alimentos, nutrientes y las
 * selecciones de intercambio realizadas por los pacientes.</p>
 *
 * <p>La configuración CORS se centraliza aquí para permitir peticiones
 * desde clientes web durante el desarrollo.</p>
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Configura la política CORS global para todos los endpoints bajo {@code /api/**}.
	 *
	 * <p>Permite los métodos HTTP necesarios para operaciones CRUD completas.</p>
	 *
	 * <p><b>MEJORA:</b> {@code allowedOrigins("*")} acepta cualquier origen, lo cual
	 * es un riesgo de seguridad en producción. Reemplazar por la URL exacta del
	 * frontend (ej. {@code "http://localhost:4200"}) y usar variables de entorno
	 * para distintos ambientes.</p>
	 *
	 * @return configurador de CORS registrado como bean de Spring
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}
}
