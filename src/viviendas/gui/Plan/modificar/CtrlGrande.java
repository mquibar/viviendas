/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.Plan.modificar;

import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;

/**
 *
 * @author desarrollo
 */
public class CtrlGrande {

    private IUModificarPlanNew _pantalla;
    private ctrlModificarPlanNew _controlModificar;
    private GestorModificarPlan _gestor;

    public CtrlGrande(GestorModificarPlan gestor, JDesktopPane panel) {
        _gestor = gestor;
        _pantalla = new IUModificarPlanNew();
        panel.add(_pantalla);
        _controlModificar = new ctrlModificarPlanNew(gestor, _pantalla);
        try {
            _pantalla.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(CtrlGrande.class.getName()).log(Level.SEVERE, null, ex);
        }
        _pantalla.setVisible(true);
        _pantalla.toFront();
    }
}
