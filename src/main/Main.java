/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */

package main;

import model.Sistema;
import view.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {
        
        Sistema sistema = new Sistema();
        
        VentanaPrincipal ventana = new VentanaPrincipal(sistema);
        ventana.setVisible(true);
    }
    
}

