# APLICACIÓN PARA LISTAR EVENTOS

## OBJETIVO:
El objetivo de este ejercicio es realizar una aplicación que permita al usuario gestionar un listado de eventos de manera sencilla.
Los usuarios podrán añadir y eliminar eventos a una lista.
Además, la aplicación tendrá soporte para español e inglés.

## ESTRUCTURA:

### Clases JAVA:

- **ActividadPrincipal.java**: Es la actividad principal de la aplicación. Carga los fragmentos y permite la navegación entre ellos. Inicializa el fragmento de la lista de eventos y configura el botón para agregar un nuevo evento.

- **FragmentoAgregarEvento.java**: Fragmento para agregar un nuevo evento. Contiene campos de entrada para los detalles del evento y botones para agregar o cancelar. Valida los campos obligatorios y guarda el evento en `SharedPreferences`.

- **FragmentoListaEventos.java**: Fragmento que muestra la lista de eventos en un `RecyclerView`. Carga los eventos desde `SharedPreferences` y permite eliminar eventos con un diálogo de confirmación.

- **AdaptadorEvento.java**: Adaptador para el `RecyclerView` que muestra la lista de eventos. Enlaza los datos de los eventos con las vistas de los elementos de la lista y maneja los clics en los elementos.

- **Evento.java**: Clase que representa un objeto Evento. Implementa `Parcelable` para permitir el paso de objetos entre actividades y fragmentos. Contiene atributos como nombre, descripción, dirección, precio, fecha y aforo.

- **PreferencesManager.java**: Clase que gestiona las preferencias de la aplicación y guarda los eventos en `SharedPreferences`. Proporciona métodos para guardar, cargar y eliminar eventos.

### Archivos XML:

- **actividad_principal.xml**: Layout de la actividad principal. Contiene un `TextView` para el título, un `FrameLayout` para el contenedor de fragmentos y un `LinearLayout` con un botón para agregar eventos.

- **fragmento_agregar_evento.xml**: Layout del fragmento para agregar eventos. Contiene varios `EditText` para los detalles del evento y botones para agregar o cancelar.

- **fragmento_lista_eventos.xml**: Layout del fragmento que muestra la lista de eventos. Contiene un `TextView` para el título y un `RecyclerView` para mostrar los eventos.

- **item_evento.xml**: Layout para los elementos individuales de la lista de eventos. Contiene `TextView` para mostrar el nombre, la descripción y el precio del evento.

- **item_background.xml**: Define el fondo de los elementos de la lista de eventos. Contiene un color de fondo, un borde y esquinas redondeadas.

## LINK:
https://github.com/Samuu10/Examen2Ejercicio2.git