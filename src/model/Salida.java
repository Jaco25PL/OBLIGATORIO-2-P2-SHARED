/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Salida extends RegistroMovimientos{
    
    //Atributos
    
    //Constructor
    public Salida(int unNumMovimiento, String unaFecha, String unaHora, String unaNota, Empleado unEmpleado, Vehiculo unVehiculo) {
        super(unNumMovimiento, unaFecha, unaHora, unaNota, unEmpleado, unVehiculo);
    }

    //Metodos

    @Override
    public String toString(){
        return "Movimiento: Salida | " + super.toString();
    }
}
