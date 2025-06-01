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
        boolean tieneSalida = false;
        if(salidaAsociada != null){
            tieneSalida = true;
        }
        return tieneSalida;
    }

    @Override
    public String toString(){
        String salidaInfo = "Sin Salida";
        if(tieneSalida()){
            salidaInfo = "Salida Asociada #" + salidaAsociada.getNumMovimiento();
        }
        return "Movimiento: Entrada | " + super.toString() + " | " + salidaInfo;
    }   
}
