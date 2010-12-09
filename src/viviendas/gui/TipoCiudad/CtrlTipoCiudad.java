/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.TipoCiudad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import viviendas.gui.models.tables.ModeloTableCiudad;
import viviendas.modulos.tipoCiudad.GestorTipoCiudad;

/**
 *
 * @author Maximiliano.
 */
public class CtrlTipoCiudad {
    private IUTipoCiudad _pantalla;
    private GestorTipoCiudad _gestor;
    private ModeloTableCiudad _modelo;


    public CtrlTipoCiudad(JDesktopPane desktop) {
        _gestor = new GestorTipoCiudad();
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
        _modelo = new ModeloTableCiudad(_gestor.obtenerTiposCiudades());
        _pantalla.getTbCiudad().setModel(_modelo);
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
