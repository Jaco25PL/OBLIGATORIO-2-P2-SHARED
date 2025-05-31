package model;

public class RegistroMovimientos {
    
    // Atributos

    protected int numMovimiento;
    protected String fecha;
    protected String hora;
    protected String nota;
    protected Empleado empleado;
    protected Vehiculo vehiculo;

    // ----------------------------------------

    // Constructor

    public RegistroMovimientos(int unNumMovimiento, String unaFecha, String unaHora, String unaNota, Empleado unEmpleado, Vehiculo unVehiculo){
        this.numMovimiento = unNumMovimiento;
        this.fecha = unaFecha;
        this.hora = unaHora;
        this.nota = unaNota;
        this.empleado = unEmpleado;
        this.vehiculo = unVehiculo;
    }

    // Getters

    public int getNumMovimiento(){
        return numMovimiento;
    }

    public String getFecha(){
        return fecha;
    }

    public String getHora(){
        return hora;
    }

    public String getNota(){
        return nota;
    }

    public Empleado getEmpleado(){
        return empleado;
    }

    public Vehiculo getVehiculo(){
        return vehiculo;
    }

}
