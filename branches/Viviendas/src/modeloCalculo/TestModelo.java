/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TestModelo.java
 *
 * Created on 12/01/2011, 13:04:44
 */

package modeloCalculo;

/**
 *
 * @author desarrollo
 */
public class TestModelo extends javax.swing.JFrame {

    /** Creates new form TestModelo */
    public TestModelo() {
        initComponents();
        md = new ModelDinamicTable(capSolicitado, TNA, comicionOtorgamiento, gastosAdministrativos, momentoOtorgamiento, plazoGracia);
        md.setCantAños(2);
        jTable1.setModel(md);
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
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestModelo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    double capSolicitado=24000;
    double TNA=0.03;
    double comicionOtorgamiento=0.02;
    double gastosAdministrativos=0.01;
    int momentoOtorgamiento=0;
    int plazoGracia=1;
//    double capSolicitado=190000;
//    double TNA=0.06;
//    double comicionOtorgamiento=0.01;
//    double gastosAdministrativos=0.01;
//    int momentoOtorgamiento=0;
//    int plazoGracia=2;
    ModelDinamicTable md;
}
