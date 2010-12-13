/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import viviendas.entidades.vivienda.AñoPlan;
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
        _tablaAños=new ModelTableAño(null);
        _distProvincial=new ModelTableDistribucionProvincial(null);
        _distCiudad = new ModelTableDistribucionCiudad(null);
        _distSEconomico = new ModelTableDistribucionSectorEconomico(null);
        _distOperatoria = new ModelTableDistribucionOperatoria(null);

        cargarPantalla();
        _pantalla.setVisible(true);
    }

    final void cargarPantalla(){
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
        _pantalla.getScrProvincia().setVisible(false);
        _pantalla.getTblCiudad().setModel(_distCiudad);
        _pantalla.getScrCiudad().setVisible(false);
        _pantalla.getTblSectorEconomico().setModel(_distSEconomico);
        _pantalla.getScrSectEconom().setVisible(false);
        _pantalla.getTblOperatoria().setModel(_distOperatoria);
        _pantalla.getScrOperatoria().setVisible(false);

    }

    void pressOkButton(){

    }

    void pressCancelButton(){
        _pantalla.dispose();
    }

    void viewProvincia(){
        _distProvincial.setList(_gestor.listarDistribucionProvincial(getAñoSeleccionado()));//LLAMAR AL GESTOR PARA QUE ME RECUPERE EL LISTADO
        _pantalla.getTblAños().setEnabled(false);
        _pantalla.getTblProvincia().setVisible(true);
    }

    void viewCiudad(){
        _distCiudad.setList(_gestor.listarDistribucionCiudad(getAñoSeleccionado(), _distProvincial.getSelectedIndex(_pantalla.getTblProvincia().getSelectedRow())));//LLAMAR ACA TB AL GESTOR
        _pantalla.getTblProvincia().setEnabled(false);
        _pantalla.getTblCiudad().setVisible(true);
    }

    void viewSectEconomico(){
        _distSEconomico.setList(_gestor.listarDistribucionSector(getAñoSeleccionado(), _distCiudad.getSelectedIndex(_pantalla.getTblCiudad().getSelectedRow())));//GESTOR
        _pantalla.getTblCiudad().setEnabled(false);
        _pantalla.getTblSectorEconomico().setVisible(true);
    }

    void viewOperatoria(){
        _distOperatoria.setList(_gestor.listarDistribucionOperatoria(getAñoSeleccionado(), _distSEconomico.getSelectedIndex(_pantalla.getTblSectorEconomico().getSelectedRow())));//GESTOR
        _pantalla.getTblSectorEconomico().setEnabled(false);
        _pantalla.getTblOperatoria().setVisible(true);
    }

    private AñoPlan getAñoSeleccionado(){
        return _tablaAños.getSelectedIndex(_pantalla.getTblAños().getSelectedRow());
    }
}
