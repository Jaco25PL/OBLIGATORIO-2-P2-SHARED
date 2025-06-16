/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package controlador;

import java.util.ArrayList;
import model.Empleado;
import model.Entrada;
import model.Sistema;
import model.Vehiculo;
import util.ValidadorFechaHora;

public class EntradaControlador {
    
    private Sistema sistema;

    public EntradaControlador(Sistema sistema) {
        this.sistema = sistema;
    }
    
    public void registrarEntrada(String fecha, String hora, String notas, String cedulaEmpleadoStr, String matriculaVehiculo) throws Exception {
        // Validar que los campos no estén vacíos y tengan el formato correcto
        ValidadorFechaHora.validarFecha(fecha);
        ValidadorFechaHora.validarHora(hora);
        
        if (notas == null) { // Las notas pueden estar vacías
            notas = "";
        }
        if (cedulaEmpleadoStr == null || cedulaEmpleadoStr.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un empleado");
        }
        if (matriculaVehiculo == null || matriculaVehiculo.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un vehículo");
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

        Vehiculo vehiculo = sistema.buscarVehiculoPorMatricula(matriculaVehiculo);
        if (vehiculo == null) {
            throw new Exception("El vehículo seleccionado no existe");
        }

        // Verificar unicidad
        if (sistema.vehiculoEstaEnParking(matriculaVehiculo)) {
            throw new Exception("El vehículo ya está en el parking");
        }

        // Crear y registrar
        Entrada entrada = new Entrada(0, fecha, hora, notas, empleado, vehiculo);
        boolean resultado = sistema.registrarEntrada(entrada);

        if (!resultado) {
            throw new Exception("No se pudo registrar la entrada");
        }
    }
    
    public ArrayList<Vehiculo> getVehiculosDisponiblesParaEntrada(){
        return sistema.getVehiculosDisponiblesParaEntrada();
    }
    
    public boolean vehiculoTieneContrato(Vehiculo vehiculo) {
        return sistema.vehiculoTieneContrato(vehiculo);
    }
    
    public Vehiculo buscarVehiculoPorMatricula(String matricula) {
        return sistema.buscarVehiculoPorMatricula(matricula);
    }
    
    public ArrayList<Vehiculo> getListaVehiculos() {
        return sistema.getListaVehiculos();
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
