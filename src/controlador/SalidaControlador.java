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

public class SalidaControlador {
    
    private Sistema sistema;
    
    public SalidaControlador(Sistema sistema) {
        this.sistema = sistema;
    }
    
    public void registrarSalida(String fecha, String hora, String notas, String cedulaEmpleadoStr, String matriculaVehiculoEntrada) throws Exception {
        // Validar que los campos no estén vacíos
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new Exception("La fecha no puede estar vacía");
        }
        if (hora == null || hora.trim().isEmpty()) {
            throw new Exception("La hora no puede estar vacía");
        }
        if (notas == null || notas.trim().isEmpty()) {
            throw new Exception("Las notas no pueden estar vacías");
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

        // Verificar unicidad
        if (!sistema.vehiculoEstaEnParking(matriculaVehiculoEntrada)) {
            throw new Exception("El vehículo no está en el parking");
        }

        // Crear y registrar
        Salida salida = new Salida(0, fecha, hora, notas, empleado, vehiculo);
        boolean resultado = sistema.registrarSalida(salida, entrada);

        if (!resultado) {
            throw new Exception("No se pudo registrar la salida");
        }
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
}
