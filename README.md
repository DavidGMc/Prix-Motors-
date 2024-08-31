# PRIX MOTORS +

[![Prix Motors + en Play Store](https://img.shields.io/badge/Download-Play%20Store-green)](https://play.google.com/store/apps/details?id=com.androidavid.prixmotors)

**Prix Motors +** es una aplicación Android especializada en servicios automotrices, enfocada en **embragues**, Consume una API propia realizada en PHP temporalmente para ofrecer información y servicios de alta calidad.

## Tecnologías Utilizadas

- **Arquitectura MVVM:** Para una gestión eficiente y modular del código.
- **Retrofit:** Integración y consumo de API.
- **Room:** Almacenamiento de datos locales.
- **Firebase (Messaging, Remote Config):** Servicios en la nube y mensajería push.
- **Coil:** Procesamiento y carga de imágenes.
- **Anuncios Intersticiales:** Monetización.
- **Navigation Component:** Navegación intuitiva entre pantallas.
- **corrutinas** ejecutar de forma asíncrona.

## Instalación

1. Clona el repositorio y ábrelo con Android Studio.
2. Registra la app en [Firebase Console](https://console.firebase.google.com/).
3. Descarga el archivo `google-services.json`.
4. Copia el archivo JSON al nivel del módulo del proyecto `app`.
5. Ejecuta el proyecto en tu entorno de desarrollo.

## Capturas de Pantalla

### Fragment Home
Banner de noticias (mediante Remote Config de Firebase) y artículos nuevos.
![Fragment Home](https://www.androidavid.com/perfiles/prix_init.jpg)

### Pantalla Principal
Vista de la pantalla principal de la aplicación.
![Pantalla Principal](https://play-lh.googleusercontent.com/SW4EKteD0fH5q8fgj3mxlJmbWZqzvGuvpgGlr7BKQIzOi-At8B0bzSo4EUVOTST21F_J=w5120-h2880-rw)

### Fragment Prensas
Vista detallada de las prensas disponibles.
![Fragment Prensas](https://www.androidavid.com/perfiles/prix_prensas.jpg)

### Fragment Discos
Listado de discos de embrague.
![Fragment Discos](https://play-lh.googleusercontent.com/PSPPPgLeVphrWLI1kzT1BO2RdibvYyilaXYpMCAAy1vG_hQykvDiNCy1euQ72pTCPo-k=w5120-h2880-rw)

### Fragment Rodamientos
Selección de rodamientos.
![Fragment Rodamientos](https://play-lh.googleusercontent.com/yKi0G2xLYcAvYwjKRSvoEOfP1PrWkwRDzwo1rJK6IXKCO0Zh7CiSb2kYo_fNBYITipg=w1052-h592-rw)

### Fragment Notas
Crea, edita, y elimina tus propias referencias, que se guardan localmente en la base de datos del dispositivo 
![Fragment Notas](https://www.androidavid.com/perfiles/prix_notas.jpg)

### Fragment Detalles 
Detalles adicionales al seleccionar un artículo, como país de origen, medidas, etc.
![Fragment Details](https://www.androidavid.com/perfiles/prix_detalles.jpg)

## Funcionalidades de la App

- **Productos divididos por categorías de embragues.**
- **Búsqueda de productos** por nombre o marca de vehículo.
- **Búsqueda de notas** por título o descripción.
- **Detalles ampliados** al elegir un artículo de embrague.
- **Compartir imágenes** del embrague de tu auto o de tu interés mediante tus redes favoritas.

## Funcionalidades Futuras

- Más categorías como volantes, horquillas, filtros.
- Base de datos para kilometraje de tu auto y cambios de aceite.
- **Reconocimiento de imágenes** para detectar de qué vehículo es la imagen capturada.

---

Este `README.md` refleja solo una **parte del código** y las funcionalidades implementadas en la aplicación. Para más detalles, consulta la [Play Store](https://play.google.com/store/apps/details?id=com.androidavid.prixmotors).
