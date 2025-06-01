# UML PARKING - Versión Mejorada

## Clases Abstractas

### **Persona**

#### **Atributos**

- `- nombre: String`
- `- cedula: int`
- `- direccion: String`

#### **Metodos**

- `+ getNombre(): String`
- `+ getCedula(): int`
- `+ getDireccion(): String`
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

- `+ getNumMovimiento(): int`
- `+ getFecha(): String`
- `+ getHora(): String`
- `+ getNota(): String`
- `+ getEmpleado(): Empleado`
- `+ getVehiculo(): Vehiculo`
- `+ toString(): String`

---

## Clases Concretas

### **Cliente** (extends Persona)

#### **Atributos**

- `- celular: int`
- `- añoCliente: int`

#### **Metodos**

- `+ getCelular(): int`
- `+ getAñoCliente(): int`
- `+ toString(): String`

### **Empleado** (extends Persona)

#### **Atributos**

- `- numEmpleado: int`

#### **Metodos**

- `+ getNumEmpleado(): int`
- `+ toString(): String`

### **Vehiculo**

#### **Atributos**

- `- matricula: String`
- `- marca: String`
- `- modelo: String`
- `- estado: String`

#### **Metodos**

- `+ getMatricula(): String`
- `+ getMarca(): String`
- `+ getModelo(): String`
- `+ getEstado(): String`
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

- `+ getValorMensual(): double`
- `+ getEmpleadoContrato(): Empleado`
- `+ getClienteContrato(): Cliente`
- `+ getVehiculoContrato(): Vehiculo`
- `+ getNumContrato(): int`
- `+ getFechaInicio(): String`
- `+ toString(): String`

### **Entrada** (extends RegistroMovimientos)

#### **Atributos**

- `- salidaAsociada: Salida`

#### **Metodos**

- `+ getSalidaAsociada(): Salida`
- `+ setSalidaAsociada(salida: Salida): void`
- `+ tieneSalida(): boolean`
- `+ toString(): String`

### **Salida** (extends RegistroMovimientos)

#### **Metodos**

- `+ toString(): String`

### **ServicioAdicional** (extends RegistroMovimientos)

#### **Atributos**

- `- tipoServicio: String`
- `- costoServicio: double`

#### **Metodos**

- `+ getTipoServicio(): String`
- `+ getCostoServicio(): double`
- `+ toString(): String`

### **Sistema**

#### **Atributos**

- `- listaClientes: List<Cliente>`
- `- listaVehiculos: List<Vehiculo>`
- `- listaEmpleados: List<Empleado>`
- `- listaContratos: List<Contrato>`
- `- listaEntradas: List<Entrada>`
- `- listaSalidas: List<Salida>`
- `- listaServiciosAdicionales: List<ServicioAdicional>`
- `- proxNumContrato: int`
- `- proxNumServicio: int`
- `- proxNumEntrada: int`
- `- proxNumSalida: int`

#### **Metodos**

<!--Gestión de Clientes-->

- `+ registrarCliente(nombre: String, cedula: int, direccion: String, celular: int, añoCliente: int): boolean`
- `+ eliminarCliente(cedula: int): boolean`
- `+ validarCedulaCliente(cedula: int): boolean`
- `+ buscarClientePorCedula(cedula: int): Cliente`
- `+ getListaClientes(): ArrayList<Cliente>`
<!--Gestión de Vehiculos-->
- `+ registrarVehiculo(matricula: String, marca: String, modelo: String, estado: String): boolean`
- `+ validarMatricula(matricula: String): boolean`
- `+ buscarVehiculoPorMatricula(matricula: String): Vehiculo`
- `+ getListaVehiculos(): ArrayList<Vehiculo>`
<!--Gestión de Empleados-->
- `+ registrarEmpleado(nombre: String, cedula: int, direccion: String, numEmpleado: int): boolean`
- `+ validarCedulaEmpleado(cedula: int): boolean`
- `+ buscarEmpleadoPorCedula(cedula: int): Empleado`
- `+ getListaEmpleados(): ArrayList<Empleado>`
<!--Gestión de Contratos-->
- `+ registrarContrato(valorMensual: double, empleado: Empleado, cliente: Cliente, vehiculo: Vehiculo, fechaInicio: String): boolean`
- `+ eliminarContratoPorCliente(cliente: Cliente): void`
- `+ buscarContratoPorVehiculo(vehiculo: Vehiculo): Contrato`
- `+ getListaContratos(): ArrayList<Contrato>`
<!--Movimientos-->
- `+ registrarEntrada(fecha: String, hora: String, nota: String, empleado: Empleado, vehiculo: Vehiculo): boolean`
- `+ registrarSalida(entrada: Entrada, fecha: String, hora: String, nota: String, empleado: Empleado): boolean`
- `+ registrarServicioAdicional(tipoServicio: String, fecha: String, hora: String, nota: String, vehiculo: Vehiculo, empleado: Empleado, costo: double): boolean`
- `+ buscarEntradaPorNumero(numEntrada: int): Entrada`
<!--Consulta Movimientos-->
- `+ getVehiculosDisponiblesParaEntrada(): ArrayList<Vehiculo>`
- `+ getEntradaSinSalida(): ArrayList<Entrada>`
- `+ calcularTiempoEstadia(entrada: Entrada, fechaSalida: String, horaSalida: String): String`
<!--Reportes-->
- `+ getHistorialPorVehiculo(vehiculo: Vehiculo): ArrayList<RegistroMovimientos>`
- `+ contarMovimientosPorPeriodo(fecha: String, horaInicio: int, horaFin: int): int`
- `+ getMovimientosPorPeriodo(fecha: String, horaInicio: int, horaFin: int): ArrayList<RegistroMovimientos>`
- `+ ordenarMovimientosPorFechaHora(movimientos: ArrayList<RegistroMovimientos>): ArrayList<RegistroMovimientos>`
- `+ getServiciosAdicionales(): ArrayList<ServicioAdicional>`
<!--Persistencia-->
- `+ grabarDatos(): boolean`
- `+ recuperarDatos(): boolean`


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
