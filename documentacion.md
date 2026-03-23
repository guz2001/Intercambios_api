================================================================
DOCUMENTACIÓN DE LA API — LISTA DE INTERCAMBIOS NUTRICIONALES
Backend: Spring Boot 3.5.11 | Java 21 | MySQL 8
Base URL: http://localhost:8080/api
================================================================


----------------------------------------------------------------
1. GRUPOS DE ALIMENTOS
   Endpoint base: /api/grupos
----------------------------------------------------------------

GET /api/grupos
Descripción : Retorna la lista completa de los 12 grupos de alimentos.
Parámetros  : Ninguno.
Respuesta   : Array JSON con objetos GrupoAlimento.

Ejemplo respuesta:
[
{ "id": 1, "nombre": "Lácteos",    "descripcion": null },
{ "id": 2, "nombre": "Sustitutos", "descripcion": null },
...
]

Campos del objeto:
- id          (Integer) : Identificador único del grupo.
- nombre      (String)  : Nombre del grupo de alimentos.
- descripcion (String)  : Descripción opcional, puede ser null.


----------------------------------------------------------------
2. SUBGRUPOS DE ALIMENTOS
   Endpoint base: /api/subgrupos
----------------------------------------------------------------

GET /api/subgrupos
Descripción : Retorna todos los subgrupos de todas las categorías.
Parámetros  : Ninguno.
Respuesta   : Array JSON con objetos SubgrupoAlimento.

Ejemplo respuesta:
[
{
"id": 1,
"grupo": { "id": 1, "nombre": "Lácteos", "descripcion": null },
"nombre": "Leches enteras frescas y fermentadas",
"poblacion": "ninos_adultos",
"kcalPromedio": 139.0
},
...
]

----------

GET /api/subgrupos/por-grupo/{grupoId}
Descripción : Retorna todos los subgrupos que pertenecen a un grupo específico.
Parámetros  :
- grupoId (Integer, path variable) : ID del grupo padre.
Respuesta   : Array JSON con objetos SubgrupoAlimento filtrados por grupoId.

Ejemplo de uso:
GET /api/subgrupos/por-grupo/1
→ Devuelve los 5 subgrupos del grupo Lácteos.

Campos del objeto:
- id           (Integer) : Identificador único del subgrupo.
- grupo        (Object)  : Objeto GrupoAlimento al que pertenece.
- nombre       (String)  : Nombre descriptivo del subgrupo.
- poblacion    (String)  : Grupo de edad/población destino.
  Valores posibles:
  · ninos_adultos   → Niños y adultos
  · menores_2_anos  → Menores de 2 años
  · adultos         → Solo adultos
  · ninos           → Solo niños
  · general         → Todas las edades
- kcalPromedio (Double)  : Promedio calórico del subgrupo (referencia para intercambios).


----------------------------------------------------------------
3. ALIMENTOS
   Endpoint base: /api/alimentos
----------------------------------------------------------------

GET /api/alimentos
Descripción : Retorna todos los alimentos de la base de datos.
Parámetros  : Ninguno.
Respuesta   : Array JSON con todos los objetos Alimento.

Nota: Este endpoint devuelve los 185+ alimentos. Para consultas
específicas usar los endpoints filtrados abajo.

----------

GET /api/alimentos/por-subgrupo/{subgrupoId}
Descripción : Retorna todos los alimentos que pertenecen a un subgrupo.
Este endpoint implementa la lógica de "ver alimentos de una categoría".
Parámetros  :
- subgrupoId (Integer, path variable) : ID del subgrupo.
Respuesta   : Array JSON con objetos Alimento filtrados por subgrupoId.

Ejemplo de uso:
GET /api/alimentos/por-subgrupo/1
→ Devuelve los 7 alimentos del subgrupo "Leches enteras frescas y fermentadas".

----------

GET /api/alimentos/{id}/intercambios
Descripción : *** ENDPOINT PRINCIPAL DE LA APLICACIÓN ***
Dado un alimento, retorna todos los alimentos intercambiables.
Un alimento es intercambiable si pertenece al mismo subgrupo
y por tanto tiene un aporte calórico similar.
El alimento seleccionado NO aparece en los resultados.
Parámetros  :
- id (Integer, path variable) : ID del alimento de referencia.
Respuesta   : Array JSON con objetos Alimento del mismo subgrupo, excluyendo el original.

Ejemplo de uso:
GET /api/alimentos/1/intercambios
→ Alimento id=1 es "Kumis de leche entera" (subgrupo 1: Leches enteras).
→ Devuelve los otros 6 alimentos del mismo subgrupo:
Leche de cabra entera cruda, Leche de vaca entera cruda,
Leche de vaca entera en polvo, Kumis comercial,
Leche pasteurizada, Yogurt regular.

Consulta que ejecuta internamente (JPQL):
SELECT a FROM Alimento a
WHERE a.subgrupo.id = (
SELECT a2.subgrupo.id FROM Alimento a2 WHERE a2.id = :alimentoId
)
AND a.id != :alimentoId

Campos del objeto Alimento:
- id                (Integer) : Identificador único.
- subgrupo          (Object)  : Objeto SubgrupoAlimento al que pertenece.
- nombre            (String)  : Nombre del alimento.
- porcionGramos     (Double)  : Peso de la porción de referencia en gramos.
- unidadMedida      (String)  : Unidad de medida (ml, g, unidad, etc).
- descripcionMedida (String)  : Descripción práctica de la porción
  (ej: "1 vaso pequeño", "3 cucharas soperas colmadas").


----------------------------------------------------------------
4. NUTRIENTES
   Endpoint base: /api/nutrientes
----------------------------------------------------------------

GET /api/nutrientes/alimento/{alimentoId}
Descripción : Retorna el perfil nutricional completo de un alimento específico.
Relación uno a uno con la tabla alimento.
Parámetros  :
- alimentoId (Integer, path variable) : ID del alimento.
Respuesta   : Objeto NutrienteAlimento con todos los macros y micronutrientes.
Si el alimento no tiene registro nutricional retorna HTTP 404.

Ejemplo de uso:
GET /api/nutrientes/alimento/1
→ Devuelve el perfil nutricional del Kumis de leche entera.

Ejemplo respuesta:
{
"id": 1,
"alimento": {
"id": 1,
"nombre": "Kumis de leche entera",
"porcionGramos": 200.0,
"unidadMedida": "ml",
"descripcionMedida": "1 vaso pequeño"
},
"kcal": 161.0,
"proteinaG": 7.0,
"grasaTotalG": 5.8,
"agsG": 4.2,
"choG": 20.3,
"fibraG": null,
"calcioMg": 212.0,
"hierroMg": 0.2,
"sodioMg": 92.0,
"potasioMg": 310.0,
"vitaminaCMg": 2.0,
"vitaminaB12Mcg": 0.74
}

Campos del objeto NutrienteAlimento:
── Macronutrientes ──────────────────────────────────────────
- kcal          (Double) : Calorías totales de la porción.
- proteinaG     (Double) : Proteína en gramos.
- grasaTotalG   (Double) : Grasa total en gramos.
- agsG          (Double) : Ácidos grasos saturados en gramos.
- choG          (Double) : Carbohidratos en gramos.
- fibraG        (Double) : Fibra dietaria en gramos (puede ser null).

── Minerales ────────────────────────────────────────────────
- calcioMg      (Double) : Calcio en miligramos.
- hierroMg      (Double) : Hierro en miligramos.
- sodioMg       (Double) : Sodio en miligramos.
- potasioMg     (Double) : Potasio en miligramos.

── Vitaminas ────────────────────────────────────────────────
- vitaminaCMg   (Double) : Vitamina C en miligramos.
- vitaminaB12Mcg(Double) : Vitamina B12 en microgramos.

Códigos HTTP posibles:
- 200 OK       → Nutrientes encontrados y retornados.
- 404 Not Found → No existe registro nutricional para ese alimento.


================================================================
FLUJO COMPLETO — Cómo usa la API la nutricionista
================================================================

Paso 1: Cargar los grupos al abrir la app
→ GET /api/grupos
→ Muestra los 12 grupos como botones en pantalla.

Paso 2: La nutricionista selecciona un grupo (ej: Lácteos, id=1)
→ GET /api/subgrupos/por-grupo/1
→ Muestra los 5 subgrupos de Lácteos.

Paso 3: La nutricionista selecciona un subgrupo (ej: Leches enteras, id=1)
→ GET /api/alimentos/por-subgrupo/1
→ Muestra los 7 alimentos del subgrupo.

Paso 4: La nutricionista selecciona un alimento (ej: Kumis, id=1)
→ GET /api/alimentos/1/intercambios
→ Devuelve los 6 intercambios posibles.
→ Por cada intercambio, el frontend llama a:
GET /api/nutrientes/alimento/{id}
→ Muestra kcal, proteína, grasa, CHO, calcio, hierro y vitamina C
de cada opción.


================================================================
CONFIGURACIÓN TÉCNICA
================================================================

Base URL local  : http://localhost:8080/api
Puerto          : 8080 (por defecto Spring Boot / Tomcat embebido)
Formato         : JSON (application/json)
Autenticación   : Ninguna (API pública)
CORS            : Habilitado para todos los orígenes (*)

Base de datos:
Motor         : MySQL 8.0.45
Nombre        : lista_intercambios
Charset       : utf8mb4 / utf8mb4_spanish_ci

Dependencias principales:
- spring-boot-starter-web      3.5.11
- spring-boot-starter-data-jpa 3.5.11
- mysql-connector-j            9.6.0
- hibernate-core               6.6.42


================================================================
ESTRUCTURA DE PAQUETES
================================================================

com.intercambios.intercambios_api
├── model/
│   ├── GrupoAlimento.java         → @Entity tabla grupo_alimento
│   ├── SubgrupoAlimento.java      → @Entity tabla subgrupo_alimento
│   ├── Alimento.java              → @Entity tabla alimento
│   └── NutrienteAlimento.java     → @Entity tabla nutriente_alimento
├── repository/
│   ├── GrupoAlimentoRepository    → JpaRepository<GrupoAlimento, Integer>
│   ├── SubgrupoAlimentoRepository → findByGrupoId()
│   ├── AlimentoRepository         → findBySubgrupoId(), findIntercambios()
│   └── NutrienteAlimentoRepository→ findByAlimentoId()
├── service/
│   ├── GrupoAlimentoService       → listarTodos()
│   ├── SubgrupoAlimentoService    → listarTodos(), listarPorGrupo()
│   ├── AlimentoService            → listarTodos(), listarPorSubgrupo(), buscarIntercambios()
│   └── NutrienteAlimentoService   → buscarPorAlimento()
├── controller/
│   ├── GrupoAlimentoController    → GET /api/grupos
│   ├── SubgrupoAlimentoController → GET /api/subgrupos, /por-grupo/{id}
│   ├── AlimentoController         → GET /api/alimentos, /por-subgrupo/{id}, /{id}/intercambios
│   └── NutrienteAlimentoController→ GET /api/nutrientes/alimento/{id}
└── Application.java               → @SpringBootApplication + CORS config
:)))

================================================================
FIN DE LA DOCUMENTACIÓN
================================================================