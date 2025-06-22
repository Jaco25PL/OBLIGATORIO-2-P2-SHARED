/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package controlador;

import java.io.IOException;
import model.Sistema;


public class SerializacionControlador {
    
    private Sistema sistema;


    public SerializacionControlador(Sistema sistema) {
        this.sistema = sistema;
    }

    
    public boolean existenDatosGuardados() {
        return sistema.existenDatosGuardados();
    }

    public boolean guardarSistema() {
        try {
            sistema.guardarDatos();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cargarSistema() {
        try {
            Sistema sistemaRecuperado = sistema.cargarDatos();
            this.sistema = sistemaRecuperado;
            
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public Sistema getSistema() {
        return sistema;
    }
}
