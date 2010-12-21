/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.financiacion.crear;

import viviendas.modulos.financiacion.crear.GestorCrearFinanciacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.gui.Plan.modificar.ctrlModificarPlan;
import viviendas.gui.sistema.CtrlPrincipal;

public class CtrlCrearFinanciacion {

    private GestorCrearFinanciacion _gestor;
    private IUFinanciacion _pantalla;
    private final ctrlModificarPlan ctrlModificarPlan;

    public CtrlCrearFinanciacion(DistribucionOperatoria distribucionOperatoria, ctrlModificarPlan ctrlModificarPlan) {
        _gestor = new GestorCrearFinanciacion(distribucionOperatoria);
        this.ctrlModificarPlan = ctrlModificarPlan;
        _pantalla = new IUFinanciacion();
        _pantalla.getBtnAdd().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaAgregar();
            }
        });
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaCancelar();
            }
        });
        _pantalla.getBtnCrearFinanciacion().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaCrearFinanciacion();
            }
        });
        _pantalla.getBtnDel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaEliminar();
            }
        });
        _pantalla.getBtnDropDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaDropDetails();
            }
        });
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaBtnOk();
            }
        });
        _pantalla.getBtnViewDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaViewDetails();
            }
        });
        CtrlPrincipal.getInstance().getDesktopPane().add(_pantalla);
        _pantalla.setVisible(true);
    }

    private void presionaViewDetails() {
        int indice = _pantalla.getTabPaneFinanciacion().getSelectedIndex();
        _pantalla.getTabPaneFinanciacion().setSelectedIndex(indice + 1);
        int total = _pantalla.getTabPaneFinanciacion().getTabCount();
        if (indice + 2 == total) {
            _pantalla.getBtnViewDetails().setEnabled(false);
        } else {
            _pantalla.getBtnViewDetails().setEnabled(true);
        }
        _pantalla.getBtnDropDetails().setEnabled(true);
    }

    private void presionaBtnOk() {
//TODO falta persistir el plan
        ctrlModificarPlan.desbloquear();
    }

    private void presionaDropDetails() {
        int indice = _pantalla.getTabPaneFinanciacion().getSelectedIndex();
        _pantalla.getTabPaneFinanciacion().setSelectedIndex(indice - 1);
        if (indice - 1 == 0) {
            _pantalla.getBtnDropDetails().setEnabled(false);
        } else {
            _pantalla.getBtnDropDetails().setEnabled(true);
        }
        _pantalla.getBtnViewDetails().setEnabled(true);
    }

    private void presionaEliminar() {
        _pantalla.getTabPaneFinanciacion().remove(_pantalla.getTabPaneFinanciacion().getSelectedIndex());
        verificarTotal();
    }

    private void presionaCrearFinanciacion() {
        _pantalla.getTexNombre().setEnabled(false);
        _pantalla.getSpinPorcentaje().setEnabled(false);
        _pantalla.getBtnCrearFinanciacion().setEnabled(false);
        _gestor.crearDistribucion(((Number) _pantalla.getSpinPorcentaje().getValue()).doubleValue());
        verificarTotal();
        JTable tabla = new JTable();
        _pantalla.getTabPaneFinanciacion().addTab("Financiacion i++", tabla);
        _pantalla.getBtnDropDetails().setEnabled(true);
        _pantalla.getTabPaneFinanciacion().setSelectedIndex(_pantalla.getTabPaneFinanciacion().getTabCount() - 1);
    }

    private void presionaAgregar() {
        _pantalla.getTexNombre().setEnabled(true);
        _pantalla.getSpinPorcentaje().setEnabled(true);
        _pantalla.getBtnCrearFinanciacion().setEnabled(true);
        Double total = _gestor.calcularPorcentaje();
        _pantalla.getSpinPorcentaje().setModel(new javax.swing.SpinnerNumberModel(100.0d - total, 0.0d, 100.0d - total, 0.10000000000000009d));

    }

    private void presionaCancelar() {
        _pantalla.dispose();
        _pantalla = null;
        _gestor = null;
        ctrlModificarPlan.desbloquear();
    }

    private void verificarTotal() {
        Double total = _gestor.calcularPorcentaje();
        _pantalla.getTexRestante().setText(String.valueOf(100.0d - total));
        _pantalla.getTexTotal().setText(String.valueOf(total));
        if (total.compareTo(100.0) == 0) {
            _pantalla.getBtnOk().setEnabled(true);
        }
    }
}
