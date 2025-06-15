/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package view;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.*;

public class ClaroOscuro {
    
    private static boolean estaModoClaro = true;

    private static PropertyChangeSupport manejador = new PropertyChangeSupport(ClaroOscuro.class);

    public static boolean getEstaModoClaro(){
        return estaModoClaro;
    }
    
    public static void setModo(){
        boolean anterior = estaModoClaro;
        estaModoClaro = !estaModoClaro;

        manejador.firePropertyChange("modo", anterior, estaModoClaro);

        actualizarVentanas();
    }

    public static void addPropertyChangeListener(PropertyChangeListener listener){
        manejador.addPropertyChangeListener(listener);
    }

    public static void aplicarModo(JFrame frame){
        
        Color colorFondo; //Background
        Color colorTexto; //Foreground
        
        if(getEstaModoClaro()){
            colorFondo = Color.WHITE;
            colorTexto = Color.BLACK;
        } else {
            colorFondo = Color.BLACK;
            colorTexto = Color.WHITE;
        }
        
        //Aplicar al jFrame
        frame.getContentPane().setBackground(colorFondo);
        frame.getContentPane().setForeground(colorTexto);
        
        //Aplicar al menu  
        aplicarModoAMenu(frame, colorFondo, colorTexto);      
        
        //Aplicar a componentes
        aplicarModoAComponentes(frame.getContentPane(), colorFondo, colorTexto);
    }
    
    private static void aplicarModoAComponentes(Container container, Color fondo, Color texto){
        Component[] components = container.getComponents();
        
        for(int i=0; i<components.length; i++){

            Component comp = components[i];

            comp.setBackground(fondo);
            comp.setForeground(texto);

            if (comp instanceof Container) {
                aplicarModoAComponentes((Container) comp, fondo, texto);
            }
        }
    }

    private static void aplicarModoAMenu(JFrame frame, Color fondo, Color texto){
        if(frame.getJMenuBar() != null){
            
            frame.getJMenuBar().setBackground(fondo);
            frame.getJMenuBar().setForeground(texto);
            
            for (int i = 0; i < frame.getJMenuBar().getMenuCount(); i++) {
                
                if (frame.getJMenuBar().getMenu(i) != null) {
                    
                    frame.getJMenuBar().getMenu(i).setBackground(fondo);
                    frame.getJMenuBar().getMenu(i).setForeground(texto);
                    
                    for (int j = 0; j < frame.getJMenuBar().getMenu(i).getItemCount(); j++) {
                        
                        if(frame.getJMenuBar().getMenu(i).getItem(j) != null){
                            frame.getJMenuBar().getMenu(i).getItem(j).setBackground(fondo); 
                            frame.getJMenuBar().getMenu(i).getItem(j).setForeground(texto); 
                        }
                        
                    }
                }
            }
        }

    }

    private static void actualizarVentanas(){
        Window[] ventanas = Window.getWindows();
        
        for(int i=0; i<ventanas.length; i++){
            Window ventana = ventanas[i];
            if (ventana instanceof JFrame) {
                aplicarModo((JFrame) ventana);
            }
            
        }
    }

}

