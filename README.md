# ClearSpend Course

Aplicación Java pequeña usada como instrumento pedagógico del curso de dirección de trabajo con Codex.

## Estado actual

Este repositorio contiene únicamente el checkpoint inicial del curso: **day1-start**.

La aplicación permite:

- consultar gastos;
- registrar gastos;
- editar gastos;
- observar si requieren aprobación.

La regla inicial de aprobación existe en el software y forma parte de lo que el alumno debe investigar durante el curso.

## Requisitos

- Java 17 o superior
- No es necesario instalar Maven globalmente: el proyecto incluye Maven Wrapper.
- IntelliJ IDEA

## Ejecutar

Desde IntelliJ, ejecutar `ClearSpendApplication`.

O desde terminal:

```bash
./mvnw spring-boot:run
```

Abrir:

`http://localhost:8080`

## Tests

```bash
./mvnw test
```

## Checkpoint

El branch/tag funcional que representa el punto de partida del curso es `course/day1-start`.
