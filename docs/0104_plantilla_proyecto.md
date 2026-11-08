# Plantilla: propuesta del Proyecto A

---

## 0 · Datos

| |                     |
|---|---------------------|
| **Nombre de la app** | MireyaEnMalta       |
| **Autor/a** | Mireya Gómez García |
| **Fecha** | 01/10/2026          |

---

## 1 · La idea en una frase

> Qué hace tu app y para quién, en una sola frase.
>
> Fórmula: «Una app que permite a [quién] hacer [qué] para [para qué].»

--- La app permite a turistas y estudiantes en Malta descrubrir los mejores lugares, locales, actividades de ocio y guardar sus favoritos
 para aprovechar al máximo su estancia.
## 2 · El problema

> ¿Qué problema resuelve? ¿Cómo se resuelve hoy sin tu app?

--- Moverse por Malta suele ser bastante caótico a la par que frustrante para los visitantes debido a las dificultades del transporte. Además de esta problemática, se le suma también la barrera idiomática, ya que esta isla cuenta con dos idiomas oficiales (inglés y maltés), lo que a veces complica el acceso a la información.

## 3 · Personas usuarias

> ¿Quién la va a usar? Describe a una persona concreta: edad, soltura con la
> tecnología, cuándo y dónde abre la app, cuánto tiempo le dedica y qué pasa
> si le falla.

---Eusebio y Rosa (ambos de 65 años). Son un matrimonio de jubilados españoles que viaja a Malta de vacaciones. Tienen una soltura básica sobre WhatsApp y otras  aplicaciones  sencillas.
No entienden inglés ni el maltés, asi que todas las mañanas antes de salir de su hotel pasan unos 20 minutos investigando y recopilando toda la información posible para conocer los lugares más espectaculares de la isla.
Necesitan letras medianamente grandes y que las aplicaciones sean lo más simples posibles para no perderse entre tantos botones y webs desconocidas.
Se les complica la app y toman la decisión de preguntar a las personas locales, terminando por depender del mapa que le habían dado en el hotel, el cual la información es bastante limitada.

## 4 · Funcionalidades

### Imprescindibles (sin esto la app no tiene sentido)

| # | Funcionalidad                                      |
|---|----------------------------------------------------|
| F1 | Catálogo e itinerarios de lugares destacados       |
| F2 | Gestionar y guardar una lista de lugares favoritos |
| F3 | Consulta del transporte                            |

### Opcionales (si sobra tiempo)

| # | Funcionalidad                     |
|---|-----------------------------------|
| O1 | Consulta del clima                |
| O2 | Consulta de medusas en las playas |

---

## 5 · Pantallas

| Pantalla | Para qué sirve | Se llega desde                                    |
|----------|----------------|---------------------------------------------------|
| | | Muestra categoría y lugares destacados (arranque) |
| | | Muestra foto, descripción, ubicación y favoritos  |
| | | Lista de los lugares preferidos                   |

---

## 6 · Bocetos

> Dibuja las pantallas principales. A mano y fotografiado es válido.
> Pega aquí las imágenes o indica el nombre de los archivos adjuntos.

![Boceto_pantallas_principales.png](../../../../Downloads/Boceto_pantallas_principales.png)

## 7 · Qué datos guarda la app

| Tipo de dato | Campos                               | Ejemplo    |
|--------------|--------------------------------------|------------|
| Lugar        | Nombre, descripción, ubicación, foto | Blue Lagoon |
| Favorito     | ID del lugar                         | Blue Lagon |

---

## 8 · Encaje con los requisitos del módulo

> Apartado obligatorio: ninguna casilla puede quedar vacía.

| Requisito                                                                               | Dónde encaja en tu app | Tema |
|-----------------------------------------------------------------------------------------|------------------------|------|
| **Persistencia de datos** — Los lugares favoritos se guardan aunque se cierrre la app   | | 4 |
| **Servicio web** — Consulta información (transporte, medusas y otros datos en internet) | | 5 |
| **Sensor o localización**      Uso de GPS para la ubicación                             | | 6 |
| **Contenido multimedia** — Los lugares incluyen foto                                    | | 7 |

---

## 9 · Riesgos

| Lo que me preocupa                                                                          | Plan B |
|-------------Que algunos servicios no funciones sin conexión-------------------------------|-----Guardar en una base de datos---|
| Que la aplicación sea demasiado compleja |                                                    Diseñar una interfaz sencilla|


---

## Antes de entregar

- [ ] La idea cabe en una frase.
- [ ] El público es una persona concreta, no «todo el mundo».
- [ ] Hay **3 o 4** funcionalidades imprescindibles, no diez.
- [ ] Cada funcionalidad imprescindible tiene su pantalla.
- [ ] Hay bocetos de las pantallas principales.
- [ ] **Las cuatro casillas del apartado 8 están rellenas.**
- [ ] Está identificado al menos un riesgo con su plan B.
