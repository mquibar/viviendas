/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import viviendas.entidades.vivienda.Plan;
import viviendas.gui.models.tables.ModelTableAño;
import viviendas.gui.models.tables.ModelTableDistribucionCiudad;
import viviendas.gui.models.tables.ModelTableDistribucionProvincial;

/**
 *
 * @author desarrollo
 */
public class ctrlModificarPlan {

    private IUModificarPlan _pantalla;
    private ModelTableAño _tablaAños;
    private ModelTableDistribucionProvincial _distProvincial;
    private ModelTableDistribucionCiudad _distCiudad;


    public ctrlModificarPlan(Plan plan) {
        _pantalla = new IUModificarPlan();
    }

    void cargarPantalla(Plan plan){
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressOkButton();
            }
        });
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressCancelButton();
            }
        });
        _pantalla.getTxtNombre().setText(plan.getNombre());
        _pantalla.getTxtTipo().setText(plan.getTipoPlan().getNombre());
        _pantalla.getTxtTotViviendas().setText(plan.getNumeroViviendas().toString());
        _pantalla.getTblAños().setModel(_tablaAños);
        _pantalla.getTblProvincia().setModel(_distProvincial);
        _pantalla.getTblCiudad().setModel(_distCiudad);

    }

    void pressOkButton(){

    }

    void pressCancelButton(){
        _pantalla.dispose();
    }
}
