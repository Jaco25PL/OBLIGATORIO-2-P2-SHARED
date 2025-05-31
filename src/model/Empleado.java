/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Empleado extends Persona {

    // Atributos
    private int numEmpleado;

    // ----------------------------------------

    // Constructor
    public Empleado(String unNombre, int unaCedula, String unaDireccion, int unNumEmpleado){
        super(unNombre, unaCedula, unaDireccion);
        this.numEmpleado = unNumEmpleado;
    }

    // Getters
    public int getNumEmpleado(){
        return numEmpleado;
    }

}
