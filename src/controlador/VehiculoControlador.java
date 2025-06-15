/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package controlador;

import java.util.ArrayList;
import model.Sistema;
import model.Vehiculo;

public class VehiculoControlador {
    
    private Sistema sistema;
    
    public VehiculoControlador(Sistema sistema){
        this.sistema = sistema;
    }
    
    public void registrarVehiculo(String matricula, String marca, String modelo, String estado) throws Exception{
        // Validar que los campos no estén vacíos
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new Exception("La matrícula no puede estar vacía");
        } 
        if (marca == null || marca.trim().isEmpty()) {
            throw new Exception("La marca no puede estar vacía");
        } 
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new Exception("El modelo no puede estar vacío");
        } 
        if (estado == null || estado.trim().isEmpty()) {
            throw new Exception("El estado no puede estar vacío");
        } 
        
        // Verificar unicidad
        if (sistema.existeVehiculoConMatricula(matricula)) {
            throw new Exception("Ya existe un vehículo con esa matrícula");
        }
        
        // Crear y registrar
        Vehiculo vehiculo = new Vehiculo(matricula, marca, modelo, estado);
        boolean resultado = sistema.registrarVehiculo(vehiculo);
        
        if (!resultado) {
            throw new Exception("No se pudo registrar el vehículo");
        }
    }
    
    public void eliminarVehiculo(String matricula) throws Exception{
        if (!sistema.existeVehiculoConMatricula(matricula)) {
            throw new Exception("No existe un vehículo con esa matrícula");
        }
        
        boolean resultado = sistema.eliminarVehiculo(matricula);
        
        if (!resultado) {
            throw new Exception("No se pudo eliminar el vehículo");
        }
    }
    
    public ArrayList<Vehiculo> getListaVehiculos(){
        return sistema.getListaVehiculos();
    }
    
    public Vehiculo buscarVehiculoPorMatricula(String matricula){
        return sistema.buscarVehiculoPorMatricula(matricula);
    }
}
