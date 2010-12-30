/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Plan.modificar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.gui.models.tables.ModelTablePlan;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author desarrollo
 */
public class ctrlAbrirPlan {
    private IUAbrirPlan _pantalla;
    private ModelTablePlan _tablaPlan;
    private GestorModificarPlan _gestor;

    public ctrlAbrirPlan(JDesktopPane panel) {
        _pantalla = new IUAbrirPlan();
        _gestor = new GestorModificarPlan();
        cargarPantalla();
        panel.add(_pantalla);
        _pantalla.toFront();
        _pantalla.setSize(388, 425);
        _pantalla.setVisible(true);
    }

    private void cargarPantalla(){
        _tablaPlan = new ModelTablePlan(_gestor.listarPlanes());
        _pantalla.getTblPlane().setModel(_tablaPlan);
        if(_tablaPlan.getAllRow().isEmpty())
            _pantalla.getBtnOpen().setEnabled(false);
        _pantalla.getTblPlane().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_ESCAPE)
                    _pantalla.dispose();
                
            }

        });
        _pantalla.getTblPlane().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if(_tablaPlan.getRowCount()>0)
                _pantalla.getTblPlane().setRowSelectionInterval(0, 0);
        _pantalla.getTblPlane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getClickCount()==2)
                    pressOkButton();
            }

        });
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
        _pantalla.getBtnDelete().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressDelButton();
            }
        });

    }

    void pressOkButton(){
        int idx=_pantalla.getTblPlane().getSelectedRow();
        if(idx<0)
            return;
        _gestor.cargarPlan(_tablaPlan.getSelectedIndex(idx));
        new CtrlGrande(_gestor);
        _pantalla.dispose();

    }

    void pressDelButton(){
        int idx=_pantalla.getTblPlane().getSelectedRow();
        if(idx<0)
            return;
        if(JOptionPane.showConfirmDialog(_pantalla, "Desea eliminar el plan:\n " + _tablaPlan.getSelectedIndex(idx).getNombre(), "Eliminar Plan", JOptionPane.YES_NO_OPTION)!=0)
            return;
        try {
            _gestor.eliminarPlan(_tablaPlan.getSelectedIndex(idx));
        } catch (BusinessOperationException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            return;
        }
        _tablaPlan.delRow(idx);
    }
}
