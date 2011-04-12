/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UIViewerReport.java
 *
 * Created on 12/04/2011, 11:52:04
 */

package viviendas.gui.reports;

/**
 *
 * @author desarrollo
 */
public class UIViewerReport extends javax.swing.JInternalFrame {

    /** Creates new form UIViewerReport */
    private UIViewerReport() {
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

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 275, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private static UIViewerReport instance;
    public boolean windViewerOpen = false;


    public static UIViewerReport getInstance() {
        if(instance ==null)
            instance= new UIViewerReport();
        return instance;
    }

     private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
         windViewerOpen = false;
     }

    public boolean isWindViewerOpen() {
        return windViewerOpen;
    }

    public void setWindViewerOpen(boolean windViewerOpen) {
        this.windViewerOpen = windViewerOpen;
    }
    
}