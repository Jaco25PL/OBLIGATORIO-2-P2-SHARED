/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

import java.io.Serializable;

public class Vehiculo implements Serializable{
    
    //Atributos
    private String matricula;
    private String marca;
    private String modelo;
    private String estado;

    //Constructor
    public Vehiculo(String unaMatricula, String unaMarca, String unModelo, String unEstado){
        this.matricula = unaMatricula;
        this.marca = unaMarca;
        this.modelo = unModelo;
        this.estado = unEstado;
    }

    //Metodos
    public String getMatricula(){
        return matricula;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String unEstado){
        this.estado = unEstado;
    }

    @Override
    public String toString(){
        return "Matricula: " + getMatricula() + " | Marca: " + getMarca() + " | Modelo: " + getModelo() + " | Estado: " + getEstado();
    }

}
