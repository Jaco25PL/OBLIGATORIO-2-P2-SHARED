/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import observer.SistemaObserver;

public class Sistema {
    
    //Atributos
    //Listas vacias
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
    private List<SistemaObserver> observers = new ArrayList<>();

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

    }

    //Metodos
    //GESTION DE CLIENTES
    public boolean registrarCliente(Cliente cliente){ //REGISTRAR
        boolean registrado = false;
        if (!existeClienteConCedula(cliente.getCedula())) {
            registrado = true;
            listaClientes.add(cliente);
            notificarClienteCreado();
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
            
            notificarClienteEliminado();
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
            notificarVehiculoCreado();
        }
        return registrado;
    }

    public boolean eliminarVehiculo(String matricula){ //ELIMINAR
        boolean eliminado = false;
        if (existeVehiculoConMatricula(matricula)) {
            Vehiculo vehiculo = buscarVehiculoPorMatricula(matricula);
            
            listaVehiculos.remove(vehiculo);
            eliminado = true;
            notificarVehiculoEliminado();
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
            notificarEmpleadoCreado();
        }
        return registrado;
    }

    public boolean eliminarEmpleado(int cedula){ //ELIMINAR
        boolean eliminado = false;
        if (existeEmpleadoConCedula(cedula)) {
            Empleado empleado = buscarEmpleadoPorCedula(cedula);
            
            listaEmpleados.remove(empleado);
            eliminado = true;
            notificarEmpleadoEliminado();
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
        notificarContratoCreado();
        return true;
    }

    public boolean eliminarContrato(int numContrato){ //ELIMINAR
        boolean eliminado = false;
        if (existeContratoConNumContrato(numContrato)) {
            Contrato contrato = buscarContratoPorNumContrato(numContrato);
            
            listaContratos.remove(contrato);
            eliminado = true;
            notificarContratoEliminado();
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
        boolean encontrada = false;
        Entrada entradaActiva = null;
        Iterator<Entrada> it = listaEntradas.iterator();
        while(it.hasNext() && !encontrada){
            Entrada entrada = it.next();
            if (entrada.getVehiculo().getMatricula().equals(vehiculo.getMatricula())){
                entradaActiva = entrada;
                encontrada = true;
            }
        }

        if (entradaActiva != null) {
            // Obtengo fecha y hora actual para calcular
            java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
            String fechaActual = ahora.getDayOfMonth() + "/" + ahora.getMonthValue() 
            + "/" + ahora.getYear();
            String horaActual = String.format("%02d:%02d", ahora.getHour(), ahora.getMinute());
        
            tiempo = calcularDiferenciaTiempo(entradaActiva.getFecha(), entradaActiva.getHora(), 
            fechaActual, horaActual);
        }

        return tiempo;
    }

    private String calcularDiferenciaTiempo(String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida){

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

        // Calcular diferencia
        java.time.Duration duracion = java.time.Duration.between(fechaHoraEntrada, fechaHoraSalida);

        long horas = duracion.toHours();
        long minutos = duracion.toMinutes() % 60;

        return String.format("%d:%02d", horas, minutos);
    }

    public ArrayList<Contrato> getListaContratos() {
        return listaContratos;
    }

    //ENTRADAS
    public boolean registrarEntrada(Entrada entrada){ //REGISTRAR
        entrada.setNumMovimiento(proxNumEntrada);
        listaEntradas.add(entrada);
        proxNumEntrada++;
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
            if (entrada.getVehiculo().getMatricula().equals(matricula)) {
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
    public boolean registrarSalida(Salida salida, Entrada entrada){ //REGISTRAR
        salida.setNumMovimiento(proxNumSalida);
        entrada.setSalidaAsociada(salida);
        listaSalidas.add(salida);
        proxNumSalida++;
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
        return true;
    }
    
    public ArrayList<ServicioAdicional> getListaServiciosAdicionales() {
        return listaServiciosAdicionales;
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
    
    // Registrar observer
    public void addObserver(SistemaObserver observer) {
        observers.add(observer);
    }
    
    // Notificar cambios
    private void notificarClienteEliminado() {
        for (int i = 0; i < observers.size(); i++) {
            SistemaObserver observer = observers.get(i);
            observer.onClienteEliminado();
        }
    }

    private void notificarClienteCreado() {
        for (int i = 0; i < observers.size(); i++) {
            SistemaObserver observer = observers.get(i);
            observer.onClienteCreado();
        }
    }
    
    private void notificarVehiculoEliminado() {
        for (int i = 0; i < observers.size(); i++) {
            SistemaObserver observer = observers.get(i);
            observer.onVehiculoEliminado();
        }
    }

    private void notificarVehiculoCreado() {
        for (int i = 0; i < observers.size(); i++) {
            SistemaObserver observer = observers.get(i);
            observer.onVehiculoCreado();
        }
    }

    private void notificarEmpleadoEliminado() {
        for (int i = 0; i < observers.size(); i++) {
            SistemaObserver observer = observers.get(i);
            observer.onEmpleadoEliminado();
        }
    }

    private void notificarEmpleadoCreado() {
        for (int i = 0; i < observers.size(); i++) {
            SistemaObserver observer = observers.get(i);
            observer.onEmpleadoCreado();
        }
    }

    private void notificarContratoEliminado() {
        for (int i = 0; i < observers.size(); i++) {
            SistemaObserver observer = observers.get(i);
            observer.onContratoEliminado();
        }
    }

    private void notificarContratoCreado() {
        for (int i = 0; i < observers.size(); i++) {
            SistemaObserver observer = observers.get(i);
            observer.onContratoCreado();
        }
    }
}
