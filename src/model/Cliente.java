/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Cliente {

    // Atributos
    private String nombre;
    private int cedula;
    private String direccion;
    private int celular;
    private int añoCliente;

    // Constructor
    public Cliente(String unNombre, int unaCedula, String unaDireccion, int unCelular, int unAñoCliente){
        this.nombre = unNombre;
        this.cedula = unaCedula;
        this.direccion = unaDireccion;
        this.celular = unCelular;
        this.añoCliente = unAñoCliente;
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

    public int getCelular(){
        return celular;
    }

    public int getAñoCliente(){
        return añoCliente;
    }

}
