/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JPanel;
import viviendas.gui.financiacion.crear.CtrlCrearFinanciacion;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;

/**
 *
 * @author desarrollo
 */
public class CtrlGrande {

    private IUModificarPlanNew _pantalla;
    private ctrlModificarPlanNew _controlModificar;
    private CtrlCrearFinanciacion _controlFinanciacion;
    private GestorModificarPlan _gestor;

    public CtrlGrande(GestorModificarPlan gestor) {
        _gestor = gestor;
        _pantalla = new IUModificarPlanNew();
        CtrlPrincipal.getInstance().getDesktopPane().add(_pantalla);
        _controlModificar = new ctrlModificarPlanNew(gestor, _pantalla);
        agregarPanel(_controlModificar.getPanel());
        try {
            _pantalla.setMaximum(true);
        } catch (PropertyVetoException ex) {
            System.err.println(ex);
        }
        _pantalla.getBtnAddFinanciacion().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressAddFinanciacionButton();
            }
        });
        _pantalla.setVisible(true);
        _pantalla.toFront();
    }

    private void agregarPanel(JPanel panel) {
        javax.swing.GroupLayout _pnlCentralLayout = new javax.swing.GroupLayout(_pantalla.getPnlCentral());
        _pantalla.getPnlCentral().setLayout(_pnlCentralLayout);
        _pnlCentralLayout.setHorizontalGroup(
                _pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, _pnlCentralLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1820, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        _pnlCentralLayout.setVerticalGroup(
                _pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(_pnlCentralLayout.createSequentialGroup().addContainerGap().addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }

    void pressAddFinanciacionButton() {
        _controlModificar.desactivar();
        if (_controlFinanciacion == null) {
            _controlFinanciacion = new CtrlCrearFinanciacion(_controlModificar.getDistOperatoriaSeleccionada(), _pantalla);
            agregarPanel(_controlFinanciacion.getPanFinanciacion());
            _controlFinanciacion.iniciar();
        }
        _controlFinanciacion.activar();
    }
}
