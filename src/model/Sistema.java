/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

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
import java.util.List;
import observer.SistemaObserver;

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
    private transient List<SistemaObserver> observers = new ArrayList<>();

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
        notificarEntradaCreada();                
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
        notificarSalidaCreada();
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
    
    // Registrar observer
    public void addObserver(SistemaObserver observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }
    
    // Notificar cambios
    private void notificarClienteEliminado() {
        // Verificar que observers no sea null antes de iterar
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onClienteEliminado();
            }
        }
    }

    private void notificarClienteCreado() {
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onClienteCreado();
            }
        }
    }
    
    // Aplicar el mismo patrón a TODOS los métodos de notificación...
    private void notificarVehiculoEliminado() {
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onVehiculoEliminado();
            }
        }
    }

    private void notificarVehiculoCreado() {
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onVehiculoCreado();
            }
        }
    }

    private void notificarEmpleadoEliminado() {
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onEmpleadoEliminado();
            }
        }
    }

    private void notificarEmpleadoCreado() {
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onEmpleadoCreado();
            }
        }
    }

    private void notificarContratoEliminado() {
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onContratoEliminado();
            }
        }
    }

    private void notificarContratoCreado() {
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onContratoCreado();
            }
        }
    }

    private void notificarEntradaCreada(){
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onEntradaCreada();
            }
        }
    }

    private void notificarSalidaCreada(){
        if (observers != null) {
            for (int i = 0; i < observers.size(); i++) {
                SistemaObserver observer = observers.get(i);
                observer.onSalidaCreada();
            }
        }
    }

    public boolean existenDatosGuardados() {
        java.io.File archivo = new java.io.File("DATOS.ser");
        return archivo.exists();
    }


    public void guardarDatos() throws IOException{
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("DATOS.ser"))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public Sistema cargarDatos() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("DATOS.ser"))) {
            return (Sistema) in.readObject(); // ← Debe retornar el sistema cargado
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("No hay datos guardados previamente");
        }
    }











    
    public void cargarDatosPrueba() {
        // Crear algunos clientes
        Cliente cliente1 = new Cliente("Juan Pérez", 12345678, "Av. Italia 1234", 99123456, 2020);
        Cliente cliente2 = new Cliente("María López", 23456789, "Rivera 567", 98765432, 2021);
        Cliente cliente3 = new Cliente("Carlos Rodríguez", 34567890, "18 de Julio 890", 97654321, 2019);
        Cliente cliente4 = new Cliente("Ana Martínez", 45678901, "Luis A. de Herrera 1234", 96543210, 2022);

        //Registrar clientes en el sistema
        registrarCliente(cliente1);
        registrarCliente(cliente2);
        registrarCliente(cliente3);
        registrarCliente(cliente4);

        // Crear algunos vehículos
        Vehiculo vehiculo1 = new Vehiculo("ABC123", "Toyota", "Corolla", "Bueno");
        Vehiculo vehiculo2 = new Vehiculo("DEF456", "Honda", "Civic", "Excelente");
        Vehiculo vehiculo3 = new Vehiculo("GHI789", "Ford", "Focus", "Regular");
        Vehiculo vehiculo4 = new Vehiculo("JKL012", "Chevrolet", "Onix", "Bueno");
        Vehiculo vehiculo5 = new Vehiculo("MNO345", "Volkswagen", "Golf", "Excelente");
        Vehiculo vehiculo6 = new Vehiculo("PQR678", "Fiat", "Uno", "Bueno");
        Vehiculo vehiculo7 = new Vehiculo("STU901", "Renault", "Logan", "Regular");
        Vehiculo vehiculo8 = new Vehiculo("VWX234", "Peugeot", "208", "Bueno");
        Vehiculo vehiculo9 = new Vehiculo("YZA567", "Hyundai", "Accent", "Excelente");
        Vehiculo vehiculo10 = new Vehiculo("BCD890", "Kia", "Rio", "Bueno");

        // Registrar vehículos en el sistema
        registrarVehiculo(vehiculo1);
        registrarVehiculo(vehiculo2);
        registrarVehiculo(vehiculo3);
        registrarVehiculo(vehiculo4);
        registrarVehiculo(vehiculo5);
        registrarVehiculo(vehiculo6);
        registrarVehiculo(vehiculo7);
        registrarVehiculo(vehiculo8);
        registrarVehiculo(vehiculo9);
        registrarVehiculo(vehiculo10);

        // Crear algunos empleados
        Empleado empleado1 = new Empleado("Roberto Gómez", 56789012, "Bulevar Artigas 456", 101);
        Empleado empleado2 = new Empleado("Laura Benítez", 67890123, "Propios 789", 102);
        Empleado empleado3 = new Empleado("Sergio Torres", 78901234, "Av. Brasil 234", 103);

        // Registrar empleados en el sistema
        registrarEmpleado(empleado1);
        registrarEmpleado(empleado2);
        registrarEmpleado(empleado3);

        // Crear algunos contratos
        Contrato contrato1 = new Contrato(5000, empleado1, cliente1, vehiculo1, proxNumContrato, "10/11/2023");
        Contrato contrato2 = new Contrato(6000, empleado2, cliente2, vehiculo2, proxNumContrato, "15/11/2023");
        Contrato contrato3 = new Contrato(4500, empleado3, cliente3, vehiculo3, proxNumContrato, "18/11/2023");
        Contrato contrato4 = new Contrato(5500, empleado1, cliente4, vehiculo4, proxNumContrato, "20/11/2023");
        Contrato contrato5 = new Contrato(6500, empleado2, cliente1, vehiculo5, proxNumContrato, "22/11/2023");
        Contrato contrato6 = new Contrato(7000, empleado3, cliente2, vehiculo6, proxNumContrato, "25/11/2023");
        Contrato contrato7 = new Contrato(5800, empleado1, cliente3, vehiculo7, proxNumContrato, "27/11/2023");
        Contrato contrato8 = new Contrato(6200, empleado2, cliente4, vehiculo8, proxNumContrato, "29/11/2023");
        Contrato contrato9 = new Contrato(5300, empleado3, cliente1, vehiculo9, proxNumContrato, "01/12/2023");
        Contrato contrato10 = new Contrato(5900, empleado1, cliente2, vehiculo10, proxNumContrato, "03/12/2023");

        // Registrar contratos en el sistema
        registrarContrato(contrato1);
        registrarContrato(contrato2);
        registrarContrato(contrato3);
        registrarContrato(contrato4);
        registrarContrato(contrato5);
        registrarContrato(contrato6);
        registrarContrato(contrato7);
        registrarContrato(contrato8);
        registrarContrato(contrato9);
        registrarContrato(contrato10);

        // Obtener la fecha actual
        java.time.LocalDate hoy = java.time.LocalDate.now();
        
        // Formatear la fecha actual y las fechas de los días siguientes
        String fechaHoy = String.format("%02d/%02d/%04d", hoy.getDayOfMonth(), hoy.getMonthValue(), hoy.getYear());
        String fechaMañana = String.format("%02d/%02d/%04d", hoy.plusDays(1).getDayOfMonth(), hoy.plusDays(1).getMonthValue(), hoy.plusDays(1).getYear());
        String fechaPasadoMañana = String.format("%02d/%02d/%04d", hoy.plusDays(2).getDayOfMonth(), hoy.plusDays(2).getMonthValue(), hoy.plusDays(2).getYear());

    // --- CREAR MOVIMIENTOS PARA HOY ---
    
    // Movimientos en la franja 00:00-05:59 (pocos movimientos - verde)
    Entrada entradaHoy1 = new Entrada(proxNumEntrada, fechaHoy, "01:30", "Entrada nocturna", empleado1, vehiculo1);
    Entrada entradaHoy2 = new Entrada(proxNumEntrada, fechaHoy, "03:45", "Sin observaciones", empleado2, vehiculo2);
    registrarEntrada(entradaHoy1);
    registrarEntrada(entradaHoy2);
    
    // Movimientos en la franja 06:00-11:59 (cantidad media - amarillo)
    Entrada entradaHoy3 = new Entrada(proxNumEntrada, fechaHoy, "07:15", "Ingreso matutino", empleado3, vehiculo3);
    Entrada entradaHoy4 = new Entrada(proxNumEntrada, fechaHoy, "08:30", "Vidrio trasero sucio", empleado1, vehiculo4);
    Entrada entradaHoy5 = new Entrada(proxNumEntrada, fechaHoy, "09:45", "Sin observaciones", empleado2, vehiculo5);
    registrarEntrada(entradaHoy3);
    registrarEntrada(entradaHoy4);
    registrarEntrada(entradaHoy5);
    
    Salida salidaHoy1 = new Salida(proxNumSalida, fechaHoy, "10:30", "Salida normal", empleado3, vehiculo1);
    Salida salidaHoy2 = new Salida(proxNumSalida, fechaHoy, "11:15", "Sin observaciones", empleado1, vehiculo2);
    registrarSalida(salidaHoy1, entradaHoy1);
    registrarSalida(salidaHoy2, entradaHoy2);
    
    // Movimientos en la franja 12:00-17:59 (muchos movimientos - rojo)
    Entrada entradaHoy6 = new Entrada(proxNumEntrada, fechaHoy, "12:10", "Ingreso mediodía", empleado2, vehiculo6);
    Entrada entradaHoy7 = new Entrada(proxNumEntrada, fechaHoy, "13:25", "Abolladura leve", empleado3, vehiculo7);
    Entrada entradaHoy8 = new Entrada(proxNumEntrada, fechaHoy, "14:40", "Sin observaciones", empleado1, vehiculo8);
    registrarEntrada(entradaHoy6);
    registrarEntrada(entradaHoy7);
    registrarEntrada(entradaHoy8);
    
    Salida salidaHoy3 = new Salida(proxNumSalida, fechaHoy, "15:05", "Salida normal", empleado2, vehiculo3);
    Salida salidaHoy4 = new Salida(proxNumSalida, fechaHoy, "16:20", "Conforme", empleado3, vehiculo4);
    Salida salidaHoy5 = new Salida(proxNumSalida, fechaHoy, "17:35", "Sin observaciones", empleado1, vehiculo5);
    registrarSalida(salidaHoy3, entradaHoy3);
    registrarSalida(salidaHoy4, entradaHoy4);
    registrarSalida(salidaHoy5, entradaHoy5);
    
    ServicioAdicional servicioHoy1 = new ServicioAdicional(proxNumServicio, "Lavado", fechaHoy, "13:00", "Lavado completo", vehiculo6, empleado2, 1200);
    ServicioAdicional servicioHoy2 = new ServicioAdicional(proxNumServicio, "Cambio de aceite", fechaHoy, "14:30", "Aceite sintético", vehiculo7, empleado3, 2500);
    ServicioAdicional servicioHoy3 = new ServicioAdicional(proxNumServicio, "Encerado", fechaHoy, "16:00", "Encerado completo", vehiculo8, empleado1, 1800);
    registrarServicio(servicioHoy1);
    registrarServicio(servicioHoy2);
    registrarServicio(servicioHoy3);
    
    // Movimientos en la franja 18:00-23:59 (cantidad media - amarillo)
    Entrada entradaHoy9 = new Entrada(proxNumEntrada, fechaHoy, "18:50", "Entrada vespertina", empleado2, vehiculo9);
    Entrada entradaHoy10 = new Entrada(proxNumEntrada, fechaHoy, "20:05", "Sin observaciones", empleado3, vehiculo10);
    registrarEntrada(entradaHoy9);
    registrarEntrada(entradaHoy10);
    
    Salida salidaHoy6 = new Salida(proxNumSalida, fechaHoy, "19:30", "Salida normal", empleado1, vehiculo6);
    Salida salidaHoy7 = new Salida(proxNumSalida, fechaHoy, "21:45", "Sin observaciones", empleado2, vehiculo7);
    registrarSalida(salidaHoy6, entradaHoy6);
    registrarSalida(salidaHoy7, entradaHoy7);
    
    ServicioAdicional servicioHoy4 = new ServicioAdicional(proxNumServicio, "Limpieza interior", fechaHoy, "20:30", "Aspirado completo", vehiculo9, empleado3, 1500);
    registrarServicio(servicioHoy4);
    
    // --- CREAR MOVIMIENTOS PARA MAÑANA ---
    
    // Movimientos en la franja 00:00-05:59 (cantidad media - amarillo)
    Entrada entradaMañana1 = new Entrada(proxNumEntrada, fechaMañana, "00:20", "Entrada madrugada", empleado1, vehiculo1);
    Entrada entradaMañana2 = new Entrada(proxNumEntrada, fechaMañana, "02:35", "Sin observaciones", empleado2, vehiculo2);
    registrarEntrada(entradaMañana1);
    registrarEntrada(entradaMañana2);
    
    Salida salidaMañana1 = new Salida(proxNumSalida, fechaMañana, "04:50", "Salida normal", empleado3, vehiculo8);
    registrarSalida(salidaMañana1, entradaHoy8);
    
    ServicioAdicional servicioMañana1 = new ServicioAdicional(proxNumServicio, "Revisión eléctrica", fechaMañana, "03:15", "Revisión completa", vehiculo1, empleado1, 2000);
    ServicioAdicional servicioMañana2 = new ServicioAdicional(proxNumServicio, "Cambio de filtro", fechaMañana, "05:30", "Filtro de aire", vehiculo2, empleado2, 1000);
    registrarServicio(servicioMañana1);
    registrarServicio(servicioMañana2);
    
    // Movimientos en la franja 06:00-11:59 (muchos movimientos - rojo)
    Entrada entradaMañana3 = new Entrada(proxNumEntrada, fechaMañana, "06:10", "Ingreso matutino", empleado3, vehiculo3);
    Entrada entradaMañana4 = new Entrada(proxNumEntrada, fechaMañana, "07:25", "Sin observaciones", empleado1, vehiculo4);
    Entrada entradaMañana5 = new Entrada(proxNumEntrada, fechaMañana, "08:40", "Rayón en puerta", empleado2, vehiculo5);
    Entrada entradaMañana6 = new Entrada(proxNumEntrada, fechaMañana, "09:55", "Sin observaciones", empleado3, vehiculo6);
    registrarEntrada(entradaMañana3);
    registrarEntrada(entradaMañana4);
    registrarEntrada(entradaMañana5);
    registrarEntrada(entradaMañana6);
    
    Salida salidaMañana2 = new Salida(proxNumSalida, fechaMañana, "10:15", "Salida normal", empleado1, vehiculo9);
    Salida salidaMañana3 = new Salida(proxNumSalida, fechaMañana, "11:30", "Sin observaciones", empleado2, vehiculo10);
    registrarSalida(salidaMañana2, entradaHoy9);
    registrarSalida(salidaMañana3, entradaHoy10);
    
    ServicioAdicional servicioMañana3 = new ServicioAdicional(proxNumServicio, "Lavado", fechaMañana, "07:00", "Lavado básico", vehiculo3, empleado3, 800);
    ServicioAdicional servicioMañana4 = new ServicioAdicional(proxNumServicio, "Cambio de aceite", fechaMañana, "09:30", "Aceite mineral", vehiculo4, empleado1, 1800);
    ServicioAdicional servicioMañana5 = new ServicioAdicional(proxNumServicio, "Pulido", fechaMañana, "11:00", "Pulido completo", vehiculo5, empleado2, 2200);
    registrarServicio(servicioMañana3);
    registrarServicio(servicioMañana4);
    registrarServicio(servicioMañana5);
    
    // Movimientos en la franja 12:00-17:59 (pocos movimientos - verde)
    Entrada entradaMañana7 = new Entrada(proxNumEntrada, fechaMañana, "13:05", "Ingreso mediodía", empleado3, vehiculo7);
    registrarEntrada(entradaMañana7);
    
    Salida salidaMañana4 = new Salida(proxNumSalida, fechaMañana, "15:20", "Salida normal", empleado1, vehiculo1);
    registrarSalida(salidaMañana4, entradaMañana1);
    
    ServicioAdicional servicioMañana6 = new ServicioAdicional(proxNumServicio, "Revisión de frenos", fechaMañana, "16:40", "Revisión completa", vehiculo2, empleado2, 1700);
    registrarServicio(servicioMañana6);
    
    // Movimientos en la franja 18:00-23:59 (muchos movimientos - rojo)
    Entrada entradaMañana8 = new Entrada(proxNumEntrada, fechaMañana, "18:15", "Entrada vespertina", empleado3, vehiculo8);
    Entrada entradaMañana9 = new Entrada(proxNumEntrada, fechaMañana, "19:30", "Abolladura leve", empleado1, vehiculo9);
    Entrada entradaMañana10 = new Entrada(proxNumEntrada, fechaMañana, "20:45", "Sin observaciones", empleado2, vehiculo10);
    registrarEntrada(entradaMañana8);
    registrarEntrada(entradaMañana9);
    registrarEntrada(entradaMañana10);
    
    Salida salidaMañana5 = new Salida(proxNumSalida, fechaMañana, "21:00", "Salida normal", empleado3, vehiculo2);
    Salida salidaMañana6 = new Salida(proxNumSalida, fechaMañana, "22:15", "Sin observaciones", empleado1, vehiculo3);
    Salida salidaMañana7 = new Salida(proxNumSalida, fechaMañana, "23:30", "Conforme", empleado2, vehiculo4);
    registrarSalida(salidaMañana5, entradaMañana2);
    registrarSalida(salidaMañana6, entradaMañana3);
    registrarSalida(salidaMañana7, entradaMañana4);
    
    ServicioAdicional servicioMañana7 = new ServicioAdicional(proxNumServicio, "Lavado", fechaMañana, "19:00", "Lavado premium", vehiculo8, empleado3, 1500);
    ServicioAdicional servicioMañana8 = new ServicioAdicional(proxNumServicio, "Encerado", fechaMañana, "21:30", "Encerado especial", vehiculo9, empleado1, 2000);
    registrarServicio(servicioMañana7);
    registrarServicio(servicioMañana8);
    
    // --- CREAR MOVIMIENTOS PARA PASADO MAÑANA ---
    
    // Movimientos en la franja 00:00-05:59 (muchos movimientos - rojo)
    Entrada entradaPasado1 = new Entrada(proxNumEntrada, fechaPasadoMañana, "00:10", "Entrada madrugada", empleado1, vehiculo1);
    Entrada entradaPasado2 = new Entrada(proxNumEntrada, fechaPasadoMañana, "01:25", "Sin observaciones", empleado2, vehiculo2);
    Entrada entradaPasado3 = new Entrada(proxNumEntrada, fechaPasadoMañana, "02:40", "Falla en luces", empleado3, vehiculo3);
    registrarEntrada(entradaPasado1);
    registrarEntrada(entradaPasado2);
    registrarEntrada(entradaPasado3);
    
    Salida salidaPasado1 = new Salida(proxNumSalida, fechaPasadoMañana, "03:55", "Salida normal", empleado1, vehiculo5);
    Salida salidaPasado2 = new Salida(proxNumSalida, fechaPasadoMañana, "05:10", "Sin observaciones", empleado2, vehiculo6);
    registrarSalida(salidaPasado1, entradaMañana5);
    registrarSalida(salidaPasado2, entradaMañana6);
    
    ServicioAdicional servicioPasado1 = new ServicioAdicional(proxNumServicio, "Revisión de luces", fechaPasadoMañana, "01:00", "Revisión completa", vehiculo1, empleado1, 1000);
    ServicioAdicional servicioPasado2 = new ServicioAdicional(proxNumServicio, "Cambio de batería", fechaPasadoMañana, "03:30", "Batería nueva", vehiculo2, empleado2, 3500);
    ServicioAdicional servicioPasado3 = new ServicioAdicional(proxNumServicio, "Revisión de ruedas", fechaPasadoMañana, "05:00", "Presión y estado", vehiculo3, empleado3, 800);
    registrarServicio(servicioPasado1);
    registrarServicio(servicioPasado2);
    registrarServicio(servicioPasado3);
    
    // Movimientos en la franja 06:00-11:59 (pocos movimientos - verde)
    Entrada entradaPasado4 = new Entrada(proxNumEntrada, fechaPasadoMañana, "08:05", "Ingreso matutino", empleado1, vehiculo4);
    registrarEntrada(entradaPasado4);
    
    Salida salidaPasado3 = new Salida(proxNumSalida, fechaPasadoMañana, "10:20", "Salida normal", empleado2, vehiculo7);
    registrarSalida(salidaPasado3, entradaMañana7);
    
    ServicioAdicional servicioPasado4 = new ServicioAdicional(proxNumServicio, "Lavado", fechaPasadoMañana, "09:15", "Lavado rápido", vehiculo4, empleado1, 500);
    registrarServicio(servicioPasado4);
    
    // Movimientos en la franja 12:00-17:59 (cantidad media - amarillo)
    Entrada entradaPasado5 = new Entrada(proxNumEntrada, fechaPasadoMañana, "12:30", "Ingreso mediodía", empleado2, vehiculo5);
    Entrada entradaPasado6 = new Entrada(proxNumEntrada, fechaPasadoMañana, "14:45", "Espejo roto", empleado3, vehiculo6);
    registrarEntrada(entradaPasado5);
    registrarEntrada(entradaPasado6);
    
    Salida salidaPasado4 = new Salida(proxNumSalida, fechaPasadoMañana, "13:00", "Salida normal", empleado1, vehiculo8);
    Salida salidaPasado5 = new Salida(proxNumSalida, fechaPasadoMañana, "15:15", "Sin observaciones", empleado2, vehiculo9);
    Salida salidaPasado6 = new Salida(proxNumSalida, fechaPasadoMañana, "17:30", "Conforme", empleado3, vehiculo10);
    registrarSalida(salidaPasado4, entradaMañana8);
    registrarSalida(salidaPasado5, entradaMañana9);
    registrarSalida(salidaPasado6, entradaMañana10);
    
    // Movimientos en la franja 18:00-23:59 (pocos movimientos - verde)
    Entrada entradaPasado7 = new Entrada(proxNumEntrada, fechaPasadoMañana, "19:40", "Entrada vespertina", empleado1, vehiculo7);
    registrarEntrada(entradaPasado7);
    
    Salida salidaPasado7 = new Salida(proxNumSalida, fechaPasadoMañana, "20:55", "Salida normal", empleado2, vehiculo1);
    registrarSalida(salidaPasado7, entradaPasado1);
    
    ServicioAdicional servicioPasado5 = new ServicioAdicional(proxNumServicio, "Lavado interior", fechaPasadoMañana, "22:10", "Lavado completo", vehiculo7, empleado1, 1800);
    registrarServicio(servicioPasado5);

    System.out.println("Datos de prueba cargados exitosamente.");
}
}
