/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.TipoCiudad;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import viviendas.modulos.tipoCiudad.GestorTipoCiudad;

/**
 *
 * @author Admin
 */
public class CtrlTipoCiudad {
    private IUTipoCiudad _pantalla;
    private GestorTipoCiudad _gestor;


    public CtrlTipoCiudad(JDesktopPane desktop) {
        _pantalla = new IUTipoCiudad();
        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrar();
            }
        });
        _pantalla.getBtnAceptar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar();
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
