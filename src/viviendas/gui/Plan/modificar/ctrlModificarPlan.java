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
import viviendas.gui.models.tables.ModelTableDistribucionOperatoria;
import viviendas.gui.models.tables.ModelTableDistribucionProvincial;
import viviendas.gui.models.tables.ModelTableDistribucionSectorEconomico;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;

/**
 *
 * @author desarrollo
 */
public class ctrlModificarPlan {

    private IUModificarPlan _pantalla;
    private ModelTableAño _tablaAños;
    private ModelTableDistribucionProvincial _distProvincial;
    private ModelTableDistribucionCiudad _distCiudad;
    private ModelTableDistribucionSectorEconomico _distSEconomico;
    private ModelTableDistribucionOperatoria _distOperatoria;
    private GestorModificarPlan _gestor;


    public ctrlModificarPlan(GestorModificarPlan gestor) {
        _gestor = gestor;
        _pantalla = new IUModificarPlan();

        cargarPantalla();
    }

    void cargarPantalla(){
        Plan plan = _gestor.getPlan();
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
        _pantalla.getTblSectorEconomico().setModel(_distSEconomico);
        _pantalla.getTblOperatoria().setModel(_distOperatoria);

    }

    void pressOkButton(){

    }

    void pressCancelButton(){
        _pantalla.dispose();
    }

    void viewProvincia(){
        _distProvincial.setList(null);//LLAMAR AL GESTOR PARA QUE ME RECUPERE EL LISTADO
    }

    void viewCiudad(){
        _distCiudad.setList(null);//LLAMAR ACA TB AL GESTOR
    }

    void viewSectEconomico(){
        _distSEconomico.setList(null);//GESTOR
    }

    void viewOperatoria(){
        _distOperatoria.setList(null);//GESTOR
    }
}
