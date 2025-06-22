/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package controlador;

import java.util.ArrayList;
import model.Empleado;
import model.Entrada;
import model.Salida;
import model.Sistema;
import model.Vehiculo;
import util.ValidadorFechaHora;

public class SalidaControlador {
    
    private Sistema sistema;
    
    public SalidaControlador(Sistema sistema) {
        this.sistema = sistema;
    }
    
    public void registrarSalida(String fechaSalida, String horaSalida, String notas, String cedulaEmpleadoStr, String matriculaVehiculoEntrada) throws Exception {
        // Validar que los campos no estén vacíos y tengan el formato correcto
        ValidadorFechaHora.validarFecha(fechaSalida);
        ValidadorFechaHora.validarHora(horaSalida);
        
        if (notas == null) { // Las notas pueden estar vacías
            notas = "";
        }
        if (cedulaEmpleadoStr == null || cedulaEmpleadoStr.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un empleado");
        }
        if (matriculaVehiculoEntrada == null || matriculaVehiculoEntrada.trim().isEmpty()) {
            throw new Exception("Debe seleccionar una entrada");
        }

        // Convertir cédulas
        int cedulaEmpleado;

        try {
            cedulaEmpleado = Integer.parseInt(cedulaEmpleadoStr);
        } catch (NumberFormatException e) {
            throw new Exception("La cédula del empleado debe ser un número válido");
        }

        // Buscar en sistema
        Empleado empleado = sistema.buscarEmpleadoPorCedula(cedulaEmpleado);
        if (empleado == null) {
            throw new Exception("El empleado seleccionado no existe");
        }

        Vehiculo vehiculo = sistema.buscarVehiculoPorMatricula(matriculaVehiculoEntrada);
        if (vehiculo == null) {
            throw new Exception("El vehículo seleccionado no existe");
        }
        
        Entrada entrada = sistema.buscarEntradaPorMatricula(matriculaVehiculoEntrada);
        if (entrada == null) {
            throw new Exception("No se encontró una entrada activa para el vehículo seleccionado");
        }

        //Validar que fecha/hora de salida sea posterior a la de entrada
        ValidadorFechaHora.validarFechaHoraPosterior(entrada.getFecha(), entrada.getHora(), fechaSalida, horaSalida);
        
        // Verificar unicidad
        if (!sistema.vehiculoEstaEnParking(matriculaVehiculoEntrada)) {
            throw new Exception("El vehículo no está en el parking");
        }
        
        // Crear y registrar
        Salida salida = new Salida(0, fechaSalida, horaSalida, notas, empleado, vehiculo);
        boolean resultado = sistema.registrarSalida(salida, entrada);

        if (!resultado) {
            throw new Exception("No se pudo registrar la salida");
        }
    }

    public Sistema getSistema() {
        return sistema;
    }
    
    public Vehiculo buscarVehiculoPorMatricula(String matricula) {
        return sistema.buscarVehiculoPorMatricula(matricula);
    }
    
    public boolean vehiculoTieneContrato(Vehiculo vehiculo) {
        return sistema.vehiculoTieneContrato(vehiculo);
    }
    
    public String vehiculoTiempoEnParking(Vehiculo vehiculo) {
        return sistema.vehiculoTiempoEnParking(vehiculo);
    }
    
    public ArrayList<Entrada> getEntradasSinSalida(){
        return sistema.getEntradasSinSalida();
    }
    
    public ArrayList<Empleado> getListaEmpleados() {
        return sistema.getListaEmpleados();
    }
    
    public String getFechaActual() {
        return ValidadorFechaHora.getFechaActual();
    }
    
    public String getHoraActual() {
        return ValidadorFechaHora.getHoraActual();
    }
}
