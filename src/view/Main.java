/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */

package view;

import model.Cliente;
import model.Empleado;

public class Main {

    public static void main(String[] args) {
        
        //Test Cliente
        Cliente clienteA = new Cliente("Matias", 56014384, "Uruguay", 93994874, 2025);
        System.out.println(clienteA);
        //Test Empleado
        Empleado empleadoA = new Empleado("Agustin", 56014384, "Uruguay", 354007);
        System.out.println(empleadoA);
    }
    
}
