/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package view;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ClaroOscuro {
    
    private static boolean estaModoClaro = true;
    
    public static void setModo(){
        estaModoClaro = !estaModoClaro;
    }
    
    public static void aplicarModo(JFrame frame){
        
        Color colorFondo; //Background
        Color colorTexto; //Foreground
        
        if(estaModoClaro){
            colorFondo = Color.WHITE;
            colorTexto = Color.BLACK;
        } else {
            colorFondo = Color.BLACK;
            colorTexto = Color.WHITE;
        }
        
        //Aplicar al fondo de la ventana
        frame.getContentPane().setBackground(colorFondo);
        frame.getContentPane().setForeground(colorTexto);
        
        //Aplicar al menu        
        if(frame.getJMenuBar() != null){
            
            frame.getJMenuBar().setBackground(colorFondo);
            frame.getJMenuBar().setForeground(colorTexto);
            
            for (int i = 0; i < frame.getJMenuBar().getMenuCount(); i++) {
                
                if (frame.getJMenuBar().getMenu(i) != null) {
                    
                    frame.getJMenuBar().getMenu(i).setBackground(colorFondo);
                    frame.getJMenuBar().getMenu(i).setForeground(colorTexto);
                    
                    for (int j = 0; j < frame.getJMenuBar().getMenu(i).getItemCount(); j++) {
                        
                        if(frame.getJMenuBar().getMenu(i).getItem(j) != null){
                            frame.getJMenuBar().getMenu(i).getItem(j).setBackground(colorFondo); 
                            frame.getJMenuBar().getMenu(i).getItem(j).setForeground(colorTexto); 
                        }
                        
                    }
                }
            }
        }
        
    }
    
    public static void aplicarModoABoton(JButton boton){
        Color colorFondo;
        Color colorTexto;
        
        if(estaModoClaro){
            colorFondo = Color.WHITE;
            colorTexto = Color.BLACK;
        } else {
            colorFondo = Color.BLACK;
            colorTexto = Color.WHITE;
        }
        
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
    }
            
}
