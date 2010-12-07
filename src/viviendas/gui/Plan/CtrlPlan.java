/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan;

import javax.swing.JDesktopPane;
import viviendas.modulos.Plan.GestorPlan;

/**
 *
 * @author Admin
 */
public class CtrlPlan {
    private IUPlan _pantalla;
    private GestorPlan _gestor;

    public CtrlPlan(JDesktopPane desktop) {
        _pantalla = new IUPlan();
        //acciones...
        _pantalla.setVisible(true);
        desktop.add(_pantalla);
    }



}
