/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import viviendas.gui.financiacion.crear.CtrlCrearFinanciacion;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;

/**
 *
 * @author desarrollo
 */
public class CtrlGrande {

    private IUModificarPlanNew _pantalla;
    private ctrlModificarPlanNew _controlModificar;
    private CtrlCrearFinanciacion _controlFinanciacion;
    private GestorModificarPlan _gestor;

    public CtrlGrande(GestorModificarPlan gestor) {
        _gestor = gestor;
        _pantalla = new IUModificarPlanNew();
        CtrlPrincipal.getInstance().getDesktopPane().add(_pantalla);
        _controlModificar = new ctrlModificarPlanNew(gestor, _pantalla);
        try {
            _pantalla.setMaximum(true);
        } catch (PropertyVetoException ex) {
            System.err.println(ex);
        }
        _pantalla.getBtnAddFinanciacion().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressAddFinanciacionButton();
            }
        });
        _pantalla.setVisible(true);
        _pantalla.toFront();
    }

    void pressAddFinanciacionButton(){
        _controlModificar.desactivar();
        if(_controlFinanciacion!=null)
            _controlFinanciacion = new CtrlCrearFinanciacion(_controlModificar.getDistOperatoriaSeleccionada(), null);
    }
}
