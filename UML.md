# UML PARKING - Versión Mejorada

## Clases Abstractas

### **Persona**

#### **Atributos**

- `- nombre: String`
- `- cedula: int`
- `- direccion: String`

#### **Metodos**

- `+ toString(): String`

### **RegistroMovimientos** (Abstracta)

#### **Atributos**

- `- numMovimiento: int`
- `- fecha: String`
- `- hora: String`
- `- nota: String`
- `- empleado: Empleado`
- `- vehiculo: Vehiculo`

#### **Metodos**

- `+ toString(): String`

---

## Clases Concretas

### **Cliente** (extends Persona)

#### **Atributos**

- `- celular: int`
- `- añoCliente: int`

#### **Metodos**

- `+ toString(): String`

### **Empleado** (extends Persona)

#### **Atributos**

- `- numEmpleado: int`

#### **Metodos**

- `+ toString(): String`

### **Vehiculo**

#### **Atributos**

- `- matricula: String`
- `- marca: String`
- `- modelo: String`
- `- estado: String`

#### **Metodos**

- `+ setEstado(estado: String): void`
- `+ toString(): String`

### **Contrato**

#### **Atributos**

- `- valorMensual: double`
- `- empleadoContrato: Empleado`
- `- clienteContrato: Cliente`
- `- vehiculoContrato: Vehiculo`
- `- numContrato: int`
- `- fechaInicio: String`

#### **Metodos**

- `+ setNumContrato(): int`
- `+ toString(): String`

### **Entrada** (extends RegistroMovimientos)

#### **Atributos**

- `- salidaAsociada: Salida`

#### **Metodos**

- `+ setSalidaAsociada(salida: Salida): void`
- `+ tieneSalida(): boolean`
- `+ toString(): String`

### **Salida** (extends RegistroMovimientos)

#### **Atributos**

- `- entrada: Entrada`

#### **Metodos**

- `+ toString(): String`

### **ServicioAdicional** (extends RegistroMovimientos)

#### **Atributos**

- `- tipoServicio: String`
- `- costoServicio: double`

#### **Metodos**

- `+ toString(): String`

### **Sistema** implements Serializable

#### **Atributos**

- `- listaClientes: ArrayList<Cliente>`
- `- listaVehiculos: ArrayList<Vehiculo>`
- `- listaEmpleados: ArrayList<Empleado>`
- `- listaContratos: ArrayList<Contrato>`
- `- listaEntradas: ArrayList<Entrada>`
- `- listaSalidas: ArrayList<Salida>`
- `- listaServiciosAdicionales: ArrayList<ServicioAdicional>`
- `- proxNumContrato: int`
- `- proxNumServicio: int`
- `- proxNumEntrada: int`
- `- proxNumSalida: int`
- `- manejador: PropertyChangeSupport`

#### **Metodos**

<!-- Gestión de Listeners -->

- `+ addPropertyChangeListener(listener: PropertyChangeListener): void`

<!-- Gestión de Clientes -->

- `+ registrarCliente(cliente: Cliente): boolean`
- `+ eliminarCliente(cedula: int): boolean`
- `+ buscarClientePorCedula(cedula: int): Cliente`
- `+ existeClienteConCedula(cedula: int): boolean`
- `+ getListaClientes(): ArrayList<Cliente>`

<!-- Gestión de Vehículos -->

- `+ registrarVehiculo(vehiculo: Vehiculo): boolean`
- `+ eliminarVehiculo(matricula: String): boolean`
- `+ buscarVehiculoPorMatricula(matricula: String): Vehiculo`
- `+ existeVehiculoConMatricula(matricula: String): boolean`
- `+ getListaVehiculos(): ArrayList<Vehiculo>`
- `+ getVehiculosDisponiblesParaEntrada(): ArrayList<Vehiculo>`

<!-- Gestión de Empleados -->

- `+ registrarEmpleado(empleado: Empleado): boolean`
- `+ eliminarEmpleado(cedula: int): boolean`
- `+ buscarEmpleadoPorCedula(cedula: int): Empleado`
- `+ existeEmpleadoConCedula(cedula: int): boolean`
- `+ getListaEmpleados(): ArrayList<Empleado>`

<!-- Gestión de Contratos -->

- `+ registrarContrato(contrato: Contrato): boolean`
- `+ eliminarContrato(numContrato: int): boolean`
- `+ buscarContratoPorNumContrato(numContrato: int): Contrato`
- `+ existeContratoConNumContrato(numContrato: int): boolean`
- `+ vehiculoTieneContrato(vehiculo: Vehiculo): boolean`
- `+ vehiculoTiempoEnParking(vehiculo: Vehiculo): String`
- `+ getListaContratos(): ArrayList<Contrato>`

<!-- Movimientos: Entradas -->

- `+ registrarEntrada(entrada: Entrada): boolean`
- `+ buscarEntradaPorNumMovimiento(numMovimiento: int): Entrada`
- `+ buscarEntradaPorMatricula(matricula: String): Entrada`
- `+ existeEntradaConNumMovimiento(numMovimiento: int): boolean`
- `+ vehiculoEstaEnParking(matricula: String): boolean`
- `+ getListaEntradas(): ArrayList<Entrada>`
- `+ getEntradasSinSalida(): ArrayList<Entrada>`

<!-- Movimientos: Salidas -->

- `+ registrarSalida(salida: Salida, entrada: Entrada): boolean`
- `+ getListaSalidas(): ArrayList<Salida>`

<!-- Movimientos: Servicios Adicionales -->

- `+ registrarServicio(servicio: ServicioAdicional): boolean`
- `+ getListaServiciosAdicionales(): ArrayList<ServicioAdicional>`
- `+ buscarServicioPorMatricula(matricula: String): ServicioAdicional`

<!-- Reportes y Estadísticas -->

- `- convertirDuracionAMinutos(duracion: String): long`
- `+ filtrarMovimientos(movimientos: ArrayList<Object>, incluirEntradas: boolean, incluirSalidas: boolean, incluirServicios: boolean): ArrayList<Object>`
- `+ ordenarMovimientos(movimientos: ArrayList<Object>, ascendente: boolean): ArrayList<Object>`
- `+ exportarMovimientosATxt(movimientos: ArrayList<Object>, matricula: String): void`
- `+ calcularDiferenciaTiempo(fechaEntrada: String, horaEntrada: String, fechaSalida: String, horaSalida: String): String`
- `+ obtenerMovimientosPorPeriodo(fecha: String, horaInicio: int, horaFin: int): ArrayList<Object>`

<!-- Persistencia y Datos de Prueba -->

- `+ cargarDatosPrueba(): void`

- `+ getProxNumContrato(): int`
- `+ getProxNumEntrada(): int`
- `+ getProxNumSalida(): int`
- `+ getProxNumServicio(): int`

- `+ existenDatosGuardados(): boolean`
- `+ guardarDatos(): void`
- `+ cargarDatos(): Sistema`

---






## Relaciones

### Herencia

- **Persona** es la clase padre de:

  - **Cliente**
  - **Empleado**

- **RegistroMovimientos** es la clase padre de:
  - **Entrada**
  - **Salida**
  - **ServicioAdicional** ← **NUEVO**

### Agregación (Sistema)

El **Sistema** contiene listas de los siguientes objetos:

- **Cliente** (`listaClientes`)
- **Vehiculo** (`listaVehiculos`)
- **Empleado** (`listaEmpleados`)
- **Contrato** (`listaContratos`)
- **Salida** (`listaSalidas`)
- **Entrada** (`listaEntradas`)
- **ServicioAdicional** (`listaServiciosAdicionales`)

### Asociaciones

#### Contrato

- **Contrato** se asocia con:
  - **Empleado** (`empleadoContrato`)
  - **Cliente** (`clienteContrato`)
  - **Vehiculo** (`vehiculoContrato`)

#### RegistroMovimientos (Clase Abstracta)

- **RegistroMovimientos** se asocia con:
  - **Vehiculo** (`vehiculo`)
  - **Empleado** (`empleado`)

#### Entrada

- **Entrada** se asocia con:
  - **Salida** (`salidaAsociada`)
