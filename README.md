# Final Drilling M5 Java Trainee

Un sistema de hóróscopo Chino

### Usando

- Posgresql
- Java 17 (Ojala...)
- Apache Tomcat 10.1

### Paquetes

- dao: Implementa las interfaces para el crud
- interfaces: Regla las clases para ser implementadas por el DAO
- modelo: Las entidades que tienen contacto directo con la base de datos
- procesaconexion: La conexion con la base de datos
- servlets: Gestionan las peticiones Http
- filters: Las clases para restringir el acceso a ciertas vistas dependiendo del estado del usuario
- util: Clases utilitarias generales

### Flujo de accion
 1. Crear la logica de lectura del horoscopo (Listo)
 2. Crear la logica de usuario después para asegurar que podré contar con los datos del horóscopo en tiempo real para la asignación durante el proceso de registro