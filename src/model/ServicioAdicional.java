/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class ServicioAdicional extends RegistroMovimientos{
    
    //Atributos
    private String tipoServicio;
    private double costoServicio;

    //Constructor
    public ServicioAdicional(int unNumMovimiento, String unTipoServicio, String unaFecha, String unaHora, String unaNota, Vehiculo unVehiculo, Empleado unEmpleado, double unCostoServicio){
        super(unNumMovimiento, unaFecha, unaHora, unaNota, unEmpleado, unVehiculo);
        this.tipoServicio = unTipoServicio;
        this.costoServicio = unCostoServicio;
    }

    //Metodos

    public String getTipoServicio() {
        return tipoServicio;
    }

    public double getCostoServicio() {
        return costoServicio;
    }

    @Override
    public String toString(){
        return "Movimiento: Servicio Adicional | " + super.toString() + " | Tipo: " + getTipoServicio() + " | Costo: " + getCostoServicio();
    }
}
