/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.Plan;

import javax.swing.JDesktopPane;
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.modulos.Plan.GestorCrearPlan;

/**
 *
 * @author Admin
 */
public class CtrlPlan {

    private IUPlan _pantalla;
    private GestorCrearPlan _gestor;

    public CtrlPlan(JDesktopPane desktop) {
        _pantalla = new IUPlan();
        //acciones...
        _pantalla.setVisible(true);
        desktop.add(_pantalla);
        _pantalla.getBtnAceptar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarNuevoPlan();
            }
        });
    }

    public void cargarNuevoPlan() {
        DtoNuevoPlan dto = new DtoNuevoPlan();
        dto.setAños(((Number)_pantalla.getSpinAños().getValue()).intValue());
        dto.setCantidadViviendas(((Number)_pantalla.getFormatedViviendas().getValue()).intValue());
        dto.setNombre(_pantalla.getTexNombre().getText());
        dto.setProvincias(((ModeloProvincia)_pantalla.getTabProvinciasSeleccionadas().getModel()).getList());
        dto.setTipo((TipoPlan)_pantalla.getComTipoPlan().getSelectedItem());
        //_gestor.crearNuevoPlan(dto);
    }
}
