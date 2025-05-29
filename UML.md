# UML PARKING

## 1. Clase `Sistema`
### Atributos
	* `- listaClientes: List<Cliente>`
	* `- listaVehiculos: List<Vehiculo>`
	* `- listaEmpleados: List<Empleado>`
	* `- listaContratos: List<Contrato>`
	* `- listaEntradas: List<Entrada>`
	* `- listaSalidas: List<Salida>`
	* `- listaServiciosAdicionales: List<ServicioAdicional>`
	* `- proxNumContrato: int`
	* `- proxNumServicio: int`
	* `- proxNumEntrada: int`
	* `- proxNumSalida: int`

---

## 2. Clase `Contrato`
### Atributos
	* `- valorMensual: double`
	* `- empleadoContrato: Empleado`
	* `- clienteContrato: Cliente`
	* `- vehiculoContrato: Vehiculo`
	* `- numContrato: int`

---

## 3. Clase `Cliente`
### Atributos
	* `- nombre: String`
	* `- cedula: int`
	* `- direccion: String`
	* `- celular: int`
	* `- añoCliente: int`

---

## 4. Clase `Vehiculo`
### Atributos
	* `- matricula: String`
	* `- marca: String`
	* `- modelo: String`
	* `- estado: String`

---

## 5. Clase `Empleado`
### Atributos
	* `- nombre: String`
	* `- cedula: int`
	* `- direccion: String`
	* `- numEmpleado: int`

---

## 6. Clase `ServicioAdicional`
### Atributos
	* `- numServicio: int`
	* `- tipoServicio: String`
	* `- fecha: String`
	* `- hora: String`
	* `- vehiculoServicio: Vehiculo`
	* `- empleadoServicio: Empleado`
	* `- costoServicio: double`

---

## 7. Clase `Entrada`
### Atributos
	* `- numEntrada: int`
	* `- fecha: String`
	* `- hora: String`
	* `- notas: String`
	* `- empleadoRecibe: Empleado`
	* `- vehiculoEntrada: Vehiculo`
	* `- salidaAsociada: Salida`

---

## 8. Clase `Salida`
### Atributos
	* `- numSalida: int`
	* `- fecha: String`
	* `- hora: String`
	* `- notas: String`
	* `- empleadoEntrega: Empleado`

---

## Relaciones

### Agregación

### Sistema
* `Cliente * -----◇ Sistema` 
* `Entrada * -----◇ Sistema` 
* `Contrato * -----◇ Sistema` 
* `Vehiculo * -----◇ Sistema` 
* `Empleado * -----◇ Sistema` 
* `Salida * -----◇ Sistema` 
* `ServicioAdicional * -----<> Sistema`

### Asociacion

#### Contrato
* `Contrato * -----> 1 Cliente` 
* `Contrato * -----> 1 Vehiculo` 
* `Contrato * -----> 1 Empleado` 

### Entrada
* `Entrada * -----> 1 Vehiculo` 
* `Entrada * -----> 1 Empleado` 
* `Entrada 1 -----> 0..1 Salida` 

### Salida
* `Salida * -----> 1 Empleado`

### ServicioAdicional
* `ServicioAdicional * -----> 1 Vehiculo` 
* `ServicioAdicional * -----> 1 Empleado` 

---

