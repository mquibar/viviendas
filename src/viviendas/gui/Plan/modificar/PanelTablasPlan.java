/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelTablasPlan.java
 *
 * Created on 23/12/2010, 11:29:33
 */

package viviendas.gui.Plan.modificar;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author desarrollo
 */
public class PanelTablasPlan extends javax.swing.JPanel {

    /** Creates new form PanelTablasPlan */
    public PanelTablasPlan() {
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

        _scrSectEconom = new javax.swing.JScrollPane();
        _tblSectorEconomico = new javax.swing.JTable();
        _scrCiudad = new javax.swing.JScrollPane();
        _tblCiudad = new javax.swing.JTable();
        _scrProvincia = new javax.swing.JScrollPane();
        _tblProvincia = new javax.swing.JTable();
        _scrAño = new javax.swing.JScrollPane();
        _tblAños = new javax.swing.JTable();
        _scrOperatoria = new javax.swing.JScrollPane();
        _tblOperatoria = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        _tblSectorEconomico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        _scrSectEconom.setViewportView(_tblSectorEconomico);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(_scrSectEconom, gridBagConstraints);

        _tblCiudad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        _scrCiudad.setViewportView(_tblCiudad);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 4.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(_scrCiudad, gridBagConstraints);

        _tblProvincia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        _scrProvincia.setViewportView(_tblProvincia);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 6.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(_scrProvincia, gridBagConstraints);

        _tblAños.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        _scrAño.setViewportView(_tblAños);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(_scrAño, gridBagConstraints);

        _tblOperatoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        _scrOperatoria.setViewportView(_tblOperatoria);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        add(_scrOperatoria, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane _scrAño;
    private javax.swing.JScrollPane _scrCiudad;
    private javax.swing.JScrollPane _scrOperatoria;
    private javax.swing.JScrollPane _scrProvincia;
    private javax.swing.JScrollPane _scrSectEconom;
    private javax.swing.JTable _tblAños;
    private javax.swing.JTable _tblCiudad;
    private javax.swing.JTable _tblOperatoria;
    private javax.swing.JTable _tblProvincia;
    private javax.swing.JTable _tblSectorEconomico;
    // End of variables declaration//GEN-END:variables

    public JScrollPane getScrAño() {
        return _scrAño;
    }

    public JScrollPane getScrCiudad() {
        return _scrCiudad;
    }

    public JScrollPane getScrOperatoria() {
        return _scrOperatoria;
    }

    public JScrollPane getScrProvincia() {
        return _scrProvincia;
    }

    public JScrollPane getScrSectEconom() {
        return _scrSectEconom;
    }

    public JTable getTblAños() {
        return _tblAños;
    }

    public JTable getTblCiudad() {
        return _tblCiudad;
    }

    public JTable getTblOperatoria() {
        return _tblOperatoria;
    }

    public JTable getTblProvincia() {
        return _tblProvincia;
    }

    public JTable getTblSectorEconomico() {
        return _tblSectorEconomico;
    }

}
