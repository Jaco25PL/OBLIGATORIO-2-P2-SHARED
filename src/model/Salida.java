/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Salida extends RegistroMovimientos{
    
    //Atributos
    private Entrada entrada;

    //Constructor
    public Salida(int unNumMovimiento, String unaFecha, String unaHora, String unaNota, Empleado unEmpleado, Vehiculo unVehiculo) {
        super(unNumMovimiento, unaFecha, unaHora, unaNota, unEmpleado, unVehiculo);
    }

    //Metodos
    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
