/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.financiacion.crear;

import viviendas.modulos.financiacion.crear.GestorCrearFinanciacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.gui.Plan.modificar.ctrlModificarPlan;
import viviendas.gui.models.tables.ModelTableDetalleDistribucion;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.gui.tool.ICalculable;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.modulos.financiacion.crear.DtoConstruccionFinanciacion;
import viviendas.systemException.MissingData;

public class CtrlCrearFinanciacion implements ICalculable {

    private HashMap<Integer, JTable> hashIndiceTabla;
    private GestorCrearFinanciacion _gestor;
    private IUFinanciacion _pantalla;
    private final ctrlModificarPlan ctrlModificarPlan;

    public CtrlCrearFinanciacion(DistribucionOperatoria distribucionOperatoria, ctrlModificarPlan ctrlModificarPlan) {
        hashIndiceTabla = new HashMap<Integer, JTable>();
        _gestor = new GestorCrearFinanciacion(distribucionOperatoria);
        SubscriptorTotal.getInstance().añadir(this);
        this.ctrlModificarPlan = ctrlModificarPlan;
        _pantalla = new IUFinanciacion();
        _pantalla.getLabCombinacion().setText(_gestor.getNombreCompletoCombinacion());
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
        try {
            if (_pantalla.getTexNombre().getText().equals("") || _pantalla.getTexNombre().getText() == null) {
                throw new MissingData("Nombre");
            }
            ctrlModificarPlan.desbloquear();
        } catch (MissingData md) {
            mostrarMensaje(md.getLocalizedMessage());
        }
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
    }

    private void presionaCrearFinanciacion() {
        _pantalla.getTexNombre().setEnabled(false);
        _pantalla.getSpinPorcentaje().setEnabled(false);
        _pantalla.getBtnCrearFinanciacion().setEnabled(false);
        DtoConstruccionFinanciacion dto = new CtrlModeloDetalleFinanciacion().crearDistribucion(((Number) _pantalla.getSpinPorcentaje().getValue()).doubleValue());
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable tabla = new JTable();
        tabla.setModel(new ModelTableDetalleDistribucion(dto.getDtoDetallesDistribuciones(), dto.getColumas()));
        jScrollPane1.setViewportView(tabla);
        hashIndiceTabla.put(_pantalla.getTabPaneFinanciacion().getTabCount(), tabla);
        _pantalla.getTabPaneFinanciacion().addTab(dto.getNombre(), jScrollPane1);
        if (_pantalla.getTabPaneFinanciacion().getTabCount() > 1) {
            _pantalla.getBtnDropDetails().setEnabled(true);
        }
        _pantalla.getTabPaneFinanciacion().setSelectedIndex(_pantalla.getTabPaneFinanciacion().getTabCount() - 1);
    }

    private void presionaAgregar() {
        _pantalla.getTexNombre().setEnabled(true);
        _pantalla.getSpinPorcentaje().setEnabled(true);
        _pantalla.getBtnCrearFinanciacion().setEnabled(true);
        Double total = 0.0;
        _pantalla.getSpinPorcentaje().setModel(new javax.swing.SpinnerNumberModel(100.0d - total, 0.0d, 100.0d - total, 0.1d));

    }

    private void presionaCancelar() {
        _pantalla.dispose();
        _pantalla = null;
        _gestor = null;
        SubscriptorTotal.getInstance().remove(this);
        ctrlModificarPlan.desbloquear();
    }

    private void mostrarMensaje(String string) {
        JOptionPane.showMessageDialog(_pantalla, string, "", JOptionPane.INFORMATION_MESSAGE);
    }

    public void actualizarPorcentaje() {
        JTable tabla = hashIndiceTabla.get(_pantalla.getTabPaneFinanciacion().getSelectedIndex());
        List<DtoDetalleDistribucion> listaDto = ((ModelTableDetalleDistribucion) tabla.getModel()).getAllRow();
        _gestor.actualizarPorcentaje(listaDto);

    }
}
