# Guía de Implementación de Serialización para el Sistema de Estacionamiento

Esta guía detalla los pasos necesarios para implementar la serialización de datos en la aplicación del sistema de estacionamiento, permitiendo guardar y recuperar el estado del sistema según lo especificado en la consigna.

## 1. Preparación de Clases para la Serialización

Para implementar la serialización en tu sistema, es necesario que ciertas clases implementen la interfaz `Serializable`. No es necesario implementarla en todas las clases, solo en las clases base y las independientes.

### Clases que deben implementar Serializable:

1. **Clases base/padres**:
   - `Sistema.java` (contiene todas las listas principales)
   - `Persona.java` (clase padre de Cliente y Empleado)
   - `RegistroMovimientos.java` (si es una clase contenedora importante)

2. **Clases independientes**:
   - `Contrato.java`
   - `ServicioAdicional.java`
   - `Vehiculo.java`
   - `Entrada.java`
   - `Salida.java`
   - `Minijuego.java`

3. **No es necesario** implementar explícitamente en clases hijas:
   - `Cliente.java` (hereda de Persona)
   - `Empleado.java` (hereda de Persona)

Para cada clase que necesita serialización, agregar:
```java
import java.io.Serializable;

public class NombreClase implements Serializable {
    // El serialVersionUID es opcional pero recomendado para control de versiones
    private static final long serialVersionUID = 1L;
    
    // Resto del código existente...
}
```

> **Nota importante:** Al serializar la instancia de `Sistema`, se serializarán automáticamente todos los objetos contenidos en sus listas, siempre que estos implementen `Serializable` directamente o lo hereden de su clase padre.

## 2. Implementar Métodos de Serialización en la Clase Sistema

Agregar los siguientes métodos a la clase `Sistema.java`:

```java
import java.io.*;

// En la clase Sistema
public void guardarDatos() throws IOException {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("DATOS.ser"))) {
        out.writeObject(this);
    }
}

public static Sistema cargarDatos() throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("DATOS.ser"))) {
        return (Sistema) in.readObject();
    } catch (FileNotFoundException e) {
        // Si no existe el archivo, retornar un sistema nuevo
        return new Sistema();
    }
}
```

## 3. Modificar el Controlador Principal

Agregar métodos en el controlador apropiado (posiblemente en una clase nueva o en una existente) para manejar las acciones de guardar y cargar:

```java
public boolean guardarSistema() {
    try {
        sistema.guardarDatos();
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean cargarSistema() {
    try {
        Sistema sistemaRecuperado = Sistema.cargarDatos();
        // Reemplazar el sistema actual con el recuperado
        this.sistema = sistemaRecuperado;
        // Notificar a los observadores sobre el cambio (si aplica)
        notificarCambios();
        return true;
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        return false;
    }
}
```

## 4. Implementar la Interfaz Gráfica

Agregar botones en la interfaz principal para guardar y cargar datos:

1. **Botón "Guardar Datos"**:
   - Mostrar un diálogo de confirmación.
   - Si se confirma, llamar al método `guardarSistema()` del controlador.
   - Mostrar un mensaje de éxito o fracaso.

2. **Botón "Recuperar Datos"**:
   - Mostrar un diálogo de advertencia indicando que se reemplazarán los datos actuales.
   - Si se confirma, llamar al método `cargarSistema()` del controlador.
   - Mostrar un mensaje de éxito o fracaso.
   - Actualizar todas las vistas para reflejar el nuevo estado del sistema.

## 5. Manejar Campos no Serializables

Si alguna clase contiene campos que no deben ser serializados (como conexiones a bases de datos, interfaces gráficas, etc.), marcarlos como `transient`:

```java
private transient JFrame ventana; // No será serializado
```

## 6. Pruebas de Serialización

1. **Prueba de Guardado**:
   - Agregar algunos datos de prueba al sistema.
   - Guardar el sistema.
   - Verificar que se crea el archivo `DATOS.ser`.

2. **Prueba de Carga**:
   - Iniciar la aplicación con un sistema vacío.
   - Cargar los datos desde el archivo.
   - Verificar que los datos cargados coinciden con los que se guardaron previamente.

## 7. Manejo de Errores

Implementar manejo de excepciones adecuado para los casos en que:
- No se pueda guardar el archivo (problemas de permisos, disco lleno, etc.)
- No se pueda cargar el archivo (archivo corrupto, incompatibilidad de versiones, etc.)
- No exista el archivo al intentar cargarlo (primer uso de la aplicación)

## Consideraciones Adicionales

- **Ubicación del archivo**: Considerar una ubicación adecuada para el archivo `DATOS.ser`, posiblemente en una carpeta de datos dentro del proyecto.
- **Respaldo**: Implementar un sistema de respaldo antes de sobrescribir el archivo existente.
- **Logs**: Registrar las operaciones de guardado y carga en un archivo de log para facilitar la depuración.

## Ejemplo de Flujo de Interacción

1. Usuario hace clic en "Guardar Datos"
2. Aparece mensaje: "¿Está seguro de que desea guardar los datos actuales?"
3. Usuario confirma
4. Sistema serializa los datos y muestra: "Datos guardados correctamente"

1. Usuario hace clic en "Recuperar Datos"
2. Aparece mensaje: "ADVERTENCIA: Se reemplazarán todos los datos actuales. ¿Desea continuar?"
3. Usuario confirma
4. Sistema deserializa los datos y muestra: "Datos recuperados correctamente"
5. Todas las vistas se actualizan con los datos recuperados
