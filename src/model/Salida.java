/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Salida {
    
    // Atributos
    private int numSalida;
    private String fecha;
    private String hora;
    private String notas;
    private Empleado empleadoEntrega;
    
    //Constructor
    public Salida(String unaFecha, String unaHora, String unasNotas, Empleado unEmpleadoEntrega) {
        this.numSalida = 1;
        this.fecha = unaFecha;
        this.hora = unaHora;
        this.notas = unasNotas;
        this.empleadoEntrega = unEmpleadoEntrega;
    }

    // Getters
    public int getNumSalida() {
        return numSalida;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getNotas() {
        return notas;
    }

    public Empleado getEmpleadoEntrega() {
        return empleadoEntrega;
    }
}
