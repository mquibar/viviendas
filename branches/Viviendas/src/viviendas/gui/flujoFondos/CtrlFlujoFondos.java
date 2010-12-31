/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.flujoFondos;

import javax.swing.JDesktopPane;
import viviendas.modulos.flujoFondos.GestorFlujoFondos;

/**
 *
 * @author Maximiliano.
 */
public class CtrlFlujoFondos {
    private IUFlujoFondos _pantalla;
    private GestorFlujoFondos _gestor;

    public CtrlFlujoFondos(JDesktopPane desktop) {
        _gestor = new GestorFlujoFondos();
        //_pantalla: internalFrame
        _pantalla = new IUFlujoFondos();

        
        desktop.add(_pantalla);
        _pantalla.setVisible(true);
    }

}