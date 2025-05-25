# Sugerencias a verificar para el 2do obligatorio. La lista no es exhaustiva ni los items son equivalentes.

### Documentación
1. **Diagrama UML** 
    - Contiene únicamente las clases del dominio, con sus atributos y notación UML correcta y completa. No incluye las clases de prueba ni interfaz. Diseño de clases correcto.
2. **Video en YouTube** 
    - Está el video, con los dos integrantes. Duración máxima 2 minutos. 
3. **Estilo codificación y estándares, comentarios**
    - Estilo de codificación y estándares correctos. Están los nombres de autores en todos los archivos. Correcta separación entre dominio e interfaz. No repite código. Correcta presentación, único PDF, archivos y carpetas ok. Está la carátula y completa, incluye fotos.

## Logica
4. **Sistema** 
    - Existe la clase Sistema, tiene las listas y la logica correspondiente al Sistema.

## Menues
5. **Título en la ventana principal** 
    - Tiene título y nombre de estudiantes
6. **Menú principal** 
    - Formato indicado
7. **Usabilidad, diseño de interfaz, ventanas no modales** 
    - Estetico, sin faltas, permite cerrar ventanas individuales

## Gestion
8. **Alta de Cliente** 
    - Valida campos y unicidad.
9. **Baja Cliente** 
    - Se puede seleccionar un cliente y borrarlo. Borra todos los contratos de ese cliente.
10. **Cliente: visualización** 
    - Lista de clientes, se actualiza al dar de alta o baja, se puede seleccionar y muestra sus datos.
11. **Alta de Vehículos** 
    - Valida campos y unicidad.
12. **Vehículo : visualización** 
    - Lista de vehículos se actualiza al dar de alta, se puede seleccionar y muestra sus datos.
13. **Alta Empleado** 
    - Valida campos y unicidad.
14. **Empleado: visualización** 
    - Lista de empleados se actualiza al dar de alta, se puede seleccionar y muestra sus datos.
15. **Alta de Contrato** 
    - Valida campos.
16. **Alta de Contrato: cliente y empleado** 
    - Se carga correctamente lista de vehiculos, clientes y empleados.
17. **Alta de Contrato: visualización** 
    - Lista de contratos se actualiza al dar de alta, se puede seleccionar y muestra sus datos.
18. **Alta de Contrato autonumerado** 
    - Al darse de alta automáticamente el número empezando desde 1.

## Movimientos
19. **Entrada: listas** 
    - Lista todos los vehiculos excepto los que ya entraron y los empleados.
20. **Alta Entrada** 
    - Se validan los campos y se informa si tiene contrato. Se realiza la entrada.
21. **Salida: lista** 
    - Lista las entradas que no tienen salida y los empleados.
22. **Alta Salida** 
    - Se validan los campos, informa el tiempo que estuvo en horas y minutos y se informa si tiene contrato. Se realiza la salida.
23. **Alta Servicios Adicionales**
    - Carga un combo con las opciones, la lista de vehiculos y de empleados. Se da alta.
24. **Consulta Servicios Adicionales**
    - Se muestran todos los servicios y al seleccionar uno muestra todos sus datos.

## Reportes
25. **Pestañas** 
    - Muestra los 3 reportes en 3 pestañas diferentes dentro de la ventana reportes.
26. **Historial**
    - Lista todos los vehiculos, permite seleccionar uno y muestra en la tabla toda la informacion. Permite ordenar por fecha creciente o decreciente, permite filtrar por tipo de movimiento.
28. **Historial : exportacion**
    - Genera un archivo TXT en la ubicación del proyecto con nombre igual a la matricula del vehiculo seleccionado. Cada linea del archivo tiene que ser una linea de la tabla como se ve en ese momento.
29. **Grilla de movimientos**
    - Permite ingresar una fecha y muestra una grilla de botones 4 filas y 3 columnas. Cada botón de la grilla debe tener color verde si tiene menos de 5 movimientos, amarillo de 5 a 8 movimientos y rojo si son más de 8 movimientos.
30. **Grilla de movimientos: click**
    - Al presionar cada botón aparece en una ventana emergente el reporte ordenado por fecha/hora, de todos los movimientos que hubo (entrada, salida, servicio adicional) en ese período, indicando matrícula y tipo de movimiento.
31. **Estadísticas generales**
    - Muestra las estadisticas: servicios adicionales más utilizados, estadías más largas, empleados con la menor cantidad de movimientos, clientes con mayor cantidad de vehículos.

## Datos
32. **Recuperación** 
    - Toman los datos del sistema que estén grabados en DATOS.ser y se sustituyen por los actuales, previa confirmación. Si no hay datos previos, se reinicia el sistema.
33. **Grabación** 
    - Persiste en ese momento todos los datos al archivo DATOS.ser, previa confirmación. El archivo estará ubicado en la misma carpeta del proyecto.

## Mini Juego
33. **Mini Juego** 
    - Hay un mini juego que utiliza mouse y o teclado.

## Funcionamiento general
34. Información de autores 
    - Muestra los datos completos de los autores, incluyendo fotos académicas.
35. Cambiar modo 
    - Cambia todas las ventanas a modo oscuro o vuelve al normal.
36. JAR 
    - Funciona, no tiene datos precargados.
37. Manejo de excepciones 
    - Correcto manejo.
38. Datos actualizados 
    - Datos siempre actualizados, uso de Observer.