/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package controlador;

import java.util.ArrayList;
import model.Cliente;
import model.Contrato;
import model.Empleado;
import model.Sistema;
import model.Vehiculo;
import util.ValidadorFechaHora;

public class ContratoControlador {
    
    private Sistema sistema;

    public ContratoControlador(Sistema sistema) {
        this.sistema = sistema;
    }

    public void registrarContrato(String valorMensualStr, String cedulaEmpleadoStr, String cedulaClienteStr, String matriculaVehiculoStr, String fechaInicio) throws Exception {
        // Validar que los campos no estén vacíos
        if (valorMensualStr == null || valorMensualStr.trim().isEmpty()) {
            throw new Exception("El valor mensual no puede estar vacío");
        }
        if (cedulaEmpleadoStr == null || cedulaEmpleadoStr.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un empleado");
        }
        if (cedulaClienteStr == null || cedulaClienteStr.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un cliente");
        }
        if (matriculaVehiculoStr == null || matriculaVehiculoStr.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un vehículo");
        }
        //Validar fecha
        ValidadorFechaHora.validarFecha(fechaInicio);

        // Convertir strings a números
        double valorMensual;

        try {
            valorMensual = Double.parseDouble(valorMensualStr);
        } catch (NumberFormatException e) {
            throw new Exception("El valor mensual debe ser un número válido");
        }

        // Validar valores numéricos
        if (valorMensual <= 0) {
            throw new Exception("El valor mensual debe ser mayor a cero");
        }

        // Convertir cédulas
        int cedulaEmpleado; 
        int cedulaCliente;
        
        try {
            cedulaEmpleado = Integer.parseInt(cedulaEmpleadoStr);
        } catch (NumberFormatException e) {
            throw new Exception("La cédula del empleado debe ser un número válido");
        }
        try {
            cedulaCliente = Integer.parseInt(cedulaClienteStr);
        } catch (NumberFormatException e) {
            throw new Exception("La cédula del cliente debe ser un número válido");
        }
        
        // Buscar en sistema
        Empleado empleado = sistema.buscarEmpleadoPorCedula(cedulaEmpleado);
        if (empleado == null) {
            throw new Exception("El empleado seleccionado no existe");
        }

        Cliente cliente = sistema.buscarClientePorCedula(cedulaCliente);
        if (cliente == null) {
            throw new Exception("El cliente seleccionado no existe");
        }

        Vehiculo vehiculo = sistema.buscarVehiculoPorMatricula(matriculaVehiculoStr);
        if (vehiculo == null) {
            throw new Exception("El vehículo seleccionado no existe");
        }
        
        // Verificar unicidad
         if (sistema.vehiculoTieneContrato(vehiculo)) {
             throw new Exception("El vehículo ya tiene un contrato activo");
         }

        // Crear y registrar
        Contrato contrato = new Contrato(valorMensual, empleado, cliente, vehiculo, 0, fechaInicio);
        boolean resultado = sistema.registrarContrato(contrato);

        if (!resultado) {
            throw new Exception("No se pudo registrar el contrato");
        }
    }

    public void eliminarContrato(int numContrato) throws Exception {
        if (!sistema.existeContratoConNumContrato(numContrato)) {
            throw new Exception("No existe un contrato con ese número de contrato");
        }

        boolean resultado = sistema.eliminarContrato(numContrato);

        if (!resultado) {
            throw new Exception("No se pudo eliminar el contrato");
        }
    }

    public ArrayList<Contrato> getListaContratos() {
        return sistema.getListaContratos();
    }
    public ArrayList<Cliente> getListaClientes() {
        return sistema.getListaClientes();
    }
    public ArrayList<Vehiculo> getListaVehiculos() {
        return sistema.getListaVehiculos();
    }
    public ArrayList<Empleado> getListaEmpleados() {
        return sistema.getListaEmpleados();
    }

    public Contrato buscarContratoPorNumContrato(int numContrato) {
        return sistema.buscarContratoPorNumContrato(numContrato);
    }
    
    public Sistema getSistema() {
        return sistema;
    }
    
    public String getFechaActual() {
        return ValidadorFechaHora.getFechaActual();
    }
}
