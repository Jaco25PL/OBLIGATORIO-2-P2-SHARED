/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import model.Empleado;
import model.ServicioAdicional;
import model.Sistema;
import model.Vehiculo;
import util.ValidadorFechaHora;

public class VentanaServiciosAdicionales extends javax.swing.JFrame implements PropertyChangeListener{

    private Sistema sistema;
    
    public VentanaServiciosAdicionales(Sistema sistema) {
        this.sistema = sistema;
        
        initComponents();
        
        sistema.addPropertyChangeListener(this);
        
        jComboBoxServicio.removeAllItems();
        jComboBoxServicio.addItem("Lavado");
        jComboBoxServicio.addItem("Cambio de rueda");
        jComboBoxServicio.addItem("Limpieza de tapizado");
        jComboBoxServicio.addItem("Cambio de luces");
        jComboBoxServicio.addItem("Otro");
        
        jTextFieldFecha.setText(ValidadorFechaHora.getFechaActual());
        jTextFieldHora.setText(ValidadorFechaHora.getHoraActual());
        
        actualizarListaVehiculos();
        actualizarListaEmpleados();
        actualizarListaServicios();
        
        ClaroOscuro.aplicarModo(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelServiciosAdicionales = new javax.swing.JPanel();
        jLabelFecha = new javax.swing.JLabel();
        jButtonVaciar = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        jTextFieldFecha = new javax.swing.JTextField();
        jTextFieldHora = new javax.swing.JTextField();
        jLabelHora = new javax.swing.JLabel();
        jScrollPaneVehiculos = new javax.swing.JScrollPane();
        jListVehiculos = new javax.swing.JList<>();
        jLabelVehiculos = new javax.swing.JLabel();
        jScrollPaneEmpleados = new javax.swing.JScrollPane();
        jListEmpleados = new javax.swing.JList<>();
        jLabelEmpleados = new javax.swing.JLabel();
        jLabelNotas = new javax.swing.JLabel();
        jComboBoxServicio = new javax.swing.JComboBox<>();
        jScrollPaneServiciosRealizadps = new javax.swing.JScrollPane();
        jListServiciosRealizados = new javax.swing.JList<>();
        jLabelServiciosRealizados = new javax.swing.JLabel();
        jLabelCosto = new javax.swing.JLabel();
        jTextFieldCosto = new javax.swing.JTextField();
        jTextFieldVehiculo = new javax.swing.JTextField();
        jTextFieldEmpleado = new javax.swing.JTextField();
        jLabelFecha1 = new javax.swing.JLabel();
        jLabelFecha2 = new javax.swing.JLabel();
        jLabelCosto1 = new javax.swing.JLabel();
        jLabelCosto2 = new javax.swing.JLabel();
        jLabelFecha3 = new javax.swing.JLabel();
        jLabelFecha4 = new javax.swing.JLabel();
        jLabelFecha5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Servicios Adicionales");
        getContentPane().setLayout(null);

        jPanelServiciosAdicionales.setLayout(null);

        jLabelFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelFecha.setText("Datos del Seleccionado");
        jPanelServiciosAdicionales.add(jLabelFecha);
        jLabelFecha.setBounds(10, 160, 140, 16);

        jButtonVaciar.setText("Vaciar");
        jButtonVaciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVaciarActionPerformed(evt);
            }
        });
        jPanelServiciosAdicionales.add(jButtonVaciar);
        jButtonVaciar.setBounds(10, 250, 120, 27);

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        jPanelServiciosAdicionales.add(jButtonAgregar);
        jButtonAgregar.setBounds(130, 250, 240, 27);
        jPanelServiciosAdicionales.add(jTextFieldFecha);
        jTextFieldFecha.setBounds(80, 30, 160, 26);
        jPanelServiciosAdicionales.add(jTextFieldHora);
        jTextFieldHora.setBounds(80, 60, 160, 26);

        jLabelHora.setText("Hora:");
        jPanelServiciosAdicionales.add(jLabelHora);
        jLabelHora.setBounds(10, 60, 50, 16);

        jListVehiculos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneVehiculos.setViewportView(jListVehiculos);

        jPanelServiciosAdicionales.add(jScrollPaneVehiculos);
        jScrollPaneVehiculos.setBounds(380, 30, 180, 70);

        jLabelVehiculos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelVehiculos.setText("Lista de Vehiculos");
        jPanelServiciosAdicionales.add(jLabelVehiculos);
        jLabelVehiculos.setBounds(380, 10, 120, 16);

        jListEmpleados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPaneEmpleados.setViewportView(jListEmpleados);

        jPanelServiciosAdicionales.add(jScrollPaneEmpleados);
        jScrollPaneEmpleados.setBounds(380, 120, 180, 70);

        jLabelEmpleados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelEmpleados.setText("Lista de Empleados");
        jPanelServiciosAdicionales.add(jLabelEmpleados);
        jLabelEmpleados.setBounds(380, 100, 120, 16);

        jLabelNotas.setText("Servicio:");
        jPanelServiciosAdicionales.add(jLabelNotas);
        jLabelNotas.setBounds(10, 90, 50, 16);

        jComboBoxServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanelServiciosAdicionales.add(jComboBoxServicio);
        jComboBoxServicio.setBounds(80, 90, 160, 26);

        jListServiciosRealizados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListServiciosRealizados.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jListServiciosRealizadosPropertyChange(evt);
            }
        });
        jListServiciosRealizados.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListServiciosRealizadosValueChanged(evt);
            }
        });
        jScrollPaneServiciosRealizadps.setViewportView(jListServiciosRealizados);

        jPanelServiciosAdicionales.add(jScrollPaneServiciosRealizadps);
        jScrollPaneServiciosRealizadps.setBounds(380, 210, 180, 70);

        jLabelServiciosRealizados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelServiciosRealizados.setText("Lista de Servicios Realizados");
        jPanelServiciosAdicionales.add(jLabelServiciosRealizados);
        jLabelServiciosRealizados.setBounds(380, 190, 180, 16);

        jLabelCosto.setText("Empleado:");
        jPanelServiciosAdicionales.add(jLabelCosto);
        jLabelCosto.setBounds(10, 210, 70, 16);
        jPanelServiciosAdicionales.add(jTextFieldCosto);
        jTextFieldCosto.setBounds(80, 120, 160, 26);
        jPanelServiciosAdicionales.add(jTextFieldVehiculo);
        jTextFieldVehiculo.setBounds(80, 180, 160, 26);
        jPanelServiciosAdicionales.add(jTextFieldEmpleado);
        jTextFieldEmpleado.setBounds(80, 210, 160, 26);

        jLabelFecha1.setText("Fecha:");
        jPanelServiciosAdicionales.add(jLabelFecha1);
        jLabelFecha1.setBounds(10, 30, 50, 16);

        jLabelFecha2.setText("$");
        jPanelServiciosAdicionales.add(jLabelFecha2);
        jLabelFecha2.setBounds(250, 120, 120, 16);

        jLabelCosto1.setText("Costo:");
        jPanelServiciosAdicionales.add(jLabelCosto1);
        jLabelCosto1.setBounds(10, 120, 50, 16);

        jLabelCosto2.setText("Vehículo:");
        jPanelServiciosAdicionales.add(jLabelCosto2);
        jLabelCosto2.setBounds(10, 180, 70, 16);

        jLabelFecha3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelFecha3.setText("Datos del Servicio");
        jPanelServiciosAdicionales.add(jLabelFecha3);
        jLabelFecha3.setBounds(10, 10, 120, 16);

        jLabelFecha4.setText("dd/mm/yyyy");
        jPanelServiciosAdicionales.add(jLabelFecha4);
        jLabelFecha4.setBounds(250, 30, 120, 16);

        jLabelFecha5.setText("hh:mm 24hs");
        jPanelServiciosAdicionales.add(jLabelFecha5);
        jLabelFecha5.setBounds(250, 60, 120, 16);

        getContentPane().add(jPanelServiciosAdicionales);
        jPanelServiciosAdicionales.setBounds(0, 0, 570, 320);

        setBounds(0, 0, 584, 329);
    }// </editor-fold>//GEN-END:initComponents

    public void mostrarServicioSeleccionado(){
        ServicioAdicional servicio = (ServicioAdicional) jListServiciosRealizados.getSelectedValue();

        if (servicio != null) {
            try {
                jTextFieldVehiculo.setText(servicio.getVehiculo().getMarca() + " "
                        + servicio.getVehiculo().getModelo() + " - "
                        + servicio.getVehiculo().getMatricula());

                jTextFieldEmpleado.setText(servicio.getEmpleado().getNombre() + " - "
                        + servicio.getEmpleado().getCedula());
                
                jComboBoxServicio.setSelectedItem(servicio.getTipoServicio());
                jTextFieldFecha.setText(servicio.getFecha());
                jTextFieldHora.setText(servicio.getHora());
                jTextFieldCosto.setText(String.valueOf(servicio.getCostoServicio()));
                
            } catch (Exception e) {
                ClaroOscuro.mostrarError(this, "Error al cargar datos del servicio: " 
                    + e.getMessage(), "Error");
            }
        }
    }
    
    private void actualizarListaVehiculos(){
        ArrayList<Vehiculo> vehiculos = sistema.getListaVehiculos();
        jListVehiculos.setListData(vehiculos.toArray());
    }
    
    private void actualizarListaEmpleados(){
        ArrayList<Empleado> empleados = sistema.getListaEmpleados();
        jListEmpleados.setListData(empleados.toArray());
    }
    
    private void actualizarListaServicios(){
        ArrayList<ServicioAdicional> serviciosAdicionales = sistema.getListaServiciosAdicionales();
        jListServiciosRealizados.setListData(serviciosAdicionales.toArray());
    }
    
    private void limpiarCampos(){
        jTextFieldFecha.setText("");
        jTextFieldHora.setText("");
        jTextFieldCosto.setText("");
        jTextFieldVehiculo.setText("");
        jTextFieldEmpleado.setText("");
        jComboBoxServicio.setSelectedIndex(0);
        jListVehiculos.clearSelection();
        jListEmpleados.clearSelection();
        jListServiciosRealizados.clearSelection();
    }
    
    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
    
        try {
            String tipoServicio = (String) jComboBoxServicio.getSelectedItem();
            String fecha = jTextFieldFecha.getText();
            String hora = jTextFieldHora.getText();
            String costoStr = jTextFieldCosto.getText();

            Empleado empleadoSeleccionado = (Empleado) jListEmpleados.getSelectedValue();
            Vehiculo vehiculoSeleccionado = (Vehiculo) jListVehiculos.getSelectedValue();

            String cedulaEmpleadoStr = "";
            String matriculaVehiculo = "";

            if (empleadoSeleccionado != null) {
                cedulaEmpleadoStr = String.valueOf(empleadoSeleccionado.getCedula());
            }
            if (vehiculoSeleccionado != null) {
                matriculaVehiculo = vehiculoSeleccionado.getMatricula();
            }

             // Validar que los campos no estén vacíos y tengan el formato correcto
            ValidadorFechaHora.validarFecha(fecha);
            ValidadorFechaHora.validarHora(hora);
            
            if (tipoServicio == null || tipoServicio.trim().isEmpty()) {
                throw new Exception("Debe seleccionar un tipo de servicio");
            }
            if (costoStr == null || costoStr.trim().isEmpty()) {
                throw new Exception("Debe ingresar costo");
            }
            
            // Convertir costo
            double costo;
            try {
                costo = Double.parseDouble(costoStr);
                if (costo <= 0) {
                    throw new Exception("El costo debe ser mayor a 0");
                }
            } catch (NumberFormatException e) {
                throw new Exception("El costo debe ser un número válido");
            }
            
            if (cedulaEmpleadoStr == null || cedulaEmpleadoStr.trim().isEmpty()) {
                throw new Exception("Debe seleccionar un empleado");
            }
            if (matriculaVehiculo == null || matriculaVehiculo.trim().isEmpty()) {
                throw new Exception("Debe seleccionar un vehículo");
            }
            
            // Convertir cédula
            int cedulaEmpleado;
            try {
                cedulaEmpleado = Integer.parseInt(cedulaEmpleadoStr);
            } catch (NumberFormatException e) {
                throw new Exception("La cédula del empleado debe ser un número válido");
            }
            
            // Buscar empleado y vehículo en sistema
            Empleado empleado = sistema.buscarEmpleadoPorCedula(cedulaEmpleado);
            if (empleado == null) {
                throw new Exception("Empleado no encontrado");
            }
            Vehiculo vehiculo = sistema.buscarVehiculoPorMatricula(matriculaVehiculo);
            if (vehiculo == null) {
                throw new Exception("Vehículo no encontrado");
            }
            
            // Crear y registrar servicio
            ServicioAdicional servicio = new ServicioAdicional(0, tipoServicio, fecha, hora, "", vehiculo, empleado, costo);
            boolean resultado = sistema.registrarServicio(servicio);
            
            if (!resultado) {
                throw new Exception("No se pudo registrar la salida");
            }

            actualizarListaServicios();

            ClaroOscuro.mostrarMensaje(this, "Servicio adicional agregado con éxito", "Éxito");

            limpiarCampos();

        } catch (Exception e) {
            ClaroOscuro.mostrarError(this, e.getMessage(), "Error");
        }
        
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVaciarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jButtonVaciarActionPerformed

    private void jListServiciosRealizadosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jListServiciosRealizadosPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jListServiciosRealizadosPropertyChange

    private void jListServiciosRealizadosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListServiciosRealizadosValueChanged
        if (!evt.getValueIsAdjusting()) {
            mostrarServicioSeleccionado();
        }
    }//GEN-LAST:event_jListServiciosRealizadosValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonVaciar;
    private javax.swing.JComboBox<String> jComboBoxServicio;
    private javax.swing.JLabel jLabelCosto;
    private javax.swing.JLabel jLabelCosto1;
    private javax.swing.JLabel jLabelCosto2;
    private javax.swing.JLabel jLabelEmpleados;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelFecha1;
    private javax.swing.JLabel jLabelFecha2;
    private javax.swing.JLabel jLabelFecha3;
    private javax.swing.JLabel jLabelFecha4;
    private javax.swing.JLabel jLabelFecha5;
    private javax.swing.JLabel jLabelHora;
    private javax.swing.JLabel jLabelNotas;
    private javax.swing.JLabel jLabelServiciosRealizados;
    private javax.swing.JLabel jLabelVehiculos;
    private javax.swing.JList jListEmpleados;              
    private javax.swing.JList jListServiciosRealizados;    
    private javax.swing.JList jListVehiculos;              
    private javax.swing.JPanel jPanelServiciosAdicionales;
    private javax.swing.JScrollPane jScrollPaneEmpleados;
    private javax.swing.JScrollPane jScrollPaneServiciosRealizadps;
    private javax.swing.JScrollPane jScrollPaneVehiculos;
    private javax.swing.JTextField jTextFieldCosto;
    private javax.swing.JTextField jTextFieldEmpleado;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldHora;
    private javax.swing.JTextField jTextFieldVehiculo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        
        // Vehículos
        if ("vehiculoCreado".equals(propertyName) || "vehiculoEliminado".equals(propertyName)) {
            actualizarListaVehiculos();
        } 
        // Empleados
        else if ("empleadoCreado".equals(propertyName) || "empleadoEliminado".equals(propertyName)) {
            actualizarListaEmpleados();
        }
        // Servicios adicionales (este es el que falta implementar en Sistema)
        else if ("servicioCreado".equals(propertyName)) {
            actualizarListaServicios();
        }
    }

}
