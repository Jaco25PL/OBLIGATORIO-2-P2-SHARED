# Diagrama de Clases UML - Versión Mejorada

A continuación, se presenta el diagrama de clases UML optimizado con herencia mejorada y lista unificada de movimientos:

## Clases Abstractas

### **Persona**
* `nombre`: String
* `cedula`: int
* `direccion`: String

### **RegistroMovimientos** (Abstracta)
* `numMovimiento`: int
* `fecha`: String
* `hora`: String
* `notas`: String
* `empleado`: Empleado

---

## Clases Concretas

### **Cliente** (extends Persona)
* `celular`: int
* `añoCliente`: int

### **Empleado** (extends Persona)
* `numEmpleado`: int

### **Vehiculo**
* `matricula`: String
* `marca`: String
* `modelo`: String
* `estado`: String

### **Contrato**
* `valorMensual`: double
* `empleadoContrato`: Empleado
* `clienteContrato`: Cliente
* `vehiculoContrato`: Vehiculo
* `numContrato`: int
* `fechaInicio`: String

### **Entrada** (extends RegistroMovimientos)
* `vehiculo`: Vehiculo
* `salidaAsociada`: Salida  ← **MANTENER AQUÍ**

### **Salida** (extends RegistroMovimientos)
* `estadoVehiculo`: String

### **ServicioAdicional** (extends RegistroMovimientos)
* `vehiculo`: Vehiculo
* `tipoServicio`: String
* `costoServicio`: double

### **Sistema**
* `listaClientes`: List<Cliente>
* `listaVehiculos`: List<Vehiculo>
* `listaEmpleados`: List<Empleado>
* `listaContratos`: List<Contrato>
* `listaMovimientos`: List<RegistroMovimientos>  ← **LISTA UNIFICADA**
* `proxNumContrato`: int
* `proxNumMovimiento`: int  ← **NUMERACIÓN UNIFICADA**

---

## Relaciones

### Herencia
* **Persona** es la clase padre de:
  - **Cliente**
  - **Empleado**

* **RegistroMovimientos** es la clase padre de:
  - **Entrada**
  - **Salida**
  - **ServicioAdicional**  ← **NUEVO**

### Agregación (Sistema)

El **Sistema** contiene listas de los siguientes objetos:
* **Cliente** (`listaClientes`)
* **Vehiculo** (`listaVehiculos`)
* **Empleado** (`listaEmpleados`)
* **Contrato** (`listaContratos`)
* **RegistroMovimientos** (`listaMovimientos`) ← **LISTA UNIFICADA**

### Asociaciones

#### Contrato
* **Contrato** se asocia con:
  - **Empleado** (`empleadoContrato`)
  - **Cliente** (`clienteContrato`)
  - **Vehiculo** (`vehiculoContrato`)

#### RegistroMovimientos (Clase Abstracta)
* **RegistroMovimientos** se asocia con:
  - **Vehiculo** (`vehiculo`)
  - **Empleado** (`empleado`)

#### Entrada
* **Entrada** se asocia con:
  - **Salida** (`salidaAsociada`)

---

## Ventajas de esta Mejora

### 1. **Eliminación de Código Duplicado**
- `fecha`, `hora`, `notas` ahora están solo en `RegistroMovimientos`
- `vehiculo` y `empleado` se mueven a la clase padre
- Numeración unificada con `numMovimiento`

### 2. **Lista Unificada de Movimientos**
- Una sola lista `List<RegistroMovimientos>` en lugar de tres listas separadas
- Facilita enormemente los reportes requeridos en la consigna
- Mejor uso del polimorfismo

### 3. **Reportes Simplificados**
```java
// Ejemplo de uso para reportes
public List<RegistroMovimientos> getMovimientosPorVehiculo(Vehiculo vehiculo) {
    return listaMovimientos.stream()
        .filter(mov -> mov.getVehiculo().equals(vehiculo))
        .collect(Collectors.toList());
}

public List<RegistroMovimientos> getTodosLosMovimientos() {
    return new ArrayList<>(listaMovimientos);
}
```

### 4. **Código Más Limpio**
```java
// En lugar de
sistema.getListaEntradas().add(entrada);
sistema.getListaSalidas().add(salida);
sistema.getListaServiciosAdicionales().add(servicio);

// Ahora solo
sistema.getListaMovimientos().add(movimiento);
```

### 5. **Alineado con los Requerimientos**
- **Punto 8.1**: "todos los movimientos (entradas, salidas, servicios adicionales)"
- **Punto 8.2**: "grilla de movimientos que hubo"
- Ambos se implementan fácilmente con la lista unificada

---

## Implementación Sugerida

### Orden de Desarrollo:
1. **Persona** (clase abstracta)
2. **RegistroMovimientos** (clase abstracta)
3. **Cliente** y **Empleado** (extends Persona)
4. **Vehiculo** (clase independiente)
5. **Entrada**, **Salida**, **ServicioAdicional** (extends RegistroMovimientos)
6. **Contrato** (clase independiente)
7. **Sistema** (con lista unificada)

### Métodos Útiles en RegistroMovimientos:
```java
public abstract String getTipoMovimiento(); // "Entrada", "Salida", "Servicio"
public abstract String getDescripcion();    // Para reportes
```

Esta estructura es más profesional, mantiene el código DRY (Don't Repeat Yourself) y facilita enormemente la implementación de los reportes requeridos en el obligatorio.
