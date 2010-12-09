/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import viviendas.gui.models.tables.ModelTablePlan;

/**
 *
 * @author desarrollo
 */
public class ctrlAbrirPlan {
    private IUAbrirPlan _pantalla;
    private ModelTablePlan _tablaPlan;

    public ctrlAbrirPlan() {
        _pantalla = new IUAbrirPlan();
        cargarPantalla();
    }

    private void cargarPantalla(){
        _tablaPlan = new ModelTablePlan(null);// REALIZO LA LLAMADA PARA LISTAR LOS PLANES
        _pantalla.getTblPlane().setModel(_tablaPlan);

        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _pantalla.dispose();
            }
        });
        _pantalla.getBtnOpen().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressOkButton();
            }
        });
    }

    void pressOkButton(){
        int idx=_pantalla.getTblPlane().getSelectedRow();
        if(idx<0)
            return;
        _tablaPlan.getSelectedIndex(idx);
        /**
         * ACA REALIZO LA LLAMADA AL NUEVO CONTROL PARA REALIZAR LAS MODIFICACIONES NECESARIAS.
         */
    }
}
