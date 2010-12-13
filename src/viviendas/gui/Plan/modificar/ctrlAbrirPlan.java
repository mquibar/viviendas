/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
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
    private JDesktopPane _panel;

    public ctrlAbrirPlan(JDesktopPane panel) {
        _pantalla = new IUAbrirPlan();
        _gestor = new GestorModificarPlan();
        cargarPantalla();
        panel.add(_pantalla);
        _pantalla.setVisible(true);
        _panel=panel;
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
        new ctrlModificarPlan(_gestor,_panel);
        _pantalla.dispose();
        /**
         * ACA REALIZO LA LLAMADA AL NUEVO CONTROL PARA REALIZAR LAS MODIFICACIONES NECESARIAS.
         */
    }
}
