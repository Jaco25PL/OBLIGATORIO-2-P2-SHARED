/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package model;

public class Entrada {
    
    // Atributos
    private int numEntrada;
    private String fecha;
    private String hora;
    private String notas;
    private Empleado empleadoRecibe;
    private Vehiculo vehiculoEntrada;
    private Salida salidaAsociada;

    // Constructor
    public Entrada(String unaFecha, String unaHora, String unasNotas, Empleado unEmpleadoRecibe, Vehiculo unVehiculoEntrada, Salida unaSalidaAsociada) {
        this.numEntrada = 1;
        this.fecha = unaFecha;
        this.hora = unaHora;
        this.notas = unasNotas;
        this.empleadoRecibe = unEmpleadoRecibe;
        this.vehiculoEntrada = unVehiculoEntrada;
        this.salidaAsociada = unaSalidaAsociada;
    }

    // Getters
    public int getNumEntrada() {
        return numEntrada;
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

    public Empleado getEmpleadoRecibe() {
        return empleadoRecibe;
    }

    public Vehiculo getVehiculoEntrada() {
        return vehiculoEntrada;
    }

    public Salida getSalidaAsociada() {
        return salidaAsociada;
    }
}
