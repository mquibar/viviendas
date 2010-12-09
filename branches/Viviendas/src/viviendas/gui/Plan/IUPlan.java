/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IUPlan.java
 *
 * Created on 06/12/2010, 16:54:24
 */

package viviendas.gui.Plan;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class IUPlan extends javax.swing.JInternalFrame {

    /** Creates new form IUPlan */
    public IUPlan() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        labNombre = new javax.swing.JLabel();
        labTipoPlan = new javax.swing.JLabel();
        labNroViviendasPorAño = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        texNombre = new javax.swing.JTextField();
        comTipoPlan = new javax.swing.JComboBox();
        formatedViviendas = new javax.swing.JFormattedTextField();
        spinAños = new javax.swing.JSpinner();
        texTotalViviendas = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabProvincias = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnAgregarTodas = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnQuitarTodas = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabProvinciasSeleccionadas = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Plan de Vivienda");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paso 1: Especificar Datos del Plan", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        labNombre.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(labNombre, gridBagConstraints);

        labTipoPlan.setText("Tipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(labTipoPlan, gridBagConstraints);

        labNroViviendasPorAño.setText("Viviendas x Año");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(labNroViviendasPorAño, gridBagConstraints);

        jLabel4.setText("Cantidad Años");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Total Viviendas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(jLabel5, gridBagConstraints);

        texNombre.setPreferredSize(new java.awt.Dimension(150, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(texNombre, gridBagConstraints);

        comTipoPlan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel1.add(comTipoPlan, gridBagConstraints);

        formatedViviendas.setPreferredSize(new java.awt.Dimension(150, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel1.add(formatedViviendas, gridBagConstraints);

        spinAños.setModel(new javax.swing.SpinnerNumberModel(Byte.valueOf((byte)1), Byte.valueOf((byte)1), Byte.valueOf((byte)50), Byte.valueOf((byte)1)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel1.add(spinAños, gridBagConstraints);

        texTotalViviendas.setPreferredSize(new java.awt.Dimension(150, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        jPanel1.add(texTotalViviendas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paso 2: Agregar Ciudades al Plan", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 18))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(350, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(350, 200));

        tabProvincias.setMinimumSize(new java.awt.Dimension(350, 200));
        tabProvincias.setPreferredSize(new java.awt.Dimension(350, 200));
        jScrollPane1.setViewportView(tabProvincias);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jScrollPane1, gridBagConstraints);

        btnAgregar.setText(">");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        jPanel3.add(btnAgregar, gridBagConstraints);

        btnAgregarTodas.setText(">>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        jPanel3.add(btnAgregarTodas, gridBagConstraints);

        btnQuitar.setText("<");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        jPanel3.add(btnQuitar, gridBagConstraints);

        btnQuitarTodas.setText("<<");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        jPanel3.add(btnQuitarTodas, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(350, 200));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(350, 200));

        tabProvinciasSeleccionadas.setMinimumSize(new java.awt.Dimension(350, 200));
        tabProvinciasSeleccionadas.setPreferredSize(new java.awt.Dimension(350, 200));
        jScrollPane2.setViewportView(tabProvinciasSeleccionadas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel3, gridBagConstraints);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(btnAceptar, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarTodas;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnQuitarTodas;
    private javax.swing.JComboBox comTipoPlan;
    private javax.swing.JFormattedTextField formatedViviendas;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labNombre;
    private javax.swing.JLabel labNroViviendasPorAño;
    private javax.swing.JLabel labTipoPlan;
    private javax.swing.JSpinner spinAños;
    private javax.swing.JTable tabProvincias;
    private javax.swing.JTable tabProvinciasSeleccionadas;
    private javax.swing.JTextField texNombre;
    private javax.swing.JTextField texTotalViviendas;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar(JButton btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    public JButton getBtnAgregarTodas() {
        return btnAgregarTodas;
    }

    public void setBtnAgregarTodas(JButton btnAgregarTodas) {
        this.btnAgregarTodas = btnAgregarTodas;
    }

    public JButton getBtnQuitar() {
        return btnQuitar;
    }

    public void setBtnQuitar(JButton btnQuitar) {
        this.btnQuitar = btnQuitar;
    }

    public JButton getBtnQuitarTodas() {
        return btnQuitarTodas;
    }

    public void setBtnQuitarTodas(JButton btnQuitarTodas) {
        this.btnQuitarTodas = btnQuitarTodas;
    }

    public JComboBox getComTipoPlan() {
        return comTipoPlan;
    }

    public void setComTipoPlan(JComboBox comTipoPlan) {
        this.comTipoPlan = comTipoPlan;
    }

    public JFormattedTextField getFormatedViviendas() {
        return formatedViviendas;
    }

    public void setFormatedViviendas(JFormattedTextField formatedViviendas) {
        this.formatedViviendas = formatedViviendas;
    }

    public JSpinner getSpinAños() {
        return spinAños;
    }

    public void setSpinAños(JSpinner spinAños) {
        this.spinAños = spinAños;
    }

    public JTable getTabProvinciasSeleccionadas() {
        return tabProvinciasSeleccionadas;
    }

    public void setTabProvinciasSeleccionadas(JTable tabProvinciasSeleccionadas) {
        this.tabProvinciasSeleccionadas = tabProvinciasSeleccionadas;
    }

    public JTextField getTexNombre() {
        return texNombre;
    }

    public void setTexNombre(JTextField texNombre) {
        this.texNombre = texNombre;
    }

    public JTextField getTexTotalViviendas() {
        return texTotalViviendas;
    }

    public void setTexTotalViviendas(JTextField texTotalViviendas) {
        this.texTotalViviendas = texTotalViviendas;
    }


}
