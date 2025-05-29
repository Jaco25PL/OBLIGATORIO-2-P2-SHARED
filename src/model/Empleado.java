/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Empleado {
   
    // Atributos
    private String nombre;
    private int cedula;
    private String direccion;
    private int numEmpleado;

    // Constructor
    public Empleado(String unNombre, int unaCedula, String unaDireccion, int unNumEmpleado){
        this.nombre = unNombre;
        this.cedula = unaCedula;
        this.direccion = unaDireccion;
        this.numEmpleado = unNumEmpleado;
    }

    // Getters
    public String getNombre(){
        return nombre;
    }

    public int getCedula(){
        return cedula;
    }

    public String getDireccion(){
        return direccion;
    }

    public int getNumEmpleado(){
        return numEmpleado;
    }

}
