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
 * @author Administrador
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

    public JMenuItem getMnuAbrirPlan() {
        return mnuAbrirPlan;
    }

    public JMenuItem getMnuAcercaDe() {
        return mnuAcercaDe;
    }

    public JMenuItem getMnuFuenteFondos() {
        return mnuFuenteFondos;
    }

    public JMenuItem getMnuNuevoPlan() {
        return mnuNuevoPlan;
    }

    public JMenuItem getMnuOperatoria() {
        return mnuOperatoria;
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
        mnuFuenteFondos = new javax.swing.JMenuItem();
        mnuOperatoria = new javax.swing.JMenuItem();
        mnuConfiguracion = new javax.swing.JMenu();
        mnuSistema = new javax.swing.JMenu();
        mnuAcercaDe = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Viviendas 1.0");
        setResizable(false);

        desktopPane.setBackground(new java.awt.Color(102, 153, 255));

        mnuPlanes.setText("Planes");

        mnuNuevoPlan.setText("Nuevo");
        mnuPlanes.add(mnuNuevoPlan);

        mnuAbrirPlan.setText("Abrir");
        mnuPlanes.add(mnuAbrirPlan);

        mnuReportes.setText("Reportes");
        mnuPlanes.add(mnuReportes);

        mnuBarSistema.add(mnuPlanes);

        mnuParametros.setText("Parámetros");

        mnuSegmentoEconomico.setText("Segmento Económico");
        mnuParametros.add(mnuSegmentoEconomico);

        mnuTiposCiudades.setText("Tipos Ciudadades");
        mnuParametros.add(mnuTiposCiudades);

        mnuFuenteFondos.setText("Fuentes Fondos");
        mnuParametros.add(mnuFuenteFondos);

        mnuOperatoria.setText("Operatoria");
        mnuParametros.add(mnuOperatoria);

        mnuBarSistema.add(mnuParametros);

        mnuConfiguracion.setText("Configuración");
        mnuBarSistema.add(mnuConfiguracion);

        mnuSistema.setText("Sistema");

        mnuAcercaDe.setText("Acerca de");
        mnuSistema.add(mnuAcercaDe);

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

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IUPantallaPrincipal().setVisible(true);
            }
        });
    }

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
    private javax.swing.JMenu mnuPlanes;
    private javax.swing.JMenuItem mnuReportes;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenuItem mnuSegmentoEconomico;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenuItem mnuTiposCiudades;
    // End of variables declaration//GEN-END:variables

}
