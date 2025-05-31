#  UML PARKING - Versión Mejorada

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
* `vehiculo`: Vehiculo

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
* `salidaAsociada`: Salida

### **Salida** (extends RegistroMovimientos)

### **ServicioAdicional** (extends RegistroMovimientos)
* `tipoServicio`: String
* `costoServicio`: double

### **Sistema**
* `listaClientes`: List<Cliente>
* `listaVehiculos`: List<Vehiculo>
* `listaEmpleados`: List<Empleado>
* `listaContratos`: List<Contrato>
* `listaMovimientos`: List<RegistroMovimientos>  ← **LISTA UNIFICADA**
* `proxNumContrato`: int
* `proxNumServicio`: int
* `proxNumEntrada`: int
* `proxNumSalida`: int 

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