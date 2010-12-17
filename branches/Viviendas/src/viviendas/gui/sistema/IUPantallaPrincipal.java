/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IUPantallaPrincipal.java
 *
 * Created on 30-nov-2010, 13:38:05
 */

package viviendas.gui.sistema;

import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;

/**
 *
 * @author Maximiliano.
 */
public class IUPantallaPrincipal extends javax.swing.JFrame {

    /** Creates new form IUPantallaPrincipal */
    public IUPantallaPrincipal() {
        initComponents();
    }

    public JDesktopPane getDesktopPane(){
        return desktopPane;
    }

    public JMenuItem getMnuTiposCiudades(){
        return mnuTiposCiudades;
    }

    public JMenuItem getMnuFuenteFondos(){
        return mnuFuenteFondos;
    }

    public JMenuItem getMnuReporteDistribGenProvincias() {
        return mnuReporteDistribGenProvincias;
    }

    public JMenuItem getMnuUsosFondos() {
        return mnuUsosFondos;
    }

    public JMenuItem getMnuAbrirPlan() {
        return mnuAbrirPlan;
    }

    public JMenuItem getMnuAcercaDe() {
        return mnuAcercaDe;
    }    

    public JMenuItem getMnuNuevoPlan() {
        return mnuNuevoPlan;
    }

    public JMenuItem getMnuOperatoria() {
        return mnuOperatoria;
    }

    public JMenuItem getMnuProvincias(){
        return mnuProvincias;
    }

    public JMenuItem getMnuReportes() {
        return mnuReportes;
    }

    public JMenuItem getMnuSalir() {
        return mnuSalir;
    }

    public JMenuItem getMnuSegmentoEconomico() {
        return mnuSegmentoEconomico;
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        mnuBarSistema = new javax.swing.JMenuBar();
        mnuPlanes = new javax.swing.JMenu();
        mnuNuevoPlan = new javax.swing.JMenuItem();
        mnuAbrirPlan = new javax.swing.JMenuItem();
        mnuReportes = new javax.swing.JMenuItem();
        mnuParametros = new javax.swing.JMenu();
        mnuSegmentoEconomico = new javax.swing.JMenuItem();
        mnuTiposCiudades = new javax.swing.JMenuItem();
        mnuOperatoria = new javax.swing.JMenuItem();
        mnuProvincias = new javax.swing.JMenuItem();
        mnuFuenteFondos = new javax.swing.JMenuItem();
        mnuUsosFondos = new javax.swing.JMenuItem();
        mnuParametrosReportes = new javax.swing.JMenu();
        mnuReporteDistribGenProvincias = new javax.swing.JMenuItem();
        mnuConfiguracion = new javax.swing.JMenu();
        mnuSistema = new javax.swing.JMenu();
        mnuAcercaDe = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Viviendas 1.0");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        desktopPane.setBackground(new java.awt.Color(102, 153, 255));

        mnuPlanes.setText("Planes");

        mnuNuevoPlan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mnuNuevoPlan.setText("Nuevo");
        mnuPlanes.add(mnuNuevoPlan);

        mnuAbrirPlan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/Open_16x16.png"))); // NOI18N
        mnuAbrirPlan.setText("Abrir");
        mnuPlanes.add(mnuAbrirPlan);

        mnuReportes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mnuReportes.setText("Reportes");
        mnuPlanes.add(mnuReportes);

        mnuBarSistema.add(mnuPlanes);

        mnuParametros.setText("Parámetros");

        mnuSegmentoEconomico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnuSegmentoEconomico.setText("Segmento Económico");
        mnuParametros.add(mnuSegmentoEconomico);

        mnuTiposCiudades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        mnuTiposCiudades.setText("Tipos Ciudadades");
        mnuParametros.add(mnuTiposCiudades);

        mnuOperatoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnuOperatoria.setText("Operatorias");
        mnuParametros.add(mnuOperatoria);

        mnuProvincias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mnuProvincias.setText("Provincias");
        mnuParametros.add(mnuProvincias);

        mnuFuenteFondos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        mnuFuenteFondos.setText("Fuentes de Fondos");
        mnuParametros.add(mnuFuenteFondos);

        mnuUsosFondos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mnuUsosFondos.setText("Usos Fondos");
        mnuParametros.add(mnuUsosFondos);

        mnuParametrosReportes.setText("Reportes");

        mnuReporteDistribGenProvincias.setText("Distrib. General Provincia");
        mnuParametrosReportes.add(mnuReporteDistribGenProvincias);

        mnuParametros.add(mnuParametrosReportes);

        mnuBarSistema.add(mnuParametros);

        mnuConfiguracion.setText("Configuración");
        mnuBarSistema.add(mnuConfiguracion);

        mnuSistema.setText("Sistema");

        mnuAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/Alert.png"))); // NOI18N
        mnuAcercaDe.setText("Acerca de");
        mnuSistema.add(mnuAcercaDe);

        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viviendas/imagenes/Log Out_16x16.png"))); // NOI18N
        mnuSalir.setText("Salir");
        mnuSistema.add(mnuSalir);

        mnuBarSistema.add(mnuSistema);

        setJMenuBar(mnuBarSistema);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem mnuAbrirPlan;
    private javax.swing.JMenuItem mnuAcercaDe;
    private javax.swing.JMenuBar mnuBarSistema;
    private javax.swing.JMenu mnuConfiguracion;
    private javax.swing.JMenuItem mnuFuenteFondos;
    private javax.swing.JMenuItem mnuNuevoPlan;
    private javax.swing.JMenuItem mnuOperatoria;
    private javax.swing.JMenu mnuParametros;
    private javax.swing.JMenu mnuParametrosReportes;
    private javax.swing.JMenu mnuPlanes;
    private javax.swing.JMenuItem mnuProvincias;
    private javax.swing.JMenuItem mnuReporteDistribGenProvincias;
    private javax.swing.JMenuItem mnuReportes;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenuItem mnuSegmentoEconomico;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenuItem mnuTiposCiudades;
    private javax.swing.JMenuItem mnuUsosFondos;
    // End of variables declaration//GEN-END:variables

}
