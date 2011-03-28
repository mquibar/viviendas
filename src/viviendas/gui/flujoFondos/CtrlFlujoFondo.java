/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.flujoFondos;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.gui.models.tables.ModelTableFlujoFondo;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author Maximiliano.
 */
public class CtrlFlujoFondo {
    private IUFlujoFondo _pantalla;    
    private ModelTableFlujoFondo _modelo;

    //Parte del controlador grande.
    public CtrlFlujoFondo(JDesktopPane desktop, Plan plan, DistribucionOperatoria distOp) {
        try {            
            _pantalla = new IUFlujoFondo();
            _modelo = new ModelTableFlujoFondo(plan, distOp);
            _pantalla.getTbFlujoFondo().setModel(_modelo);
            _pantalla.setVisible(true);
            desktop.add(_pantalla);
            _pantalla.toFront();
        } catch (BusinessOperationException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Viviendas", JOptionPane.ERROR_MESSAGE);
        }
    }
}
