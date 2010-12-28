/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import viviendas.entidades.vivienda.Plan;
import viviendas.gui.models.tables.ModelTableInversionPlan;
import viviendas.gui.models.tables.ModelTableValorInversion;
import viviendas.modulos.inversion.GestorModificarInversion;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author desarrollo
 */
public class CtrlModificarInversion {
    private PanelInversionPlan _panel;
    private IUModificarPlanNew _pantalla;
    private GestorModificarInversion _gestor;
    private ModelTableInversionPlan _tInversion;
    private ModelTableValorInversion _tValor;
    private boolean activo;

    public CtrlModificarInversion(IUModificarPlanNew pantalla, Plan plan) {
        this._pantalla = pantalla;
        _gestor = new GestorModificarInversion(plan);
        loadScreen();
        activo = true;
    }

    private void loadScreen(){
        _tInversion = new ModelTableInversionPlan(_gestor.iniciarCU());
        _panel = new PanelInversionPlan();
        _panel.getTblInversion().setModel(_tInversion);
        _panel.getTblInversion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        _tValor=new ModelTableValorInversion(null);
        _panel.getTblUsosInversion().setModel(_tValor);

        _panel.getTblInversion().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                mostrarDetalle();
            }
        });
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(activo)
                    pressOkButton();
            }
        });
        
        if(_tInversion.getRowCount()>0)
            _panel.getTblInversion().setRowSelectionInterval(0, 0);
        _panel.setVisible(true);

    }

    public PanelInversionPlan getPanel() {
        return _panel;
    }

    
    void mostrarDetalle(){
        int rowIndex = _panel.getTblInversion().getSelectedRow();
        if(rowIndex<0)
            return;
        _tValor.setList(_tInversion.getSelectedIndex(rowIndex).getValoresInversion());
    }

    void pressOkButton(){
        try {
            _gestor.actualizarInversiones(_tInversion.getAllRow());
            JOptionPane.showMessageDialog(_pantalla, "Operación Realizada con exito");
        } catch (BusinessOperationException ex) {
            JOptionPane.showMessageDialog(_panel, ex.getMessage(), "Error de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void activar(){
        activo= true;
        _panel.setVisible(true);
        //Desactivar de botones
        _pantalla.getBtnViewDetails().setEnabled(false);
        _pantalla.getBtnDropDetails().setEnabled(false);
        _pantalla.getBtnAdd().setEnabled(false);
        _pantalla.getBtnDel().setEnabled(false);
    }

    public void desactivar(){
        activo=false;
        _panel.setVisible(false);
    }



}
