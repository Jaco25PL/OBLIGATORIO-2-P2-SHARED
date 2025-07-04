/**
 * Autores: [Matias Piedra 354007] y [Joaquin Piedra 304804]
 */
package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import model.Sistema;
import model.Vehiculo;

public class VentanaGestionVehiculos extends javax.swing.JFrame implements PropertyChangeListener{

    private Sistema sistema;
    
    public VentanaGestionVehiculos(Sistema sistema) {
        this.sistema = sistema;
        
        initComponents();

        sistema.addPropertyChangeListener(this);
        
        actualizarListaVehiculos();
        
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

        jPanelGestionVehiculos = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jScrollPaneVehiculos = new javax.swing.JScrollPane();
        jListVehiculos = new javax.swing.JList();
        jLabelVehiculos = new javax.swing.JLabel();
        jLabelMarca = new javax.swing.JLabel();
        jLabelMatricula = new javax.swing.JLabel();
        jLabelEstado = new javax.swing.JLabel();
        jLabelModelo = new javax.swing.JLabel();
        jTextFieldMarca = new javax.swing.JTextField();
        jTextFieldModelo = new javax.swing.JTextField();
        jTextFieldMatricula = new javax.swing.JTextField();
        jTextFieldEstado = new javax.swing.JTextField();
        jButtonVaciar = new javax.swing.JButton();
        jLabelVehiculos1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Vehículos");
        getContentPane().setLayout(null);

        jPanelGestionVehiculos.setLayout(null);

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });
        jPanelGestionVehiculos.add(jButtonAgregar);
        jButtonAgregar.setBounds(130, 250, 240, 27);

        jListVehiculos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListVehiculos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListVehiculosValueChanged(evt);
            }
        });
        jScrollPaneVehiculos.setViewportView(jListVehiculos);

        jPanelGestionVehiculos.add(jScrollPaneVehiculos);
        jScrollPaneVehiculos.setBounds(380, 40, 180, 240);

        jLabelVehiculos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelVehiculos.setText("Datos del Vehículo");
        jPanelGestionVehiculos.add(jLabelVehiculos);
        jLabelVehiculos.setBounds(10, 10, 120, 16);

        jLabelMarca.setText("Marca:");
        jPanelGestionVehiculos.add(jLabelMarca);
        jLabelMarca.setBounds(10, 80, 50, 16);

        jLabelMatricula.setText("Matrícula:");
        jPanelGestionVehiculos.add(jLabelMatricula);
        jLabelMatricula.setBounds(10, 40, 60, 16);

        jLabelEstado.setText("Estado:");
        jPanelGestionVehiculos.add(jLabelEstado);
        jLabelEstado.setBounds(10, 160, 50, 16);

        jLabelModelo.setText("Modelo:");
        jPanelGestionVehiculos.add(jLabelModelo);
        jLabelModelo.setBounds(10, 120, 60, 16);
        jPanelGestionVehiculos.add(jTextFieldMarca);
        jTextFieldMarca.setBounds(80, 80, 160, 26);
        jPanelGestionVehiculos.add(jTextFieldModelo);
        jTextFieldModelo.setBounds(80, 120, 160, 26);
        jPanelGestionVehiculos.add(jTextFieldMatricula);
        jTextFieldMatricula.setBounds(80, 40, 160, 26);
        jPanelGestionVehiculos.add(jTextFieldEstado);
        jTextFieldEstado.setBounds(80, 160, 160, 26);

        jButtonVaciar.setText("Vaciar");
        jButtonVaciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVaciarActionPerformed(evt);
            }
        });
        jPanelGestionVehiculos.add(jButtonVaciar);
        jButtonVaciar.setBounds(10, 250, 120, 27);

        jLabelVehiculos1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelVehiculos1.setText("Lista de Vehículos");
        jPanelGestionVehiculos.add(jLabelVehiculos1);
        jLabelVehiculos1.setBounds(380, 10, 110, 16);

        getContentPane().add(jPanelGestionVehiculos);
        jPanelGestionVehiculos.setBounds(0, 0, 570, 320);

        setBounds(0, 0, 584, 329);
    }// </editor-fold>//GEN-END:initComponents

    private void jListVehiculosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListVehiculosValueChanged
        if (!evt.getValueIsAdjusting()) {
            mostrarVehiculoSeleccionado();
        }
    }//GEN-LAST:event_jListVehiculosValueChanged

    private void mostrarVehiculoSeleccionado() {
        Vehiculo vehiculo = (Vehiculo) jListVehiculos.getSelectedValue();

        if (vehiculo != null) {
            try {
                jTextFieldMatricula.setText(vehiculo.getMatricula());
                jTextFieldMarca.setText(String.valueOf(vehiculo.getMarca()));
                jTextFieldModelo.setText(vehiculo.getModelo());
                jTextFieldEstado.setText(vehiculo.getEstado());
            } catch (Exception e) {
                ClaroOscuro.mostrarError(this, "Error al cargar datos del vehículo: " + e.getMessage(),
                        "Error");
            }
        }
    }

    private void limpiarCampos() {
        jTextFieldMatricula.setText("");
        jTextFieldMarca.setText("");
        jTextFieldModelo.setText("");
        jTextFieldEstado.setText("");
    }

    private void actualizarListaVehiculos() {
        ArrayList<Vehiculo> vehiculos = sistema.getListaVehiculos();
        jListVehiculos.setListData(vehiculos.toArray());
    }
    
    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        
        try {
            String matricula = jTextFieldMatricula.getText();
            String marca = jTextFieldMarca.getText();
            String modelo = jTextFieldModelo.getText();
            String estado = jTextFieldEstado.getText();

            // Validar que los campos no estén vacíos
            if (matricula == null || matricula.trim().isEmpty()) {
                throw new Exception("La matrícula no puede estar vacía");
            } 
            if (marca == null || marca.trim().isEmpty()) {
                throw new Exception("La marca no puede estar vacía");
            } 
            if (modelo == null || modelo.trim().isEmpty()) {
                throw new Exception("El modelo no puede estar vacío");
            } 
            if (estado == null || estado.trim().isEmpty()) {
                throw new Exception("El estado no puede estar vacío");
            } 
            
            // Verificar unicidad
            if (sistema.existeVehiculoConMatricula(matricula)) {
                throw new Exception("Ya existe un vehículo con esa matrícula");
            }
            
            // Crear y registrar
            Vehiculo vehiculo = new Vehiculo(matricula, marca, modelo, estado);
            boolean resultado = sistema.registrarVehiculo(vehiculo);
            
            if (!resultado) {
                throw new Exception("No se pudo registrar el vehículo");
            }

            actualizarListaVehiculos();

            ClaroOscuro.mostrarMensaje(this, "Vehículo agregado con éxito", "Éxito");

            limpiarCampos();

        } catch (Exception e) {
            ClaroOscuro.mostrarError(this, e.getMessage(), "Error");
        }
        
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVaciarActionPerformed
        limpiarCampos();
        jListVehiculos.clearSelection();
    }//GEN-LAST:event_jButtonVaciarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonVaciar;
    private javax.swing.JLabel jLabelEstado;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JLabel jLabelMatricula;
    private javax.swing.JLabel jLabelModelo;
    private javax.swing.JLabel jLabelVehiculos;
    private javax.swing.JLabel jLabelVehiculos1;
    private javax.swing.JList jListVehiculos;
    private javax.swing.JPanel jPanelGestionVehiculos;
    private javax.swing.JScrollPane jScrollPaneVehiculos;
    private javax.swing.JTextField jTextFieldEstado;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldModelo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        String propertyName = evt.getPropertyName();

        if("vehiculoCreado".equals(propertyName) || "vehiculoEliminado".equals(propertyName)) {
            actualizarListaVehiculos();
        } 

    }
}
