/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package controlador;

import java.util.ArrayList;
import model.Empleado;
import model.Sistema;

public class EmpleadoControlador {
    
    private Sistema sistema;
    
    public EmpleadoControlador(Sistema sistema){
        this.sistema = sistema;
    }
    
    public void registrarEmpleado(String nombre, String cedulaStr, String direccion, String numEmpleadoStr) throws Exception{
        // Validar que los campos no estén vacíos
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío");
        } 
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new Exception("La dirección no puede estar vacía");
        } 
        if (cedulaStr == null || cedulaStr.trim().isEmpty()) {
            throw new Exception("La cédula no puede estar vacía");
        } 
        if (numEmpleadoStr == null || numEmpleadoStr.trim().isEmpty()) {
            throw new Exception("El número de empleado no puede estar vacío");
        } 
        
        // Convertir strings a números
        int cedula;
        int numEmpleado;
        
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException e){
            throw new Exception("La cédula debe ser un número válido");
        }
        try {
            numEmpleado = Integer.parseInt(numEmpleadoStr);
        } catch (NumberFormatException e){
            throw new Exception("El número de empleado debe ser un número válido");
        }
        
        // Validar valores numéricos
        if (cedula <= 0) {
            throw new Exception("La cédula debe ser un número positivo");
        }
        if (numEmpleado <= 0) {
            throw new Exception("El número de empleado debe ser un número positivo");
        }
        
        // Verificar unicidad
        if (sistema.existeEmpleadoConCedula(cedula)) {
            throw new Exception("Ya existe un empleado con esa cédula");
        }
        
        // Crear y registrar cliente
        Empleado empleado = new Empleado(nombre, cedula, direccion, numEmpleado);
        boolean resultado = sistema.registrarEmpleado(empleado);
        
        if (!resultado) {
            throw new Exception("No se pudo registrar el empleado");
        }
    }
    
    public void eliminarEmpleado(int cedula) throws Exception{
        if (!sistema.existeEmpleadoConCedula(cedula)) {
            throw new Exception("No existe un empleado con esa cédula");
        }
        
        boolean resultado = sistema.eliminarEmpleado(cedula);
        
        if (!resultado) {
            throw new Exception("No se pudo eliminar el empleado");
        }
    }
    
    public ArrayList<Empleado> getListaEmpleados(){
        return sistema.getListaEmpleados();
    }
    
    public Empleado buscarEmpleadoPorCedula(int cedula){
        return sistema.buscarEmpleadoPorCedula(cedula);
    }
}