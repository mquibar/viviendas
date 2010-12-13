/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import viviendas.gui.models.tables.ModelTablePlan;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;

/**
 *
 * @author desarrollo
 */
public class ctrlAbrirPlan {
    private IUAbrirPlan _pantalla;
    private ModelTablePlan _tablaPlan;
    private GestorModificarPlan _gestor;

    public ctrlAbrirPlan() {
        _pantalla = new IUAbrirPlan();
        cargarPantalla();
        _gestor = new GestorModificarPlan();
    }

    private void cargarPantalla(){
        _tablaPlan = new ModelTablePlan(_gestor.listarPlanes());
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
        _gestor.cargarPlan(_tablaPlan.getSelectedIndex(idx));
        new ctrlModificarPlan(_gestor);
        /**
         * ACA REALIZO LA LLAMADA AL NUEVO CONTROL PARA REALIZAR LAS MODIFICACIONES NECESARIAS.
         */
    }
}
