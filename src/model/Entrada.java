/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Entrada extends RegistroMovimientos{
    
    //Atributos
    private Salida salidaAsociada;

    //Constructor
    public Entrada(int unNumMovimiento, String unaFecha, String unaHora, String unaNota, Empleado unEmpleado, Vehiculo unVehiculo) {
        super(unNumMovimiento, unaFecha, unaHora, unaNota, unEmpleado, unVehiculo);
        this.salidaAsociada = null; // Se asigna cuando se crea la salida
    }

    //Metodos
    public Salida getSalidaAsociada() {
        return salidaAsociada;
    }

    public void setSalidaAsociada(Salida unaSalida){
        this.salidaAsociada = unaSalida;
    }

    public boolean tieneSalida(){
        boolean tiene = false;
        //Completar coigo
        return tiene;
    }

    @Override
    public String toString(){
        return "Movimiento: Entrada | " + super.toString() + " | Salida Asociada #" + salidaAsociada.getNumMovimiento();
    }   
}
