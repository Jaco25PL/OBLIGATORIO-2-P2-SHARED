# PARKING

Se desea un sistema para un Parking Céntrico 24/7. En el parking hay clientes mensuales, que dejan su vehículo durante el tiempo que deseen por una tarifa mensual y también hay vehículos que ingresan al parking por horas.
En el parking los autos son dejados en la puerta del parking y los empleados los ubican en algún lugar disponible.
Al comienzo del sistema debe ser posible acceder al menú principal con:

* Gestión:
    1. Gestión de Clientes.
    2. Gestión de Vehículos.
    3. Gestión de Empleados.
    4. Gestión de Contratos.

* Movimientos:
    5. Entradas.
    6. Salidas.
    7. Servicios Adicionales.

* Varios:
    8. Reportes.
    9. Recuperación de datos.
    10. Grabación de datos.
    11. MiniJuego.
    12. Información de Autores.

* Terminar:
    13. Salir.

Se describe cada uno:

1) Gestión de Clientes: se debe poder registrar y eliminar clientes mensuales. Se indica nombre, cédula (única, no se realiza otra validación), dirección, celular y el año desde el cual son clientes. Se debe poder visualizar todos los clientes en una lista y al seleccionar uno, se ponen sus datos en la pantalla.

2) Gestión de Vehículos se debe poder registrar vehículos. Cada vehículo tiene matrícula (debe ser única, no se realiza ninguna otra validación sobre ella), marca, modelo y estado (string). Se debe poder visualizar todos los vehículos en una lista, y al elegir uno, se cargan sus datos en la pantalla.

3) Gestión de Empleados: Registrar empleados: nombre, cédula (única, no se realiza otra validación), dirección y número de empleado. Se debe poder ver la lista de los empleados y al elegir uno, se ven sus datos en pantalla.

4) Gestión de Contratos: se puede asociar un vehículo a un cliente registrado, en ese caso se indica el empleado que realiza el contrato y el valor mensual. Los contratos se autonumeran desde 1. Si se elimina el cliente, se elimina/n automáticamente también su/sus contratos. Se debe poder visualizar todos los contratos y al elegir uno, mostrar sus datos en pantalla.

5) Entradas: Se elige el vehículo de una lista (que no esté ya dentro del Parking), se indica fecha, hora (en formato HH:MM, 24 hs), un texto para notas (por ejemplo, para dejar constancia que una rueda está desinflada) y se elige el empleado que recibe y estaciona el vehículo. El sistema debe informar si tiene contrato o no.

6) Salida: Se elige una entrada de una lista (se muestran sólo las entradas que no registraron la salida), se elige de una lista el empleado que trae el vehículo hasta la puerta, se registra fecha, hora (formato: HH:MM, 24 hs, y debe ser posterior a la entrada) y un comentario sobre el estado del vehículo. Informar el tiempo (en horas y minutos) que estuvo el vehículo en relación a la entrada y si tiene contrato o no.

7) Servicios Adicionales. Es posible dar de alta servicios adicionales a vehículos. Se elige el tipo de servicio de un combo con: “lavado”, “cambio de rueda”, “limpieza de tapizado”, “cambio de luces”, “otro”, se indica fecha, hora, se eligen vehículo y empleado que lo realiza de listas y costo. Se debe poder consultar los servicios realizados con todos sus datos, al seleccionar uno mostrar todos sus datos en pantalla.

8) Reportes. En una misma ventana se visualizan en diferentes pestañas:

    1. Historial por vehículo: se elige un vehículo y se muestra en una tabla todos sus movimientos (entradas, salidas, servicios adicionales). Se debe poder ordenar por fecha/hora creciente o decreciente y tambien filtrar qué tipo de movimiento se desea visualizar. También se puede exportar a un archivo .txt de nombre la matrícula (ej. “ABC123.txt”) en el mismo directorio del proyecto y contiene cada línea presentada en la tabla.

    2. Grilla de movimientos. Se debe poder indicar una fecha y se muestra una grilla de botones con 4 filas (horas 0-5:59, 6:00 a 11:59, 12:00 a 17:59 y 18:00 a 23:59) y 3 columnas (una para el día solicitado y otras dos para los dos días siguientes). Al presionar cada botón aparece en una ventana emergente el reporte ordenado por fecha/hora, de todos los movimientos que hubo (entrada, salida, servicio adicional) en ese período, indicando matrícula y tipo de movimiento. Cada botón de la grilla debe tener color verde si tiene menos de 5 movimientos, amarillo de 5 a 8 movimientos y rojo si son más de 8 movimientos.

    3. Estadísticas generales: servicios adicionales más utilizados, estadías más largas, empleados con la menor cantidad de movimientos, clientes con mayor cantidad de vehículos.

    Nota: para la pestaña b) Movimientos, incluir un panel (“panelMovimientos”), con grid layout de 4 filas y 3 columnas. Los botones se pueden crear dinámicamente dentro de estructuras repetitivas: 
        
        JButton nuevo = new JButton(" ");
    nuevo.setMargin(new Insets(-5, -5, -5, -5));
    nuevo.setBackground(Color.BLACK);
    nuevo.setForeground(Color.WHITE);
    nuevo.setText( "texto"); // texto ejemplo, a completar
    nuevo.addActionListener(new MovListener());
    panelMovimientos.add(nuevo);

    Agregar los import requeridos (ej. import java.awt.event.*, import java.awt.* e import javax.swing.*.).
    Incluir dentro de la ventana esta clase:

        private class MovListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
            // este código se ejecutará al presionar el botón, obtengo cuál botón
                JButton cual = ((JButton) e.getSource());
                // código a completar según el botón presionado
            }
        }

9) Recuperación de datos. Al elegir Recuperar, se toman los datos del sistema que estén serializados en DATOS.ser y se sustituyen por los actuales, previa confirmación. Si no hay datos previos, se reinicia el sistema.

10) Grabación de datos. Al elegir Grabación, se serializan en ese momento todos los datos al archivo DATOS.ser, previa confirmación. El archivo estará ubicado en la misma carpeta del proyecto.

11) MiniJuego. Disponer de un MiniJuego da un espacio lúdico para distenderse en los momentos en que no hay actividad en el parking. Crear un mini juego interactivo (como por ejemplo para esquivar obstáculos o hacer clic en lugares aleatorios). Debe incluir el uso de mouse y, o teclado. NOTA: para este mini juego se considera válido utilizar propuestas generadas por ChatGPT o adaptadas, atribuyendo su autoría.

12) Información de autores: Poner los datos completos de los autores, incluyendo fotos académicas.

13) Salir. Termina el sistema, previa confirmación. 

En la ventana principal debe estar como título los nombres y números de estudiante de los autores y también debe haber un botón para cambiar modo claro o modo oscuro. Esta configuración se aplica en todas las ventanas. El modo oscuro tiene color de fondo negro y el modo claro tiene color de fondo blanco.

Se pide:

Implementar en Java un sistema para brindar todas las funcionalidades presentadas. La interfaz debe ser estilo Windows, en ventanas independientes y con las opciones presentadas. El diseño de las ventanas queda a cargo del equipo. Se deben realizar todas las validaciones necesarias. Se requiere que toda la información esté permanentemente actualizada en pantalla. Entregar además un archivo .jar que contenga toda la aplicación para funcionar correctamente, no debe contener datos precargados.

Para la entrega: subir un archivo zip/rar de hasta 40 MB con tres carpetas:

1) carpeta del código. Dentro debe estar el proyecto completo en NetBeans, incluyendo los fuentes java. Los fuentes deben incluir el nombre de los autores al comienzo de cada clase. Importante: El proyecto debe ser ANT (NO MAVEN ni otros formatos). No debe estar comprimido.

2) carpeta del ejecutable. Contiene exclusivamente el archivo .jar, sin datos precargados ni archivos adicionales.

3) carpeta de la documentación. La documentación es un UNICO pdf que contiene:
    1. Carátula con foto académica e identificación de los 2 integrantes del equipo (las 2 personas deben pertenecer al mismo grupo de clase)
    2. Diagrama de clases (UML) exclusivamente del dominio, especificando relaciones, atributos y métodos
    3. Link a un video en Youtube que describa el sistema y muestre las principales funcionalidades. La duración máxima es de 2 minutos y deben verse los integrantes del equipo.