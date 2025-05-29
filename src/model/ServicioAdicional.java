/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class ServicioAdicional {
    
    // Atributos
    private int numServicio;
    private String tipoServicio;
    private String fecha;
    private String hora;
    private Vehiculo vehiculoServicio;
    private Empleado empleadoServicio;
    private double costoServicio;

    //Constructor
    public ServicioAdicional(String unTipoServicio, String unaFecha, String unaHora, Vehiculo unVehiculoServicio, Empleado unEmpleadoServicio, double unCostoServicio){
        this.numServicio = 1;
        this.tipoServicio = unTipoServicio;
        this.fecha = unaFecha;
        this.hora = unaHora;
        this.vehiculoServicio = unVehiculoServicio;
        this.empleadoServicio = unEmpleadoServicio;
        this.costoServicio = unCostoServicio;
    }

    //Getters
    public int getNumServicio() {
        return numServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Vehiculo getVehiculoServicio() {
        return vehiculoServicio;
    }

    public Empleado getEmpleadoServicio() {
        return empleadoServicio;
    }

    public double getCostoServicio() {
        return costoServicio;
    }

}
