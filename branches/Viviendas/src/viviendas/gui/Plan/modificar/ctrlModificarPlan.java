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
public class ctrlModificarPlan {
    private IUModificarPlan _pantalla;
    private ModelTablePlan _tablaPlan;

    public ctrlModificarPlan() {
        _pantalla = new IUModificarPlan();
        cargarPantalla();
    }

    void cargarPantalla(){
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
        
    }
}
