/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

import java.io.Serializable;

public abstract class RegistroMovimientos implements Serializable{

    private static final long serialVersionUID = 1L; // Para la serialización, version del objeto

    
    //Atributos

    protected int numMovimiento;
    protected String fecha;
    protected String hora;
    protected String nota;
    protected Empleado empleado;
    protected Vehiculo vehiculo;

    //Constructor

    public RegistroMovimientos(int unNumMovimiento, String unaFecha, String unaHora, String unaNota, Empleado unEmpleado, Vehiculo unVehiculo){
        this.numMovimiento = unNumMovimiento;
        this.fecha = unaFecha;
        this.hora = unaHora;
        this.nota = unaNota;
        this.empleado = unEmpleado;
        this.vehiculo = unVehiculo;
    }

    //Metodos

    public int getNumMovimiento(){
        return numMovimiento;
    }

    public void setNumMovimiento(int unNumMovimiento){
        this.numMovimiento = unNumMovimiento;
    }

    public String getFecha(){
        return fecha;
    }

    public String getHora(){
        return hora;
    }

    public String getNota(){
        return nota;
    }

    public Empleado getEmpleado(){
        return empleado;
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }

    @Override
    public String toString(){
        return getVehiculo().getMarca() + " " + getVehiculo().getModelo() + " - " + getVehiculo().getMatricula(); 
    }

}
