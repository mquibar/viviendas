/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.SegmentoEconomico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import viviendas.gui.models.tables.ModeloTablaSectorEconomico;
import viviendas.modulos.SegmentoEconomico.GestorSectorEconomico;

/**
 *
 * @author Admin
 */
public class CtrlSegmentoEconomico {
    private IUSegmentoEconomico _pantalla;
    private GestorSectorEconomico _gestor;
    private ModeloTablaSectorEconomico _modelo;

    public CtrlSegmentoEconomico(JDesktopPane desktop) {
        _gestor = new GestorSectorEconomico();
        _pantalla = new IUSegmentoEconomico();
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
        _modelo = new ModeloTablaSectorEconomico(_gestor.obtenerSectoresEconomicos());
        _pantalla.getTbSegmentoEconomico().setModel(_modelo);
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
