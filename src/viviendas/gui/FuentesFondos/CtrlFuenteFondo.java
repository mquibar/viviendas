/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.FuentesFondos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import viviendas.modulos.FuentesFondos.GestorFuentesFondos;

/**
 *
 * @author Maximiliano.
 */
public class CtrlFuenteFondo {
    private IUFuenteFondo _pantalla;
    private GestorFuentesFondos _gestor;

    public CtrlFuenteFondo(JDesktopPane desktop) {
        _pantalla = new IUFuenteFondo();
        _pantalla.getBtnAceptar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cerrar();
            }
        });
        _pantalla.setVisible(true);
        desktop.add(_pantalla);
    }

    private void cerrar(){
        _pantalla.hide();
        _pantalla.dispose();
    }

    private void guardar(){
        _gestor.guardar();
    }

}
