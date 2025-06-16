/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package view;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.*;

public class ClaroOscuro {
    
    private static boolean estaModoClaro = true; // con static, existe una unica copia de este atributo

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
        
        for(int i = 0; i < ventanas.length; i++){
            Window ventana = ventanas[i];
            if (ventana instanceof JFrame) {
                aplicarModo((JFrame) ventana);
            } 
        }
    }

    // Métodos wrapper para diálogos personalizados
    
    // mensaje de información
    public static void mostrarMensaje(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
    }
    
    // mensaje de error
    public static void mostrarError(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
    }
    
    // mensaje de advertencia
    public static void mostrarAdvertencia(Component parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, JOptionPane.WARNING_MESSAGE, JOptionPane.DEFAULT_OPTION);
    }
    
    // Muestra un diálogo de confirmación (Sí/No)
    // @return JOptionPane.YES_OPTION o JOptionPane.NO_OPTION
    public static int mostrarConfirmacion(Component parent, String mensaje, String titulo) {
        return mostrarDialogo(parent, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
    }
    
    // Muestra un diálogo de confirmación (Sí/No/Cancelar)
    // @return JOptionPane.YES_OPTION, JOptionPane.NO_OPTION o JOptionPane.CANCEL_OPTION
    public static int mostrarConfirmacionConCancelar(Component parent, String mensaje, String titulo) {
        return mostrarDialogo(parent, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
    }

    // crea y configura los diálogos
    private static int mostrarDialogo(Component parent, String mensaje, String titulo, int tipoMensaje, int tipoOpcion) {

        Color colorFondo;
        Color colorTexto;

        if(getEstaModoClaro()){
            // Modo claro
            colorFondo = Color.WHITE;
            colorTexto = Color.BLACK;
        } else {
            // Modo oscuro
            colorFondo = Color.BLACK;
            colorTexto = Color.WHITE;
        }

        // Crear el JOptionPane
        JOptionPane optionPane = new JOptionPane(mensaje, tipoMensaje, tipoOpcion);
        
        // Aplicar colores al JOptionPane
        aplicarModoADialogo(optionPane, colorFondo, colorTexto);
        
        // Crear el JDialog
        JDialog dialog = optionPane.createDialog(parent, titulo);
        
        // Aplicar colores al JDialog
        dialog.getContentPane().setBackground(colorFondo);
        aplicarModoAComponentes(dialog.getContentPane(), colorFondo, colorTexto);
        
        // Mostrar el diálogo
        dialog.setVisible(true);
        
        // Obtener el resultado
        Object resultado = optionPane.getValue();
        if (resultado == null) {
            return JOptionPane.CLOSED_OPTION;
        } else if (resultado instanceof Integer) {
            return ((Integer) resultado).intValue();
        }

        return JOptionPane.CLOSED_OPTION;
    }
    
    
    // Aplica el modo claro/oscuro específicamente a componentes de JOptionPane
    
    private static void aplicarModoADialogo(JOptionPane optionPane, Color fondo, Color texto) {
        optionPane.setBackground(fondo);
        optionPane.setForeground(texto);
        
        // Aplicar a todos los componentes del JOptionPane
        aplicarModoAComponentes(optionPane, fondo, texto);
    }

}
