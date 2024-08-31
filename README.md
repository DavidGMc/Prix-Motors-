# PRIX MOTORS +

[![Prix Motors + en Play Store](https://img.shields.io/badge/Download-Play%20Store-green)](https://play.google.com/store/apps/details?id=com.androidavid.prixmotors)

Prix Motors + es una aplicación Android especializada en servicios automotrices, enfocada en embragues. Consume una API propia realizada en PHP para ofrecer información y servicios de alta calidad.

## Tecnologías Utilizadas

- **Arquitectura MVVM:** Para una gestión eficiente y modular del código.
- **Retrofit:** Integración y consumo de API.
- **Room:** Almacenamiento de datos locales.
- **Firebase (Messaging, Remote Config):** Servicios en la nube y mensajería push.
- **Coil:** Procesamiento y carga de imágenes.
- **Anuncios Intersticiales:** Monetización.
- **Navigation Component:** Navegación intuitiva entre pantallas.

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

### Fragment Prensas
Vista detallada de las prensas disponibles.
![Fragment Prensas](https://www.androidavid.com/perfiles/prix_prensas.jpg)

### Fragment Discos
Listado de discos de embrague.
![Fragment Discos](https://www.androidavid.com/perfiles/prix_discos.jpg)

### Fragment Rodamientos
Selección de rodamientos.
![Fragment Rodamientos](https://www.androidavid.com/perfiles/prix_balineras.jpg)

### Fragment Notas
Crea, edita, y elimina tus propias referencias, que se guardan localmente en la base de datos del dispositivo (Room).
![Fragment Notas](https://www.androidavid.com/perfiles/prix_notas.jpg)

### Fragment Details
Detalles adicionales al seleccionar un artículo, como país de origen, medidas, etc.
![Fragment Details](https://www.androidavid.com/perfiles/prix_detalles.jpg)

---

Este `README.md` refleja solo una parte del código y las funcionalidades implementadas en la aplicación. Para más detalles, consulta la [Play Store](https://play.google.com/store/apps/details?id=com.androidavid.prixmotors).
