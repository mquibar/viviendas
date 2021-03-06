/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IUFuenteFondo.java
 *
 * Created on 02/12/2010, 18:04:43
 */

package viviendas.gui.FuentesFondos;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Maximiliano.
 */
public class IUFuenteFondo extends javax.swing.JInternalFrame {

    /** Creates new form IUFuenteFondo */
    public IUFuenteFondo() {
        initComponents();
    }

    public JButton getBtnAceptar(){
        return btnAceptar;
    }

    public JButton getBtnCancelar(){
        return btnCancelar;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnQuitar() {
        return btnQuitar;
    }

    public JTable getTbFuentesFondos() {
        return tbFuentesFondos;
    }

    public JTextField getTxtPorcentajeTotal() {
        return txtPorcentajeTotal;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFuentesFondos = new viviendas.gui.tool.TableUpdated();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPorcentajeTotal = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Fuentes de Fondos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/Home.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jScrollPane1.setViewportView(tbFuentesFondos);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/+.png"))); // NOI18N
        btnAgregar.setText("Agregar");

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/-.png"))); // NOI18N
        btnQuitar.setText("Quitar");

        jLabel1.setText("Total");

        txtPorcentajeTotal.setEditable(false);
        txtPorcentajeTotal.setFont(new java.awt.Font("Tahoma", 1, 11));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnQuitar)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPorcentajeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPorcentajeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnAgregar)
                    .addComponent(btnQuitar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/Delete.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/Save.png"))); // NOI18N
        btnAceptar.setText("Guardar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbFuentesFondos;
    private javax.swing.JTextField txtPorcentajeTotal;
    // End of variables declaration//GEN-END:variables

}
