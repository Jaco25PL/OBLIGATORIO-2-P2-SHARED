/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Contrato {
    
    // Atributos
    private double valorMensual;
    private Empleado empleadoContrato;
    private Cliente clienteContrato;
    private Vehiculo vehiculoContrato;
    private int numContrato;
    private String fechaInicio;

    // ----------------------------------------

    // Constructor
    public Contrato(double unValorMensual, Empleado unEmpleadoContratEmpleado, Cliente unClienteContrato, Vehiculo unClienteVehiculo, int unNumContrato, String unFechaInicio){
        this.valorMensual = unValorMensual;
        this.empleadoContrato = unEmpleadoContratEmpleado;
        this.clienteContrato = unClienteContrato;
        this.vehiculoContrato = unClienteVehiculo;
        this.numContrato = unNumContrato;
        this.fechaInicio = unFechaInicio;
    }

    // Getters
    public double getValorMensual(){
        return valorMensual;
    }

    public Empleado getEmpleadoContrato(){
        return empleadoContrato;
    }

    public Cliente getClienteContrato(){
        return clienteContrato;
    }
    
    public Vehiculo getVehiculoContrato(){
        return vehiculoContrato;
    }

    public int getNumContrato(){
        return numContrato;
    }

    public String getFechaInicio(){
        return fechaInicio;
    }


}



