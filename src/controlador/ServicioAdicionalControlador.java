/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package controlador;

import java.util.ArrayList;
import model.Empleado;
import model.ServicioAdicional;
import model.Sistema;
import model.Vehiculo;
import util.ValidadorFechaHora;

public class ServicioAdicionalControlador {
    
    private Sistema sistema;
    
    public ServicioAdicionalControlador(Sistema sistema){
        this.sistema = sistema;
    }
    
    public void registrarServicio(String tipoServicio, String fecha, String hora, 
        String cedulaEmpleadoStr, String matriculaVehiculo, String costoStr) throws Exception{
        // Validar que los campos no estén vacíos y tengan el formato correcto
        ValidadorFechaHora.validarFecha(fecha);
        ValidadorFechaHora.validarHora(hora);
        
        if (tipoServicio == null || tipoServicio.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un tipo de servicio");
        }
        if (costoStr == null || costoStr.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un tipo de servicio");
        }
        
        // Convertir costo
        double costo;
        try {
            costo = Double.parseDouble(costoStr);
            if (costo <= 0) {
                throw new Exception("El costo debe ser mayor a 0");
            }
        } catch (NumberFormatException e) {
            throw new Exception("El costo debe ser un número válido");
        }
        
        if (cedulaEmpleadoStr == null || cedulaEmpleadoStr.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un empleado");
        }
        if (matriculaVehiculo == null || matriculaVehiculo.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un vehículo");
        }
        
        // Convertir cédula
        int cedulaEmpleado;
        try {
            cedulaEmpleado = Integer.parseInt(cedulaEmpleadoStr);
        } catch (NumberFormatException e) {
            throw new Exception("La cédula del empleado debe ser un número válido");
        }
        
        // Buscar empleado y vehículo en sistema
        Empleado empleado = sistema.buscarEmpleadoPorCedula(cedulaEmpleado);
        if (empleado == null) {
            throw new Exception("Empleado no encontrado");
        }
        Vehiculo vehiculo = sistema.buscarVehiculoPorMatricula(matriculaVehiculo);
        if (vehiculo == null) {
            throw new Exception("Vehículo no encontrado");
        }
        
        // Crear y registrar servicio
        ServicioAdicional servicio = new ServicioAdicional(0, tipoServicio, fecha, hora, "", vehiculo, empleado, costo);
        boolean resultado = sistema.registrarServicio(servicio);
        
        if (!resultado) {
            throw new Exception("No se pudo registrar la salida");
        }
    }
    
    public ArrayList<Vehiculo> getListaVehiculos() {
        return sistema.getListaVehiculos();
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return sistema.getListaEmpleados();
    }

    public ArrayList<ServicioAdicional> getListaServicios() {
        return sistema.getListaServiciosAdicionales();
    }
    
    public String getFechaActual() {
        return ValidadorFechaHora.getFechaActual();
    }

    public String getHoraActual() {
        return ValidadorFechaHora.getHoraActual();
    }
    
    public ServicioAdicional buscarServicioPorMatricula(String matricula) {
        return sistema.buscarServicioPorMatricula(matricula);
    }
}
