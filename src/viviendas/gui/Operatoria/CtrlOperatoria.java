/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Operatoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import viviendas.modulos.Operatoria.GestorOperatoria;

/**
 *
 * @author Admin
 */
public class CtrlOperatoria {
    private IUOperatoria _pantalla;
    private GestorOperatoria _gestor;

    public CtrlOperatoria(JDesktopPane desktop) {
        _pantalla = new IUOperatoria();
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
        _pantalla.getTbOperatoria().setModel(new ModeloOperatoria());
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
