/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IUPanelCrearFinanciacion.java
 *
 * Created on 27/12/2010, 10:48:09
 */

package viviendas.gui.financiacion.crear;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author batte
 */
public class IUPanelCrearFinanciacion extends javax.swing.JPanel {

    /** Creates new form IUPanelCrearFinanciacion */
    public IUPanelCrearFinanciacion() {
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

        labPorcentaje = new javax.swing.JLabel();
        _spinPorcentaje = new javax.swing.JSpinner();
        _btnCrearFinanciacion = new javax.swing.JButton();
        _tabPaneFinanciacion = new javax.swing.JTabbedPane();
        labNombre = new javax.swing.JLabel();
        _texNombre = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        labPorcentaje.setText("Porcentaje: ");
        labPorcentaje.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        add(labPorcentaje, gridBagConstraints);

        _spinPorcentaje.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 15);
        add(_spinPorcentaje, gridBagConstraints);

        _btnCrearFinanciacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/Accept.png"))); // NOI18N
        _btnCrearFinanciacion.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 15);
        add(_btnCrearFinanciacion, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        add(_tabPaneFinanciacion, gridBagConstraints);

        labNombre.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
        add(labNombre, gridBagConstraints);

        _texNombre.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 10, 15);
        add(_texNombre, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _btnCrearFinanciacion;
    private javax.swing.JSpinner _spinPorcentaje;
    private javax.swing.JTabbedPane _tabPaneFinanciacion;
    private javax.swing.JTextField _texNombre;
    private javax.swing.JLabel labNombre;
    private javax.swing.JLabel labPorcentaje;
    // End of variables declaration//GEN-END:variables



    public JButton getBtnCrearFinanciacion() {
        return _btnCrearFinanciacion;
    }

    public void setBtnCrearFinanciacion(JButton _btnCrearFinanciacion) {
        this._btnCrearFinanciacion = _btnCrearFinanciacion;
    }


    public JTabbedPane getTabPaneFinanciacion() {
        return _tabPaneFinanciacion;
    }

    public void setTabPaneFinanciacion(JTabbedPane _tabPaneFinanciacion) {
        this._tabPaneFinanciacion = _tabPaneFinanciacion;
    }

    public JTextField getTexNombre() {
        return _texNombre;
    }

    public void setTexNombre(JTextField _texNombre) {
        this._texNombre = _texNombre;
    }

    public JSpinner getSpinPorcentaje() {
        return _spinPorcentaje;
    }

    public void setSpinPorcentaje(JSpinner _spinPorcentaje) {
        this._spinPorcentaje = _spinPorcentaje;
    }

    public JLabel getLabPorcentaje() {
        return labPorcentaje;
    }

    public void setLabPorcentaje(JLabel labPorcentaje) {
        this.labPorcentaje = labPorcentaje;
    }
}