/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package controlador;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Cliente;
import model.Sistema;

public class ClienteControlador {
    
    private Sistema sistema;
    
    public ClienteControlador(Sistema sistema){
        this.sistema = sistema;
    }
    
    public void registrarCliente(String nombre, String cedulaStr, String direccion, String celularStr, String añoClienteStr) throws Exception{
        // Validar que los campos no estén vacíos
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío");
        } 
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new Exception("La dirección no puede estar vacía");
        } 
        if (cedulaStr == null || cedulaStr.trim().isEmpty()) {
            throw new Exception("La cédula no puede estar vacía");
        } 
        if (celularStr == null || celularStr.trim().isEmpty()) {
            throw new Exception("El celular no puede estar vacío");
        } 
        if (añoClienteStr == null || añoClienteStr.trim().isEmpty()) {
            throw new Exception("El año no puede estar vacío");
        }
        
        // Convertir strings a números
        int cedula;
        int celular;
        int añoCliente;
        
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException e){
            throw new Exception("La cédula debe ser un número válido");
        }
        try {
            celular = Integer.parseInt(celularStr);
        } catch (NumberFormatException e){
            throw new Exception("El celular debe ser un número válido");
        }
        try {
            añoCliente = Integer.parseInt(añoClienteStr);
        } catch (NumberFormatException e){
            throw new Exception("El año debe ser un número válido");
        }
        
        // Validar valores numéricos
        if (cedula <= 0) {
            throw new Exception("La cédula debe ser un número positivo");
        }
        if (celular <= 0) {
            throw new Exception("El celular debe ser un número positivo");
        }
        if (añoCliente <= 0 || añoCliente > LocalDate.now().getYear()) {
            throw new Exception("El año debe ser válido");
        }
        
        // Verificar cliente existente
        if (sistema.existeClienteConCedula(cedula)) {
            throw new Exception("Ya existe un cliente con esa cédula");
        }
        
        // Crear y registrar cliente
        Cliente cliente = new Cliente(nombre, cedula, direccion, celular, añoCliente);
        boolean resultado = sistema.registrarCliente(cliente);
        
        if (!resultado) {
            throw new Exception("No se pudo registrar el cliente");
        }
    }
    
    public Sistema getSistema() {
        return sistema;
    }

    public void eliminarCliente(int cedula) throws Exception{
        if (!sistema.existeClienteConCedula(cedula)) {
            throw new Exception("No existe un cliente con esa cédula");
        }
        
        boolean resultado = sistema.eliminarCliente(cedula);
        
        if (!resultado) {
            throw new Exception("No se pudo eliminar el cliente");
        }
    }
    
    public ArrayList<Cliente> getListaClientes(){
        return sistema.getListaClientes();
    }
    
    public Cliente buscarClientePorCedula(int cedula){
        return sistema.buscarClientePorCedula(cedula);
    }
    
}
