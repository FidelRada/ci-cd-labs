# ci-cd-labs

Repositorio de prácticas del módulo de CI/CD del diplomado.

## Laboratorio 1 — Primer Pipeline de Integración Continua

Este repo contiene el primer pipeline construido con GitHub Actions (`.github/workflows/pipeline.yml`). Se ejecuta automáticamente en cada push a `main` y:

- muestra un mensaje de bienvenida,
- muestra la fecha y hora de ejecución,
- muestra la versión de Git instalada,
- finaliza correctamente.

Servirá como base para los siguientes laboratorios del módulo.

## Laboratorio 2 — Branching, Pull Requests y Ejecución de CI

A partir de este laboratorio, los cambios ya no se envían directamente a `main`. El flujo de trabajo es:

1. Crear una rama de funcionalidad a partir de `main` (ej. `feature/update-readme`).
2. Realizar el cambio y hacer commit en esa rama.
3. Enviar la rama al repositorio remoto — esto dispara el pipeline de CI sobre la rama.
4. Abrir un Pull Request hacia `main` una vez que el pipeline pasa.
5. Fusionar el Pull Request solo si las validaciones de CI son exitosas (rama `main` protegida).

Este cambio en el `README.md` es precisamente el ejercicio práctico de ese flujo.

## Laboratorio 3 — Pruebas Automatizadas y Quality Gate

El repositorio incorpora ahora una aplicación real: una calculadora hecha con **Spring Boot 4.1 y Java 21**, construida con **Maven** (vía el wrapper `mvnw`, no hace falta instalar Maven).

### Estructura

```
src/main/java/bo/diplomado/calculadora/
├── CalculadoraApplication.java      # arranque de Spring Boot
├── service/CalculadoraService.java  # lógica de negocio
└── controller/CalculadoraController.java  # API REST

src/test/java/bo/diplomado/calculadora/
├── CalculadoraApplicationTests.java        # carga del contexto
├── service/CalculadoraServiceTest.java     # pruebas unitarias (JUnit 5)
└── controller/CalculadoraControllerTest.java
```

### Ejecutar en local

```bash
./mvnw clean test     # compila y ejecuta las pruebas
./mvnw verify         # además aplica el Quality Gate de cobertura
```

Los reportes quedan en `target/surefire-reports/` (resultados) y `target/site/jacoco/index.html` (cobertura).

### El pipeline

El workflow se reorganizó en dos etapas encadenadas:

| Etapa | Qué hace |
|---|---|
| **Build** | `./mvnw clean compile` — si el código no compila, el pipeline se detiene aquí. |
| **Test** | Solo corre si `Build` terminó bien (`needs: build`). Ejecuta las pruebas, genera el reporte de cobertura con **JaCoCo**, aplica el **Quality Gate** (cobertura mínima de líneas) y publica ambos reportes como artefactos descargables. |

Si alguna prueba falla, la etapa `Test` falla y la rama `main` — protegida — impide fusionar el Pull Request.
