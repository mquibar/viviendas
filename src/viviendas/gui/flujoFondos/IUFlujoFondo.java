/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IUFlujoFondo.java
 *
 * Created on 16-mar-2011, 12:22:35
 */

package viviendas.gui.flujoFondos;

import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author Maximiliano.
 */
public class IUFlujoFondo extends javax.swing.JInternalFrame {

    /** Creates new form IUFlujoFondo */
    public IUFlujoFondo() {
        initComponents();
    }

    public JTable getTbFlujoFondo() {
        return tbFlujoFondo;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbFlujoFondo = new viviendas.gui.tool.TableUpdated();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setTitle("Flujo de Fondo");

        jScrollPane1.setViewportView(tbFlujoFondo);

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/Printer InkJet.png"))); // NOI18N
        btnImprimir.setText("Imprimir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnImprimir)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbFlujoFondo;
    // End of variables declaration//GEN-END:variables

}
