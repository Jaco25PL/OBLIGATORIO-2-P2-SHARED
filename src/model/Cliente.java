/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Cliente extends Persona {

    // Atributos
    private int celular;
    private int añoCliente;

    // ----------------------------------------

    // Constructor
    public Cliente(String unNombre, int unaCedula, String unaDireccion, int unCelular, int unAñoCliente){
        super(unNombre, unaCedula, unaDireccion);
        this.celular = unCelular;
        this.añoCliente = unAñoCliente;
    }

    // Getters
    public int getCelular(){
        return celular;
    }

    public int getAñoCliente(){
        return añoCliente;
    }

}
