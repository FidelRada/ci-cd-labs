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
