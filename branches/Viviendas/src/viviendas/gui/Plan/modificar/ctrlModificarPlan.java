/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan.modificar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JDesktopPane;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.entidades.vivienda.Plan;
import viviendas.gui.tool.ICalculable;
import viviendas.gui.models.tables.ModelTableAño;
import viviendas.gui.models.tables.ModelTableDistribucionCiudad;
import viviendas.gui.models.tables.ModelTableDistribucionOperatoria;
import viviendas.gui.models.tables.ModelTableDistribucionProvincial;
import viviendas.gui.models.tables.ModelTableDistribucionSectorEconomico;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;

/**
 *
 * @author desarrollo
 */
public class ctrlModificarPlan implements ICalculable {

    private IUModificarPlan _pantalla;
    private ModelTableAño _tablaAños;
    private ModelTableDistribucionProvincial _distProvincial;
    private ModelTableDistribucionCiudad _distCiudad;
    private ModelTableDistribucionSectorEconomico _distSEconomico;
    private ModelTableDistribucionOperatoria _distOperatoria;
    private GestorModificarPlan _gestor;
    private final int AÑO=0;
    private final int PROVINCIA = 1;
    private final int CIUDAD = 2;
    private final int SECTORECONOMICO = 3;
    private final int OPERATORIA = 4;
    private int tablaOnTop=AÑO;

    public ctrlModificarPlan(GestorModificarPlan gestor,JDesktopPane panel) {
        _gestor = gestor;
        _pantalla = new IUModificarPlan();
        _tablaAños=new ModelTableAño(null);
        _distProvincial=new ModelTableDistribucionProvincial(null);
        _distCiudad = new ModelTableDistribucionCiudad(null);
        _distSEconomico = new ModelTableDistribucionSectorEconomico(null);
        _distOperatoria = new ModelTableDistribucionOperatoria(null);

        cargarPantalla();
        panel.add(_pantalla);
        _pantalla.setVisible(true);
        SubscriptorTotal.getInstance().añadir(this);
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
        _tablaAños.setList(_gestor.getPlan().getListaAñoPlan());
        _pantalla.getTblAños().setModel(_tablaAños);
        _pantalla.getTblProvincia().setModel(_distProvincial);
        _pantalla.getScrProvincia().setVisible(false);
        _pantalla.getTblCiudad().setModel(_distCiudad);
        _pantalla.getScrCiudad().setVisible(false);
        _pantalla.getTblSectorEconomico().setModel(_distSEconomico);
        _pantalla.getScrSectEconom().setVisible(false);
        _pantalla.getTblOperatoria().setModel(_distOperatoria);
        _pantalla.getScrOperatoria().setVisible(false);

        _pantalla.getBtnViewDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                verTablas();
            }
        });

        _pantalla.getBtnDropDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ocultarTablas();
            }
        });

    }

    void pressOkButton(){

    }

    void pressCancelButton(){
        _pantalla.dispose();
        SubscriptorTotal.getInstance().remove(this);
    }

    void verTablas(){
        tablaOnTop++;
        switch(tablaOnTop){
            case AÑO:
                _pantalla.getBtnDropDetails().setEnabled(false);
                break;
            case PROVINCIA:
                _pantalla.getBtnDropDetails().setEnabled(true);
                viewProvincia();
                break;
            case CIUDAD:
                viewCiudad();
                break;
            case SECTORECONOMICO:
                viewSectEconomico();
                break;
            case OPERATORIA:
                viewOperatoria();
                _pantalla.getBtnViewDetails().setEnabled(false);
                break;
            default:
                tablaOnTop--;
        }
        actualizarPorcentaje();
    }

    void ocultarTablas(){
        
        switch(tablaOnTop){
            case AÑO:
                _pantalla.getBtnDropDetails().setEnabled(false);
                break;
            case PROVINCIA:
                dropProvincia();
                _pantalla.getBtnDropDetails().setEnabled(false);
                break;
            case CIUDAD:
                dropCiudad();
                break;
            case SECTORECONOMICO:
                dropSectEconomico();
                break;
            case OPERATORIA:
                dropOperatoria();
                _pantalla.getBtnViewDetails().setEnabled(true);
                break;
            default:
                tablaOnTop++;
        }
        tablaOnTop--;
        actualizarPorcentaje();
    }
    void viewProvincia(){
        _distProvincial.setList(_gestor.listarDistribucionProvincial(getAñoSeleccionado()));//LLAMAR AL GESTOR PARA QUE ME RECUPERE EL LISTADO
        _pantalla.getTblAños().setEnabled(false);
        _pantalla.getScrProvincia().setVisible(true);
    }

    void viewCiudad(){
        _distCiudad.setList(_gestor.listarDistribucionCiudad(getAñoSeleccionado(), _distProvincial.getSelectedIndex(_pantalla.getTblProvincia().getSelectedRow())));//LLAMAR ACA TB AL GESTOR
        _pantalla.getTblProvincia().setEnabled(false);
        _pantalla.getScrCiudad().setVisible(true);
    }

    void viewSectEconomico(){
        _distSEconomico.setList(_gestor.listarDistribucionSector(getAñoSeleccionado(), _distCiudad.getSelectedIndex(_pantalla.getTblCiudad().getSelectedRow())));//GESTOR
        _pantalla.getTblCiudad().setEnabled(false);
        _pantalla.getScrSectEconom().setVisible(true);
    }

    void viewOperatoria(){
        _distOperatoria.setList(_gestor.listarDistribucionOperatoria(getAñoSeleccionado(), _distSEconomico.getSelectedIndex(_pantalla.getTblSectorEconomico().getSelectedRow())));//GESTOR
        _pantalla.getTblSectorEconomico().setEnabled(false);
        _pantalla.getScrOperatoria().setVisible(true);
    }

    void dropOperatoria(){
        _pantalla.getTblSectorEconomico().setEnabled(true);
        _pantalla.getScrOperatoria().setVisible(false);
    }
    void dropSectEconomico(){
        _pantalla.getTblCiudad().setEnabled(true);
        _pantalla.getScrSectEconom().setVisible(false);
    }
    void dropCiudad(){
        _pantalla.getTblProvincia().setEnabled(true);
        _pantalla.getScrCiudad().setVisible(false);
    }
    void dropProvincia(){
        _pantalla.getTblAños().setEnabled(true);
        _pantalla.getScrProvincia().setVisible(false);
    }

    private AñoPlan getAñoSeleccionado(){
        int idx = _pantalla.getTblAños().getSelectedRow();
        if(idx<0) idx=0;
        return _tablaAños.getSelectedIndex(idx);
    }

    public void actualizarPorcentaje() {
        switch(tablaOnTop){
            case PROVINCIA:
                porcentajeProvincia();
                break;
            case CIUDAD:
                porcentajeCiudad();
                break;
            case SECTORECONOMICO:
                porcentajeSEconomico();
                break;
            case OPERATORIA:
                porcentajeOperatoria();
                break;
        }
    }

    private void colorTotal(double porcentaje){
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        _pantalla.getTxtTotal().setText(nf.format(porcentaje));
        if(porcentaje!=100.0){
            _pantalla.getTxtTotal().setForeground(Color.red);
        }
        else
            _pantalla.getTxtTotal().setForeground(Color.BLUE);
    }

    private void porcentajeProvincia(){
        double porcentaje=0;
        for (DistribucionProvincial dProvincial : _distProvincial.getAllRow()) {
            porcentaje+=dProvincial.getPorcentajeDistribucion();
        }
        colorTotal(porcentaje);
    }

    private void porcentajeCiudad(){
        double porcentaje = 0;
        for (DistribucionCiudad dCiudad : _distCiudad.getAllRow()) {
            porcentaje+= dCiudad.getPorcentajeDistribucion();
        }
        colorTotal(porcentaje);
    }

    private void porcentajeSEconomico(){
        double porcentaje = 0;
        for (DistribucionSector dSector : _distSEconomico.getAllRow()) {
            porcentaje+=dSector.getPorcentajeDistribucion();
        }
        colorTotal(porcentaje);
    }
    private void porcentajeOperatoria(){
        double porcentaje = 0;
        for (DistribucionOperatoria dOperatoria : _distOperatoria.getAllRow()) {
            porcentaje+=dOperatoria.getPorcentajeDistribucion();
        }
        colorTotal(porcentaje);
    }
}
