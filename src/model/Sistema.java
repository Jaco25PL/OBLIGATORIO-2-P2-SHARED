/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Sistema implements Serializable{

    private static final long serialVersionUID = 1L; // Para la serialización, version del objeto

    
    //Atributos
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Vehiculo> listaVehiculos;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<Contrato> listaContratos;
    private ArrayList<Entrada> listaEntradas;
    private ArrayList<Salida> listaSalidas;
    private ArrayList<ServicioAdicional> listaServiciosAdicionales;

    // Contadores para autonumerar
    private int proxNumContrato;
    private int proxNumEntrada;
    private int proxNumSalida;
    private int proxNumServicio;
    
    // Lista de observers
    // transitent para que no se serializen los observers
    private transient PropertyChangeSupport manejador;

    //Constructor
    public Sistema() {
        // Listas inicializadas
        this.listaClientes = new ArrayList<>();
        this.listaVehiculos = new ArrayList<>();
        this.listaEmpleados = new ArrayList<>();
        this.listaContratos = new ArrayList<>();
        this.listaEntradas = new ArrayList<>();
        this.listaSalidas = new ArrayList<>();
        this.listaServiciosAdicionales = new ArrayList<>();

        // Contadores inicializados
        this.proxNumContrato = 1;
        this.proxNumEntrada = 1;
        this.proxNumSalida = 1;
        this.proxNumServicio = 1;

        this.manejador = new PropertyChangeSupport(this);
    }

    // Métodos para manejar listeners
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (manejador == null) {
            manejador = new PropertyChangeSupport(this);
        }
        manejador.addPropertyChangeListener(listener);
    }

    //Metodos
    //GESTION DE CLIENTES
    public boolean registrarCliente(Cliente cliente){ //REGISTRAR
        boolean registrado = false;
        if (!existeClienteConCedula(cliente.getCedula())) {
            registrado = true;
            listaClientes.add(cliente);
            manejador.firePropertyChange("clienteCreado", null, null);
        }
        return registrado;
    }

    public boolean eliminarCliente(int cedula){ //ELIMINAR
        boolean eliminado = false;
        if (existeClienteConCedula(cedula)) {
            Cliente cliente = buscarClientePorCedula(cedula);
            
            Iterator<Contrato> it = listaContratos.iterator();
            while (it.hasNext()) {
                Contrato contrato = it.next();
                if (contrato.getClienteContrato().getCedula() == cedula) {
                    it.remove();
                }
            }
            
            listaClientes.remove(cliente);
            eliminado = true;
            
            manejador.firePropertyChange("clienteEliminado", null, null);
        }
        return eliminado;
    }

    public Cliente buscarClientePorCedula(int cedula){ //BUSCAR
        Cliente clienteEncontrado = null;
        Iterator<Cliente> it = listaClientes.iterator();
        while(it.hasNext() && clienteEncontrado == null){
            Cliente cliente = it.next();
            if (cliente.getCedula() == cedula) {
                clienteEncontrado = cliente;
            }
        }
        return clienteEncontrado;
    }

    public boolean existeClienteConCedula(int cedula){ //EXISTE
        boolean existe = false;
        if(buscarClientePorCedula(cedula) != null){
            existe = true;
        }
        return existe;
    }

    public ArrayList<Cliente> getListaClientes() { //GET
        return listaClientes;
    }

    //GESTION DE VEHICULOS
    public boolean registrarVehiculo(Vehiculo vehiculo){ //REGISTRAR
        boolean registrado = false;
        if (!existeVehiculoConMatricula(vehiculo.getMatricula())) {
            registrado = true;
            listaVehiculos.add(vehiculo);

            manejador.firePropertyChange("vehiculoCreado", null, null);
        }
        return registrado;
    }

    public boolean eliminarVehiculo(String matricula){ //ELIMINAR
        boolean eliminado = false;
        if (existeVehiculoConMatricula(matricula)) {
            Vehiculo vehiculo = buscarVehiculoPorMatricula(matricula);
            
            listaVehiculos.remove(vehiculo);
            eliminado = true;

            manejador.firePropertyChange("vehiculoEliminado", null, null);
        }
        return eliminado;
    }

    public Vehiculo buscarVehiculoPorMatricula(String matricula){ //BUSCAR
        Vehiculo vehiculoEncontrado = null;
        Iterator<Vehiculo> it = listaVehiculos.iterator();
        while(it.hasNext() && vehiculoEncontrado == null){
            Vehiculo vehiculo = it.next();
            if (vehiculo.getMatricula().equals(matricula)) {
                vehiculoEncontrado = vehiculo;
            }
        }
        return vehiculoEncontrado;
    }

    public boolean existeVehiculoConMatricula(String matricula){ //EXISTE
        boolean existe = false;
        if(buscarVehiculoPorMatricula(matricula) != null){
            existe = true;
        }
        return existe;
    }

    public ArrayList<Vehiculo> getListaVehiculos() { //GET
        return listaVehiculos;
    }

    //GESTION DE EMPLEADOS
    public boolean registrarEmpleado(Empleado empleado){ //REGISTRAR
        boolean registrado = false;
        if (!existeEmpleadoConCedula(empleado.getCedula())) {
            registrado = true;
            listaEmpleados.add(empleado);

            manejador.firePropertyChange("empleadoCreado", null, null);
        }
        return registrado;
    }

    public boolean eliminarEmpleado(int cedula){ //ELIMINAR
        boolean eliminado = false;
        if (existeEmpleadoConCedula(cedula)) {
            Empleado empleado = buscarEmpleadoPorCedula(cedula);
            
            listaEmpleados.remove(empleado);
            eliminado = true;

            manejador.firePropertyChange("empleadoEliminado", null, null);
        }
        return eliminado;
    }

    public Empleado buscarEmpleadoPorCedula(int cedula){ //BUSCAR
        Empleado empleadoEncontrado = null;
        Iterator<Empleado> it = listaEmpleados.iterator();
        while(it.hasNext() && empleadoEncontrado == null){
            Empleado empleado = it.next();
            if (empleado.getCedula() == cedula) {
                empleadoEncontrado = empleado;
            }
        }
        return empleadoEncontrado;
    }

    public boolean existeEmpleadoConCedula(int cedula){ //EXISTE
        boolean existe = false;
        if(buscarEmpleadoPorCedula(cedula) != null){
            existe = true;
        }
        return existe;
    }

    public ArrayList<Empleado> getListaEmpleados() { //GET
        return listaEmpleados;
    }

    //GESTION DE CONTRATOS
    public boolean registrarContrato(Contrato contrato){ //REGISTRAR
        contrato.setNumContrato(proxNumContrato);
        listaContratos.add(contrato);
        proxNumContrato++;

        manejador.firePropertyChange("contratoCreado", null, null);
        return true;
    }

    public boolean eliminarContrato(int numContrato){ //ELIMINAR
        boolean eliminado = false;
        if (existeContratoConNumContrato(numContrato)) {
            Contrato contrato = buscarContratoPorNumContrato(numContrato);
            
            listaContratos.remove(contrato);
            eliminado = true;
            manejador.firePropertyChange("contratoEliminado", null, null);
        }
        return eliminado;
    }

    public Contrato buscarContratoPorNumContrato(int numContrato){ //BUSCAR
        Contrato contratoEncontrado = null;
        Iterator<Contrato> it = listaContratos.iterator();
        while(it.hasNext() && contratoEncontrado == null){
            Contrato contrato = it.next();
            if (contrato.getNumContrato() == numContrato) {
                contratoEncontrado = contrato;
            }
        }
        return contratoEncontrado;
    }

    public boolean existeContratoConNumContrato(int numContrato){ //EXISTE
        boolean existe = false;
        if(buscarContratoPorNumContrato(numContrato) != null){
            existe = true;
        }
        return existe;
    }
    
    public boolean vehiculoTieneContrato(Vehiculo vehiculo){
        boolean tiene = false;
        
        if (buscarVehiculoPorMatricula(vehiculo.getMatricula()) != null) {
            
            Iterator<Contrato> it = listaContratos.iterator();
            while (it.hasNext() && !tiene) {
                Contrato contrato = it.next();
                if (contrato.getVehiculoContrato().getMatricula().equals(vehiculo.getMatricula())) {
                    tiene = true;
                }
            }
        }
        return tiene;
    }
    
    public String vehiculoTiempoEnParking(Vehiculo vehiculo){
        String tiempo = "0:00";

        // Busco la entrada más reciente del vehiculo que no tenga salida
        Entrada entradaActiva = null;
        Iterator<Entrada> it = listaEntradas.iterator();
        while(it.hasNext()){
            Entrada entrada = it.next();
            if (entrada.getVehiculo().getMatricula().equals(vehiculo.getMatricula()) && !entrada.tieneSalida()){
                // Si encontramos una entrada sin salida, la guardamos
                // (como recorremos en orden, la última que encontremos será la más reciente)
                entradaActiva = entrada;
            }
        }

        if (entradaActiva != null) {
            // Obtengo fecha y hora actual para calcular
            java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
            String fechaActual = String.format("%02d/%02d/%04d", ahora.getDayOfMonth(), ahora.getMonthValue(), ahora.getYear());
            String horaActual = String.format("%02d:%02d", ahora.getHour(), ahora.getMinute());
            
            // Verificar si la fecha de entrada es posterior a la fecha actual
            try {
                // Parsear fecha de entrada
                String[] partesEntrada = entradaActiva.getFecha().split("/");
                int diaEntrada = Integer.parseInt(partesEntrada[0]);
                int mesEntrada = Integer.parseInt(partesEntrada[1]);
                int añoEntrada = Integer.parseInt(partesEntrada[2]);
                
                // Parsear hora de entrada
                String[] partesHoraEntrada = entradaActiva.getHora().split(":");
                int horasEntrada = Integer.parseInt(partesHoraEntrada[0]);
                int minutosEntrada = Integer.parseInt(partesHoraEntrada[1]);
                
                // Crear LocalDateTime para entrada y actual
                java.time.LocalDateTime fechaHoraEntrada = java.time.LocalDateTime.of(
                    añoEntrada, mesEntrada, diaEntrada, horasEntrada, minutosEntrada);
                
                // Si la entrada es en el futuro, retornar mensaje apropiado
                if (fechaHoraEntrada.isAfter(ahora)) {
                    return "Entrada futura";
                }
                
                // Si la entrada es válida (pasada o presente), calcular tiempo
                tiempo = calcularDiferenciaTiempo(entradaActiva.getFecha(), entradaActiva.getHora(), 
                    fechaActual, horaActual);
                    
            } catch (Exception e) {
                return "Error en fecha";
            }
        }

        return tiempo;
    }

    public String calcularDiferenciaTiempo(String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida){
        // Parsear la fecha entrada a formato: dd/mm/yyyy
        String[] partesFechaEntrada = fechaEntrada.split("/");
        int diaEntrada = Integer.parseInt(partesFechaEntrada[0]);
        int mesEntrada = Integer.parseInt(partesFechaEntrada[1]);
        int añoEntrada = Integer.parseInt(partesFechaEntrada[2]);

        // Parsear hora de entrada a formato: HH:MM
        String[] partesHoraEntrada = horaEntrada.split(":");
        int horasEntrada = Integer.parseInt(partesHoraEntrada[0]);
        int minutosEntrada = Integer.parseInt(partesHoraEntrada[1]);

        // Parsear fecha de salida a formato: dd/mm/yyyy
        String[] partesFechaSalida = fechaSalida.split("/");
        int diaSalida = Integer.parseInt(partesFechaSalida[0]);
        int mesSalida = Integer.parseInt(partesFechaSalida[1]);
        int añoSalida = Integer.parseInt(partesFechaSalida[2]);

        // Parsear hora de salida a formato: HH:MM
        String[] partesHoraSalida = horaSalida.split(":");
        int horasSalida = Integer.parseInt(partesHoraSalida[0]);
        int minutosSalida = Integer.parseInt(partesHoraSalida[1]);

        // Crear objetos LocalDateTime
        java.time.LocalDateTime fechaHoraEntrada = java.time.LocalDateTime.of(
            añoEntrada, mesEntrada, diaEntrada, horasEntrada, minutosEntrada);
        java.time.LocalDateTime fechaHoraSalida = java.time.LocalDateTime.of(
            añoSalida, mesSalida, diaSalida, horasSalida, minutosSalida);

        // Calcular diferencia usando Period y Duration
        java.time.Period periodo = java.time.Period.between(fechaHoraEntrada.toLocalDate(), fechaHoraSalida.toLocalDate());
        java.time.Duration duracion = java.time.Duration.between(fechaHoraEntrada.toLocalTime(), fechaHoraSalida.toLocalTime());
        
        // Si la duración es negativa, ajustar
        if (duracion.isNegative()) {
            periodo = periodo.minusDays(1);
            duracion = duracion.plusDays(1);
        }
        
        int años = periodo.getYears();
        int meses = periodo.getMonths();
        int dias = periodo.getDays();
        long horas = duracion.toHours();
        long minutos = duracion.toMinutes() % 60;
        
        // Formatear el resultado usando concatenación de strings
        String resultado = "";
        
        if (años > 0) {
            if (años == 1) {
                resultado = resultado + años + " año";
            } else {
                resultado = resultado + años + " años";
            }
        }
        
        if (meses > 0) {
            if (!resultado.equals("")) {
                resultado = resultado + ", ";
            }
            if (meses == 1) {
                resultado = resultado + meses + " mes";
            } else {
                resultado = resultado + meses + " meses";
            }
        }
        
        if (dias > 0) {
            if (!resultado.equals("")) {
                resultado = resultado + ", ";
            }
            if (dias == 1) {
                resultado = resultado + dias + " día";
            } else {
                resultado = resultado + dias + " días";
            }
        }
        
        // Siempre mostrar las horas y minutos
        if (!resultado.equals("")) {
            resultado = resultado + ", ";
        }
        
        String minutosFormateados;
        if (minutos < 10) {
            minutosFormateados = "0" + minutos;
        } else {
            minutosFormateados = "" + minutos;
        }
        
        resultado = resultado + horas + ":" + minutosFormateados;
        
        return resultado;
    }

    public ArrayList<Contrato> getListaContratos() {
        return listaContratos;
    }

    //ENTRADAS
    public boolean registrarEntrada(Entrada entrada){ //REGISTRAR
        entrada.setNumMovimiento(proxNumEntrada);
        listaEntradas.add(entrada);
        proxNumEntrada++;
        manejador.firePropertyChange("entradaCreada", null, null);              
        return true;
    }

    public Entrada buscarEntradaPorNumMovimiento(int numMovimiento){ //BUSCAR
        Entrada entradaEncontrado = null;
        Iterator<Entrada> it = listaEntradas.iterator();
        while(it.hasNext() && entradaEncontrado == null){
            Entrada entrada = it.next();
            if (entrada.getNumMovimiento() == numMovimiento) {
                entradaEncontrado = entrada;
            }
        }
        return entradaEncontrado;
    }
    
    public Entrada buscarEntradaPorMatricula(String matricula){ //BUSCAR
        Entrada entradaEncontrado = null;
        Iterator<Entrada> it = listaEntradas.iterator();
        while(it.hasNext() && entradaEncontrado == null){
            Entrada entrada = it.next();
            if (entrada.getVehiculo().getMatricula().equals(matricula) && !entrada.tieneSalida()) {
                entradaEncontrado = entrada;
            }
        }
        return entradaEncontrado;
    }

    public boolean existeEntradaConNumMovimiento(int numMovimiento){ //EXISTE
        boolean existe = false;
        if(buscarEntradaPorNumMovimiento(numMovimiento) != null){
            existe = true;
        }
        return existe;
    }
    
    public boolean vehiculoEstaEnParking(String matricula){
        boolean esta = false;
        Iterator<Entrada> it = listaEntradas.iterator();
        while(it.hasNext() && !esta){
            Entrada entrada = it.next();
            if (entrada.getVehiculo().getMatricula().equals(matricula) && !entrada.tieneSalida()) {
                esta = true;
            }
        }
        return esta;
    }
    
    public ArrayList<Vehiculo> getVehiculosDisponiblesParaEntrada() {
        ArrayList<Vehiculo> vehiculosDisponibles = new ArrayList<>();
        
        Iterator<Vehiculo> it = listaVehiculos.iterator();
        while(it.hasNext()){
            Vehiculo vehiculo = it.next();
            if (!vehiculoEstaEnParking(vehiculo.getMatricula())){
                vehiculosDisponibles.add(vehiculo);
            }
        }
        return vehiculosDisponibles;
    }

    public ArrayList<Entrada> getListaEntradas() {
        return listaEntradas;
    }

    public ArrayList<Entrada> getEntradasSinSalida(){
        ArrayList<Entrada> entradasSinSalida = new ArrayList<>();
        
        Iterator<Entrada> it = listaEntradas.iterator();
        while(it.hasNext()){
            Entrada entrada = it.next();
            if (!entrada.tieneSalida()) {
                entradasSinSalida.add(entrada);
            }
        }

        return entradasSinSalida;
    }

    //SALIDAS
    public boolean registrarSalida(Salida salida, Entrada entrada) {
        salida.setNumMovimiento(proxNumSalida);
        salida.setEntrada(entrada); // Asegurarse de establecer la entrada en la salida
        entrada.setSalidaAsociada(salida);
        listaSalidas.add(salida);
        proxNumSalida++;
        manejador.firePropertyChange("salidaCreada", null, null);
        return true;
    }
    
    public ArrayList<Salida> getListaSalidas() {
        return listaSalidas;
    }

    //SERVICIOS ADICIONALES
    public boolean registrarServicio(ServicioAdicional servicio){ //REGISTRAR
        servicio.setNumMovimiento(proxNumServicio);
        listaServiciosAdicionales.add(servicio);
        proxNumServicio++;
        manejador.firePropertyChange("servicioCreado", null, null);
        return true;
    }

    
    public ArrayList<ServicioAdicional> getListaServiciosAdicionales() {
        return listaServiciosAdicionales;
    }
    
    public ServicioAdicional buscarServicioPorMatricula(String matricula) {
        ServicioAdicional servicioEncontrado = null;
        Iterator<ServicioAdicional> it = listaServiciosAdicionales.iterator();
        while (it.hasNext() && servicioEncontrado == null) {
            ServicioAdicional servicio = it.next();
            if (servicio.getVehiculo().getMatricula().equals(matricula)) {
                servicioEncontrado = servicio;
            }
        }
        return servicioEncontrado;
    }
    
    public ArrayList<String> getServiciosMasUtilizados(){
        // Lista donde guardaremos los resultados finales
        ArrayList<String> resultado = new ArrayList<>();

        // Crear listas para almacenar los tipos de servicios y sus contadores
        ArrayList<String> tiposServicios = new ArrayList<>();
        ArrayList<Integer> contadores = new ArrayList<>();

        // Recorrer todos los servicios y contar cuántas veces aparece cada tipo
        for (int i = 0; i < listaServiciosAdicionales.size(); i++) {
            ServicioAdicional servicio = listaServiciosAdicionales.get(i);
            String tipo = servicio.getTipoServicio();

            // Verificar si este tipo ya está en nuestra lista
            boolean encontrado = false;
            int posicion = -1;

            for (int j = 0; j < tiposServicios.size(); j++) {
                if (tiposServicios.get(j).equals(tipo)) {
                    encontrado = true;
                    posicion = j;
                }
            }
            
            // Si ya existe el tipo, incrementar su contador
            if (encontrado) {
                int valorActual = contadores.get(posicion);
                contadores.set(posicion, valorActual + 1);
            } // Si no existe, añadirlo con contador 1
            else {
                tiposServicios.add(tipo);
                contadores.add(1);
            }
        }

        // Ordenar los servicios por frecuencia (de mayor a menor)
        // Usando ordenamiento burbuja simple
        for (int i = 0; i < tiposServicios.size() - 1; i++) {
            for (int j = 0; j < tiposServicios.size() - i - 1; j++) {
                if (contadores.get(j) < contadores.get(j + 1)) {
                    // Intercambiar contadores
                    int tempContador = contadores.get(j);
                    contadores.set(j, contadores.get(j + 1));
                    contadores.set(j + 1, tempContador);

                    // Intercambiar tipos de servicios
                    String tempTipo = tiposServicios.get(j);
                    tiposServicios.set(j, tiposServicios.get(j + 1));
                    tiposServicios.set(j + 1, tempTipo);
                }
            }
        }
        
        // Formatear y agregar todos los resultados
        for (int i = 0; i < tiposServicios.size(); i++) {
            String texto = tiposServicios.get(i) + " - " + contadores.get(i) + " veces";
            resultado.add(texto);
        }

        return resultado;
    }
    
    public ArrayList<String> getEmpleadosConMenosMovimientos() {
        // Lista donde guardaremos los resultados finales
        ArrayList<String> resultado = new ArrayList<>();

        // Crear listas para almacenar los empleados y sus contadores de movimientos
        ArrayList<Empleado> empleados = new ArrayList<>();
        ArrayList<Integer> contadores = new ArrayList<>();

        // Primero, agregar todos los empleados a la lista
        for (int i = 0; i < listaEmpleados.size(); i++) {
            empleados.add(listaEmpleados.get(i));
            contadores.add(0); // Inicializar contadores en 0
        }

        // Contar movimientos para cada empleado
        // 1. Contar entradas
        for (int i = 0; i < listaEntradas.size(); i++) {
            Entrada entrada = listaEntradas.get(i);
            int cedulaEmpleado = entrada.getEmpleado().getCedula();
            
            // Buscar el empleado en nuestra lista
            boolean encontrado = false;
            int posicion = -1;
            int j = 0;
            
            while (j < empleados.size() && !encontrado) {
                if (empleados.get(j).getCedula() == cedulaEmpleado) {
                    encontrado = true;
                    posicion = j;
                }
                j++;
            }
            
            // Si encontramos al empleado, incrementar su contador
            if (encontrado) {
                contadores.set(posicion, contadores.get(posicion) + 1);
            }
        }
        
        // 2. Contar salidas
        for (int i = 0; i < listaSalidas.size(); i++) {
            Salida salida = listaSalidas.get(i);
            int cedulaEmpleado = salida.getEmpleado().getCedula();
            
            boolean encontrado = false;
            int posicion = -1;
            int j = 0;
            
            while (j < empleados.size() && !encontrado) {
                if (empleados.get(j).getCedula() == cedulaEmpleado) {
                    encontrado = true;
                    posicion = j;
                }
                j++;
            }
            
            if (encontrado) {
                contadores.set(posicion, contadores.get(posicion) + 1);
            }
        }
        
        // 3. Contar servicios adicionales
        for (int i = 0; i < listaServiciosAdicionales.size(); i++) {
            ServicioAdicional servicio = listaServiciosAdicionales.get(i);
            int cedulaEmpleado = servicio.getEmpleado().getCedula();
            
            boolean encontrado = false;
            int posicion = -1;
            int j = 0;
            
            while (j < empleados.size() && !encontrado) {
                if (empleados.get(j).getCedula() == cedulaEmpleado) {
                    encontrado = true;
                    posicion = j;
                }
                j++;
            }
            
            if (encontrado) {
                contadores.set(posicion, contadores.get(posicion) + 1);
            }
        }

        // Ordenar los empleados por cantidad de movimientos (de menor a mayor)
        for (int i = 0; i < empleados.size() - 1; i++) {
            for (int j = 0; j < empleados.size() - i - 1; j++) {
                if (contadores.get(j) > contadores.get(j + 1)) {
                    // Intercambiar contadores
                    int tempContador = contadores.get(j);
                    contadores.set(j, contadores.get(j + 1));
                    contadores.set(j + 1, tempContador);

                    // Intercambiar empleados
                    Empleado tempEmpleado = empleados.get(j);
                    empleados.set(j, empleados.get(j + 1));
                    empleados.set(j + 1, tempEmpleado);
                }
            }
        }
        
        // Formatear y agregar todos los resultados
        for (int i = 0; i < empleados.size(); i++) {
            String texto = empleados.get(i).getNombre() + " - " + contadores.get(i) + " movimientos";
            resultado.add(texto);
        }

        return resultado;
    }
    
    public ArrayList<String> getEstadiasMasLargas(){
        // Lista donde guardaremos los resultados finales
        ArrayList<String> resultado = new ArrayList<>();

        // Crear listas para almacenar los datos
        ArrayList<String> matriculasVehiculos = new ArrayList<>();
        ArrayList<String> duraciones = new ArrayList<>();
        ArrayList<Long> minutosEstadia = new ArrayList<>(); // Para ordenar

        // Recorrer todas las salidas para encontrar estadías completas
        for (int i = 0; i < listaSalidas.size(); i++) {
            Salida salida = listaSalidas.get(i);
            
            if (salida.getEntrada() != null) {
                Entrada entrada = salida.getEntrada();
                Vehiculo vehiculo = entrada.getVehiculo();
                
                // Calcular la duración de la estadía usando el método existente
                String duracion = calcularDiferenciaTiempo(
                    entrada.getFecha(), entrada.getHora(),
                    salida.getFecha(), salida.getHora()
                );
                
                // Convertir la duración a un valor numérico para ordenar
                long minutos = convertirDuracionAMinutos(duracion);
                
                String infoVehiculo = vehiculo.getMarca() + " " + vehiculo.getModelo() + 
                                     " - " + vehiculo.getMatricula();
                
                matriculasVehiculos.add(infoVehiculo);
                duraciones.add(duracion);
                minutosEstadia.add(minutos);
            }
        }

        // Ordenar las estadías por duración (de mayor a menor)
        for (int i = 0; i < minutosEstadia.size() - 1; i++) {
            for (int j = 0; j < minutosEstadia.size() - i - 1; j++) {
                if (minutosEstadia.get(j) < minutosEstadia.get(j + 1)) {
                    // Intercambiar los tres arrays
                    long tempMinutos = minutosEstadia.get(j);
                    minutosEstadia.set(j, minutosEstadia.get(j + 1));
                    minutosEstadia.set(j + 1, tempMinutos);

                    String tempMatricula = matriculasVehiculos.get(j);
                    matriculasVehiculos.set(j, matriculasVehiculos.get(j + 1));
                    matriculasVehiculos.set(j + 1, tempMatricula);
                    
                    String tempDuracion = duraciones.get(j);
                    duraciones.set(j, duraciones.get(j + 1));
                    duraciones.set(j + 1, tempDuracion);
                }
            }
        }
        
        // Formatear y agregar todos los resultados
        for (int i = 0; i < matriculasVehiculos.size(); i++) {
            String texto = matriculasVehiculos.get(i) + " - " + duraciones.get(i);
            resultado.add(texto);
        }

        return resultado;
    }

    // Método auxiliar para convertir la duración formateada a minutos totales
    private long convertirDuracionAMinutos(String duracion) {
        long minutosTotales = 0;
        
        // Primero, separar por comas para obtener cada componente de tiempo
        String[] componentes = duracion.split(", ");
        
        for (int i = 0; i < componentes.length; i++) {
            String componente = componentes[i];
            
            if (componente.contains(":")) {
                // Es el componente de horas:minutos
                String[] partesHorasMinutos = componente.split(":");
                int horas = Integer.parseInt(partesHorasMinutos[0]);
                int minutos = Integer.parseInt(partesHorasMinutos[1]);
                minutosTotales += horas * 60 + minutos;
            } else {
                // Es un componente de años, meses o días
                // Extraer el valor numérico
                String numeroStr = componente.replaceAll("[^0-9]", "");
                if (!numeroStr.isEmpty()) {
                    int valor = Integer.parseInt(numeroStr);
                    
                    if (componente.contains("año") || componente.contains("años")) {
                        minutosTotales += valor * 365 * 24 * 60L; // Uso 365L para evitar desbordamiento
                    } else if (componente.contains("mes") || componente.contains("meses")) {
                        minutosTotales += valor * 30 * 24 * 60L;
                    } else if (componente.contains("día") || componente.contains("días") || 
                              componente.contains("dia") || componente.contains("dias")) {
                        minutosTotales += valor * 24 * 60L;
                    }
                }
            }
        }
        
        return minutosTotales;
    }

    public ArrayList<String> getClientesConMasVehiculos(){
        // Lista donde guardaremos los resultados finales
        ArrayList<String> resultado = new ArrayList<>();

        // Crear listas para almacenar los clientes y sus contadores de vehículos
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Integer> contadores = new ArrayList<>();

        // Recorrer todos los contratos y contar cuántos vehículos tiene cada cliente
        for (int i = 0; i < listaContratos.size(); i++) {
            Contrato contrato = listaContratos.get(i);
            Cliente cliente = contrato.getClienteContrato();

            // Verificar si este cliente ya está en nuestra lista
            boolean encontrado = false;
            int posicion = -1;

            for (int j = 0; j < clientes.size(); j++) {
                if (clientes.get(j).getCedula() == cliente.getCedula()) {
                    encontrado = true;
                    posicion = j;
                }
            }
            
            // Si ya existe el cliente, incrementar su contador
            if (encontrado) {
                int valorActual = contadores.get(posicion);
                contadores.set(posicion, valorActual + 1);
            } 
            // Si no existe, añadirlo con contador 1
            else {
                clientes.add(cliente);
                contadores.add(1);
            }
        }

        // Ordenar los clientes por cantidad de vehículos (de mayor a menor)
        // Usando ordenamiento burbuja simple
        for (int i = 0; i < clientes.size() - 1; i++) {
            for (int j = 0; j < clientes.size() - i - 1; j++) {
                if (contadores.get(j) < contadores.get(j + 1)) {
                    // Intercambiar contadores
                    int tempContador = contadores.get(j);
                    contadores.set(j, contadores.get(j + 1));
                    contadores.set(j + 1, tempContador);

                    // Intercambiar clientes
                    Cliente tempCliente = clientes.get(j);
                    clientes.set(j, clientes.get(j + 1));
                    clientes.set(j + 1, tempCliente);
                }
            }
        }
        
        // Formatear y agregar todos los resultados
        for (int i = 0; i < clientes.size(); i++) {
            String texto = clientes.get(i).getNombre() + " - " + contadores.get(i) + " vehículo";
            if (contadores.get(i) > 1) {
                texto += "s";
            }
            resultado.add(texto);
        }

        return resultado;
    }
    
    public ArrayList<Object> getMovimientosVehiculo(String matricula) {
        ArrayList<Object> movimientos = new ArrayList<>();
    
        // Agregar entradas
        Iterator<Entrada> itEntradas = listaEntradas.iterator();
        while (itEntradas.hasNext()) {
            Entrada entrada = itEntradas.next();
            if (entrada.getVehiculo().getMatricula().equals(matricula)) {
                movimientos.add(entrada);
            }
        }
    
        // Agregar salidas
        Iterator<Salida> itSalidas = listaSalidas.iterator();
        while (itSalidas.hasNext()) {
            Salida salida = itSalidas.next();
            // Verificar que la entrada no sea nula antes de acceder a ella
            if (salida.getEntrada() != null && 
                salida.getEntrada().getVehiculo().getMatricula().equals(matricula)) {
                movimientos.add(salida);
            } else if (salida.getVehiculo().getMatricula().equals(matricula)) {
                // Si la entrada es nula, verificamos el vehículo directamente en la salida
                movimientos.add(salida);
            }
        }
    
        // Agregar servicios adicionales
        Iterator<ServicioAdicional> itServicios = listaServiciosAdicionales.iterator();
        while (itServicios.hasNext()) {
            ServicioAdicional servicio = itServicios.next();
            if (servicio.getVehiculo().getMatricula().equals(matricula)) {
                movimientos.add(servicio);
            }
        }
    
        return movimientos;
    }
    
    public ArrayList<Object> filtrarMovimientos(ArrayList<Object> movimientos,
    boolean incluirEntradas, boolean incluirSalidas, boolean incluirServicios) {

        ArrayList<Object> movimientosFiltrados = new ArrayList<>();

        Iterator<Object> it = movimientos.iterator();
        while(it.hasNext()) {
            Object movimiento = it.next();
            if (incluirEntradas && movimiento instanceof Entrada) {
                movimientosFiltrados.add(movimiento);
            } else if (incluirSalidas && movimiento instanceof Salida) {
                movimientosFiltrados.add(movimiento);
            } else if (incluirServicios && movimiento instanceof ServicioAdicional) {
                movimientosFiltrados.add(movimiento);
            }
        }

        return movimientosFiltrados;
    }
    
    public ArrayList<Object> ordenarMovimientos(ArrayList<Object> movimientos, boolean ascendente) {
        Collections.sort(movimientos, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                String fecha1 = "";
                String hora1 = "";
                String fecha2 = "";
                String hora2 = "";
                int resultado = 0;

                if (o1 instanceof Entrada) {
                    Entrada entrada = (Entrada) o1;
                    fecha1 = entrada.getFecha();
                    hora1 = entrada.getHora();
                } else if (o1 instanceof Salida) {
                    Salida salida = (Salida) o1;
                    fecha1 = salida.getFecha();
                    hora1 = salida.getHora();
                } else if (o1 instanceof ServicioAdicional) {
                    ServicioAdicional servicio = (ServicioAdicional) o1;
                    fecha1 = servicio.getFecha();
                    hora1 = servicio.getHora();
                }

                if (o2 instanceof Entrada) {
                    Entrada entrada = (Entrada) o2;
                    fecha2 = entrada.getFecha();
                    hora2 = entrada.getHora();
                } else if (o2 instanceof Salida) {
                    Salida salida = (Salida) o2;
                    fecha2 = salida.getFecha();
                    hora2 = salida.getHora();
                } else if (o2 instanceof ServicioAdicional) {
                    ServicioAdicional servicio = (ServicioAdicional) o2;
                    fecha2 = servicio.getFecha();
                    hora2 = servicio.getHora();
                }
                
                // Comparar fechas primero (en formato dd/mm/yyyy)
                // Convertir a LocalDate para comparar correctamente
                String[] partesFecha1 = fecha1.split("/");
                String[] partesFecha2 = fecha2.split("/");
                LocalDate localDate1 = LocalDate.of(
                    Integer.parseInt(partesFecha1[2]),
                    Integer.parseInt(partesFecha1[1]),
                    Integer.parseInt(partesFecha1[0])
                );
                LocalDate localDate2 = LocalDate.of(
                    Integer.parseInt(partesFecha2[2]),
                    Integer.parseInt(partesFecha2[1]),
                    Integer.parseInt(partesFecha2[0])
                );

                int comparacionFecha = localDate1.compareTo(localDate2);
                if (comparacionFecha != 0) {
                    if (ascendente) {
                        resultado = comparacionFecha;
                    } else {
                        resultado = -comparacionFecha;
                    }
                } else {
                    // Si las fechas son iguales, comparar horas
                    if (ascendente) {
                        resultado = hora1.compareTo(hora2);
                    } else {
                        resultado = -hora1.compareTo(hora2);
                    }
                }
                
                return resultado;
            }
        });

        return movimientos;
    }

    public void exportarMovimientosATxt(ArrayList<Object> movimientos, String matricula) {
        try {
            String nombreArchivo = matricula + ".txt";
            FileWriter fileWriter = new FileWriter(nombreArchivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("HISTORIAL DE MOVIMIENTOS PARA VEHÍCULO: " + matricula);
            printWriter.println("========================================================");
            printWriter.println();

            for (int i = 0; i<movimientos.size(); i++) {
                Object movimiento = movimientos.get(i);
                String tipo = "";
                String fecha = "";
                String hora = "";
                String detalles = "";

                if (movimiento instanceof Entrada) {
                    Entrada entrada = (Entrada) movimiento;
                    tipo = "ENTRADA";
                    fecha = entrada.getFecha();
                    hora = entrada.getHora();
                    detalles = "Empleado: " + entrada.getEmpleado().getNombre();
                    if (!entrada.getNota().isEmpty()) {
                        detalles += " - Notas: " + entrada.getNota();
                    }
                } else if (movimiento instanceof Salida) {
                    Salida salida = (Salida) movimiento;
                    tipo = "SALIDA";
                    fecha = salida.getFecha();
                    hora = salida.getHora();
                    detalles = "Empleado: " + salida.getEmpleado().getNombre();
                    if (!salida.getNota().isEmpty()) {
                        detalles += " - Notas: " + salida.getNota();
                    }
                    
                    // Agregar tiempo en parking con manejo de error
                    try {
                        if (salida.getEntrada() != null) {
                            detalles += " - Tiempo en parking: " + calcularDiferenciaTiempo(
                                    salida.getEntrada().getFecha(),
                                    salida.getEntrada().getHora(),
                                    salida.getFecha(),
                                    salida.getHora());
                        } else {
                            detalles += " - Tiempo en parking: No disponible";
                        }
                    } catch (Exception e) {
                        detalles += " - Tiempo en parking: No disponible";
                    }
                } else if (movimiento instanceof ServicioAdicional) {
                    ServicioAdicional servicio = (ServicioAdicional) movimiento;
                    tipo = "SERVICIO: " + servicio.getTipoServicio();
                    fecha = servicio.getFecha();
                    hora = servicio.getHora();
                    detalles = "Empleado: " + servicio.getEmpleado().getNombre()
                            + " - Costo: $" + servicio.getCostoServicio();
                }

                printWriter.println(tipo + " | Fecha: " + fecha + " | Hora: " + hora + " | " + detalles);
            }

            printWriter.close();
        } catch (IOException e) {
            System.err.println("Error al exportar movimientos: " + e.getMessage());
        }
    }

    public int getProxNumContrato() {
        return proxNumContrato;
    }

    public int getProxNumEntrada() {
        return proxNumEntrada;
    }

    public int getProxNumSalida() {
        return proxNumSalida;
    }

    public int getProxNumServicio() {
        return proxNumServicio;
    }
    
    public boolean existenDatosGuardados() {
        java.io.File archivo = new java.io.File("DATOS.ser");
        return archivo.exists();
    }

    public void guardarDatos() throws IOException{
        // try catch con recursos, cierra automaticamente el ObjectOutputStream (out.close())
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("DATOS.ser"))) {
            out.writeObject(this); // serializamos el sistema que contiene todas las listas, implementamos serializable en las clases para serializar correctamente
        } catch (FileNotFoundException e) {
            System.err.println("No se pudo encontrar el archivo para guardar los datos: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public Sistema cargarDatos() throws IOException, ClassNotFoundException, FileNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("DATOS.ser"))) {

            return (Sistema) in.readObject(); // retornar el sistema cargado
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No hay datos guardados previamente");
        } catch (IOException e) {
            throw new IOException("Error al cargar los datos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Clase no encontrada al cargar los datos: " + e.getMessage());
        }
    }

}
