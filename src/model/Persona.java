/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

import java.io.Serializable;

public abstract class Persona implements Serializable{
    
    // Atributos

    protected String nombre;
    protected int cedula;
    protected String direccion;

    // ----------------------------------------

    // Constructor
    public Persona(String unNombre, int unaCedula, String unaDireccion) {
        this.nombre = unNombre;
        this.cedula = unaCedula;
        this.direccion = unaDireccion;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public String getDireccion(){
        return direccion;
    }

    @Override
    public String toString(){
        return getNombre() + " | CI: " + getCedula() + " | Dirección: " + getDireccion();
    }
}
