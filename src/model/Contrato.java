/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

import java.io.Serializable;

public class Contrato implements Serializable{
    
    private static final long serialVersionUID = 1L; // Para la serialización, version del objeto

    //Atributos
    private double valorMensual;
    private Empleado empleadoContrato;
    private Cliente clienteContrato;
    private Vehiculo vehiculoContrato;
    private int numContrato = 1;
    private String fechaInicio;

    //Constructor
    public Contrato(double unValorMensual, Empleado unEmpleadoContrato, Cliente unClienteContrato, Vehiculo unClienteVehiculo, int unNumContrato, String unFechaInicio){
        this.valorMensual = unValorMensual;
        this.empleadoContrato = unEmpleadoContrato;
        this.clienteContrato = unClienteContrato;
        this.vehiculoContrato = unClienteVehiculo;
        this.numContrato = unNumContrato;
        this.fechaInicio = unFechaInicio;
    }

    //Metodos
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

    public void setNumContrato(int unNumContrato){
        this.numContrato = unNumContrato;
    }

    public String getFechaInicio(){
        return fechaInicio;
    }

    @Override
    public String toString(){
        return 
            getNumContrato() 
            + " - " + getClienteContrato().getNombre() 
            + " - " + getVehiculoContrato().getMarca() + " " + getVehiculoContrato().getModelo()
            + " - Empleado: " + getEmpleadoContrato().getNombre();
    
    }
}



