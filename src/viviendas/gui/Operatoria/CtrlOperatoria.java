/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Operatoria;

import viviendas.gui.models.tables.ModeloTablaOperatoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import viviendas.modulos.Operatoria.GestorOperatoria;

/**
 *
 * @author Maximiliano.
 */
public class CtrlOperatoria {
    private IUOperatoria _pantalla;
    private GestorOperatoria _gestor;
    private ModeloTablaOperatoria _modelo;

    public CtrlOperatoria(JDesktopPane desktop) {
        _gestor = new GestorOperatoria();
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
        _pantalla.getBtnAgregar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });
        _pantalla.getBtnQuitar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                quitar();
            }
        });

        _modelo = new ModeloTablaOperatoria(_gestor.obtenerOperatorias());
        _pantalla.getTbOperatoria().setModel(_modelo);
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

    private void agregar(){        

    }

    private void quitar(){

    }


}
