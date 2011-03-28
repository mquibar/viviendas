/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JPanel;
import viviendas.gui.financiacion.modificar.CtrlModificarFinanciacion;
import viviendas.gui.flujoFondos.CtrlFlujoFondo;
import viviendas.gui.flujoFondos.CtrlFlujoFondos;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;

/**
 *
 * @author desarrollo
 */
public class CtrlGrande {

    private IUModificarPlanNew _pantalla;
    private ctrlModificarPlanNew _controlModificar;
    private CtrlModificarFinanciacion _controlFinanciacion;
    private CtrlModificarInversion _controlInversion;
    private CtrlFlujoFondo _ctrlFlujoFondo;
    private GestorModificarPlan _gestor;

    public CtrlGrande(GestorModificarPlan gestor) {
        _gestor = gestor;
        _pantalla = new IUModificarPlanNew();
        CtrlPrincipal.getInstance().getDesktopPane().add(_pantalla);
        
        //INSTANCIAR LOS CONTROLADORES
        _controlModificar = new ctrlModificarPlanNew(_gestor, _pantalla);

        agregarPanel(_controlModificar.getPanel());
        try {
            _pantalla.setMaximum(true);
        } catch (PropertyVetoException ex) {
            System.err.println(ex);
        }

        //MANEJO DE EVENTOS DE LOS BOTONES LATERALES
        _pantalla.getBtnAddFinanciacion().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressAddFinanciacionButton();
            }
        });
        _pantalla.getBtnPlan().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressPlanButton();
            }
        });
        _pantalla.getBtnFlujoFondo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressFlujoFondoButton();
            }
        });
        _pantalla.getBtnInversion().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressInversionButton();
            }
        });
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _pantalla.dispose();
            }
        });

        //INSTANCIA EL RESTO DE LOS CONTROLADORES Y AGREGA PANELES
        _controlFinanciacion = new CtrlModificarFinanciacion(_pantalla);
        _controlFinanciacion.desactivar();
        _controlInversion = new CtrlModificarInversion(_pantalla, _gestor.getPlan());
        _controlInversion.desactivar();
        _pantalla.setVisible(true);
        _pantalla.getBtnPlan().setEnabled(false);
        _pantalla.toFront();
        
    }

    private void agregarPanel(JPanel panel) {
        _pantalla.getPnlCentral().removeAll();
        javax.swing.GroupLayout _pnlCentralLayout = new javax.swing.GroupLayout(_pantalla.getPnlCentral());
        _pantalla.getPnlCentral().setLayout(_pnlCentralLayout);
        _pnlCentralLayout.setHorizontalGroup(
                _pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, _pnlCentralLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1820, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        _pnlCentralLayout.setVerticalGroup(
                _pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(_pnlCentralLayout.createSequentialGroup().addContainerGap().addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }

    void pressAddFinanciacionButton() {
        _controlModificar.desactivar();
        _controlInversion.desactivar();
        if (_controlFinanciacion == null) {
            _controlFinanciacion = new CtrlModificarFinanciacion(_pantalla);
        }
        agregarPanel(_controlFinanciacion.getPanFinanciacion());
        _controlFinanciacion.activar();
        _controlFinanciacion.iniciar(_controlModificar.getDistOperatoriaSeleccionada());
        _pantalla.getLblOperatoria().setText(_controlModificar.getDistOperatoriaSeleccionada().getOperatoria().getNombre());

        _pantalla.getBtnAddFinanciacion().setEnabled(false);
        _pantalla.getBtnPlan().setEnabled(true);
        _pantalla.getBtnInversion().setEnabled(true);
    }

    void pressPlanButton() {
        _controlFinanciacion.desactivar();
        _controlInversion.desactivar();
        _controlModificar.activar();
        agregarPanel(_controlModificar.getPanel());
        _pantalla.getBtnPlan().setEnabled(false);
        _pantalla.getBtnInversion().setEnabled(true);
    }

    void pressInversionButton(){
            
        _controlModificar.desactivar();
        _controlFinanciacion.desactivar();
        if(_controlInversion== null){
            _controlInversion = new CtrlModificarInversion(_pantalla, _gestor.getPlan());
        }
        agregarPanel(_controlInversion.getPanel());
        _controlInversion.activar();
        _pantalla.getBtnInversion().setEnabled(false);
        _pantalla.getBtnAddFinanciacion().setEnabled(false);
        _pantalla.getBtnPlan().setEnabled(true);
        
    }

    void pressFlujoFondoButton(){
        //_ctrlFlujoFondo = new CtrlFlujoFondos(_pantalla.getDesktopPane(), _controlModificar.getDistOperatoriaSeleccionada(), _gestor.getPlan());
        _ctrlFlujoFondo = new CtrlFlujoFondo(_pantalla.getDesktopPane(), _gestor.getPlan(), _controlModificar.getDistOperatoriaSeleccionada());
    }
}
