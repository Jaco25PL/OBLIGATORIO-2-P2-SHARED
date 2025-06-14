package controlador;

import model.Cliente;
import model.Sistema;

public class ClienteControlador {
    
    public void agregarCliente(String nombre, int cedula, String direccion, int celular, int añoCliente){
        
        Cliente cliente = new Cliente(nombre, cedula, direccion, celular, añoCliente);
        
        boolean a = Sistema.SingletonSistema().registrarCliente(cliente);
    }
    
}
