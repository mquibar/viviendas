package viviendas.gui.sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import viviendas.gui.FuentesFondos.CtrlFuenteFondo;
import viviendas.gui.Operatoria.CtrlOperatoria;
import viviendas.gui.Plan.crear.CtrlPlan;
import viviendas.gui.Plan.modificar.ctrlAbrirPlan;
import viviendas.gui.SegmentoEconomico.CtrlSegmentoEconomico;
import viviendas.gui.TipoCiudad.CtrlTipoCiudad;
import viviendas.gui.provincia.CtrlProvincia;

public class CtrlPrincipal {

    private IUPantallaPrincipal _pantalla;
    private static final String _VERSION = "Viviendas 1.0";
    private static CtrlPrincipal instance;

    private CtrlPrincipal() {
        _pantalla = new IUPantallaPrincipal();
        _pantalla.setLocationRelativeTo(null);
        _pantalla.setExtendedState(JFrame.MAXIMIZED_BOTH);
        _pantalla.getMnuTiposCiudades().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                abrirIUTiposCiudades();
            }
        });
        _pantalla.getMnuFuenteFondos().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                abrirIUFuentesFondos();
            }
        });
        _pantalla.getMnuOperatoria().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                abrirIUOperatoria();
            }
        });
        _pantalla.getMnuSegmentoEconomico().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                abrirIUSegmentoEconomico();
            }
        });
        _pantalla.getMnuAcercaDe().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(_pantalla, _VERSION, "Acerca de", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        _pantalla.getMnuSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        _pantalla.getMnuNuevoPlan().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                abrirIUPlan();
            }
        });
        _pantalla.getMnuAbrirPlan().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                abrirIUAbrirPlan();
            }
        });
        _pantalla.getMnuProvincias().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                abrirIUAbrirProvincias();
            }
        });

    }

    public static CtrlPrincipal getInstance() {
        if (instance == null) {
            instance = new CtrlPrincipal();
        }
        return instance;
    }

    public void abrirPantallaPrincipal() {
        _pantalla.setVisible(true);
    }

    private void abrirIUTiposCiudades() {
        new CtrlTipoCiudad(_pantalla.getDesktopPane());
    }

    private void abrirIUSegmentoEconomico() {
        new CtrlSegmentoEconomico(_pantalla.getDesktopPane());
    }

    private void abrirIUOperatoria() {
        new CtrlOperatoria(_pantalla.getDesktopPane());
    }

    private void abrirIUFuentesFondos() {
        new CtrlFuenteFondo(_pantalla.getDesktopPane());
    }

    private void abrirIUPlan() {
        new CtrlPlan(_pantalla.getDesktopPane());
    }

    private void abrirIUAbrirPlan() {
        new ctrlAbrirPlan(_pantalla.getDesktopPane());
    }

    private void abrirIUAbrirProvincias() {
        new CtrlProvincia(_pantalla.getDesktopPane());
    }

    private void abrirReporteDistribGenProvincias() {
    }

    public JDesktopPane getDesktopPane() {
        return _pantalla.getDesktopPane();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CtrlPrincipal.getInstance().iniciar();
    }

    private void iniciar() {
        new CtrlUsuario(this);
    }
}
