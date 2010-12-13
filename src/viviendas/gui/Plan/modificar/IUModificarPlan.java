/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IUModificarPlan.java
 *
 * Created on 09/12/2010, 10:48:38
 */

package viviendas.gui.Plan.modificar;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author desarrollo
 */
public class IUModificarPlan extends javax.swing.JInternalFrame {

    /** Creates new form IUModificarPlan */
    public IUModificarPlan() {
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
        jLabel1 = new javax.swing.JLabel();
        _txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        _txtTipo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        _spnAños = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        _txtTotViviendas = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        _scrOperatoria = new javax.swing.JScrollPane();
        _tblOperatoria = new javax.swing.JTable();
        _scrSectEconom = new javax.swing.JScrollPane();
        _tblSectorEconomico = new javax.swing.JTable();
        _scrCiudad = new javax.swing.JScrollPane();
        _tblCiudad = new javax.swing.JTable();
        _scrProvincia = new javax.swing.JScrollPane();
        _tblProvincia = new javax.swing.JTable();
        _scrAño = new javax.swing.JScrollPane();
        _tblAños = new javax.swing.JTable();
        _btnOk = new javax.swing.JButton();
        _btnCancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Plan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), java.awt.Color.gray)); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(_txtNombre, gridBagConstraints);

        jLabel2.setText("Tipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(_txtTipo, gridBagConstraints);

        jLabel3.setText("Años");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(_spnAños, gridBagConstraints);

        jLabel4.setText("Total Viviendas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel4, gridBagConstraints);

        _txtTotViviendas.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(_txtTotViviendas, gridBagConstraints);

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

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

        _scrOperatoria.setBounds(120, 0, 260, 440);
        jLayeredPane1.add(_scrOperatoria, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        _scrSectEconom.setBounds(90, 0, 260, 450);
        jLayeredPane1.add(_scrSectEconom, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        _scrCiudad.setBounds(60, 0, 260, 460);
        jLayeredPane1.add(_scrCiudad, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        _scrProvincia.setBounds(30, 0, 260, 470);
        jLayeredPane1.add(_scrProvincia, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        _scrAño.setBounds(0, 0, 210, 480);
        jLayeredPane1.add(_scrAño, javax.swing.JLayeredPane.DEFAULT_LAYER);

        _btnOk.setText("Guardar");

        _btnCancel.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_btnOk)
                    .addComponent(_btnCancel))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(_btnOk)
                        .addGap(52, 52, 52)
                        .addComponent(_btnCancel)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton _btnCancel;
    private javax.swing.JButton _btnOk;
    private javax.swing.JScrollPane _scrAño;
    private javax.swing.JScrollPane _scrCiudad;
    private javax.swing.JScrollPane _scrOperatoria;
    private javax.swing.JScrollPane _scrProvincia;
    private javax.swing.JScrollPane _scrSectEconom;
    private javax.swing.JSpinner _spnAños;
    private javax.swing.JTable _tblAños;
    private javax.swing.JTable _tblCiudad;
    private javax.swing.JTable _tblOperatoria;
    private javax.swing.JTable _tblProvincia;
    private javax.swing.JTable _tblSectorEconomico;
    private javax.swing.JTextField _txtNombre;
    private javax.swing.JTextField _txtTipo;
    private javax.swing.JTextField _txtTotViviendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnCancel() {
        return _btnCancel;
    }

    public JButton getBtnOk() {
        return _btnOk;
    }

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

    public JSpinner getSpnAños() {
        return _spnAños;
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

    public JTextField getTxtNombre() {
        return _txtNombre;
    }

    public JTextField getTxtTipo() {
        return _txtTipo;
    }

    public JTextField getTxtTotViviendas() {
        return _txtTotViviendas;
    }

}