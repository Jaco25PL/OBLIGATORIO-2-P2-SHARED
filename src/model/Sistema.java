/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

import java.util.ArrayList;

public class Sistema {
    
    // Listas vacias
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Vehiculo> listaVehiculos;
    private ArrayList<Empleado> listaEmpleados;
    private ArrayList<Contrato> listaContratos;
    private ArrayList<Entrada> listaEntradas;
    private ArrayList<Salida> listaSalidas;
    private ArrayList<ServicioAdicional> listaServiciosAdicionales;

    // Contadores para autonumerar
    private int proxNumContrato;
    private int proxNumEntrada;
    private int proxNumSalida;
    private int proxNumServicio;

    // ----------------------------------------

    public Sistema() {
        // Listas inicializadas
        this.listaClientes = new ArrayList<>();
        this.listaVehiculos = new ArrayList<>();
        this.listaEmpleados = new ArrayList<>();
        this.listaContratos = new ArrayList<>();
        this.listaEntradas = new ArrayList<>();
        this.listaSalidas = new ArrayList<>();
        this.listaServiciosAdicionales = new ArrayList<>();

        // Contadores inicializados
        this.proxNumContrato = 1;
        this.proxNumEntrada = 1;
        this.proxNumSalida = 1;
        this.proxNumServicio = 1;

    }

    //Getters
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public ArrayList<Contrato> getListaContratos() {
        return listaContratos;
    }

    public ArrayList<Entrada> getListaEntradas() {
        return listaEntradas;
    }

    public ArrayList<Salida> getListaSalidas() {
        return listaSalidas;
    }

    public ArrayList<ServicioAdicional> getListaServiciosAdicionales() {
        return listaServiciosAdicionales;
    }

    public int getProxNumContrato() {
        return proxNumContrato;
    }

    public int getProxNumEntrada() {
        return proxNumEntrada;
    }

    public int getProxNumSalida() {
        return proxNumSalida;
    }

    public int getProxNumServicio() {
        return proxNumServicio;
    }
}
