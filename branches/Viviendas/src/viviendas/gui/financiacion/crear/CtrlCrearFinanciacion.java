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
import viviendas.gui.Plan.modificar.IUModificarPlanNew;
import viviendas.gui.models.tables.ModelTableDetalleDistribucion;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.gui.tool.ICalculable;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.modulos.financiacion.crear.DtoConstruccionFinanciacion;
import viviendas.systemException.MissingData;

public class CtrlCrearFinanciacion implements ICalculable {

    private HashMap<Integer, JTable> hashIndiceTabla;
    private GestorCrearFinanciacion _gestor;
    private IUPanelCrearFinanciacion _panFinanciacion;
    private IUModificarPlanNew _pantalla;
    private Boolean activo;

    public CtrlCrearFinanciacion(DistribucionOperatoria distribucionOperatoria, IUModificarPlanNew pantalla) {
        hashIndiceTabla = new HashMap<Integer, JTable>();
        _pantalla = pantalla;
        _pantalla.getBtnDropDetails().setEnabled(false);
        _gestor = new GestorCrearFinanciacion(distribucionOperatoria);
        SubscriptorTotal.getInstance().añadir(this);
        _panFinanciacion = new IUPanelCrearFinanciacion();
        _panFinanciacion.getTexNombre().setText(_gestor.getNombreCompletoCombinacion());
        _pantalla.getBtnAdd().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    presionaAgregar();
                }
            }
        });
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    presionaCancelar();
                }
            }
        });
        _pantalla.getBtnDel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    presionaEliminar();
                }
            }
        });
        _pantalla.getBtnDropDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    presionaDropDetails();
                }
            }
        });
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    presionaBtnOk();
                }
            }
        });
        _pantalla.getBtnViewDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    presionaViewDetails();
                }
            }
        });
        CtrlPrincipal.getInstance().getDesktopPane().add(_panFinanciacion);
        _panFinanciacion.setVisible(true);
    }

    private void presionaViewDetails() {
        int indice = _panFinanciacion.getTabPaneFinanciacion().getSelectedIndex();
        _panFinanciacion.getTabPaneFinanciacion().setSelectedIndex(indice + 1);
        int total = _panFinanciacion.getTabPaneFinanciacion().getTabCount();
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
            if (_panFinanciacion.getTexNombre().getText().equals("") || _panFinanciacion.getTexNombre().getText() == null) {
                throw new MissingData("Nombre");
            }
        } catch (MissingData md) {
            mostrarMensaje(md.getLocalizedMessage());
        }
    }

    private void presionaDropDetails() {
        int indice = _panFinanciacion.getTabPaneFinanciacion().getSelectedIndex();
        _panFinanciacion.getTabPaneFinanciacion().setSelectedIndex(indice - 1);
        if (indice - 1 == 0) {
            _pantalla.getBtnDropDetails().setEnabled(false);
        } else {
            _pantalla.getBtnDropDetails().setEnabled(true);
        }
        _pantalla.getBtnViewDetails().setEnabled(true);
    }

    private void presionaEliminar() {
        _panFinanciacion.getTabPaneFinanciacion().remove(_panFinanciacion.getTabPaneFinanciacion().getSelectedIndex());
    }

    public void iniciar() {
        _panFinanciacion.getTexNombre().setEnabled(false);
        _panFinanciacion.getSpinPorcentaje().setEnabled(false);
        _panFinanciacion.getBtnCrearFinanciacion().setEnabled(false);
        DtoConstruccionFinanciacion dto = new CtrlModeloDetalleFinanciacion().crearDistribucion(((Number) _panFinanciacion.getSpinPorcentaje().getValue()).doubleValue());
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable tabla = new JTable();
        tabla.setModel(new ModelTableDetalleDistribucion(dto.getDtoDetallesDistribuciones(), dto.getColumas()));
        jScrollPane1.setViewportView(tabla);
        hashIndiceTabla.put(_panFinanciacion.getTabPaneFinanciacion().getTabCount(), tabla);
        _panFinanciacion.getTabPaneFinanciacion().addTab(dto.getNombre(), jScrollPane1);
        if (_panFinanciacion.getTabPaneFinanciacion().getTabCount() > 1) {
            _pantalla.getBtnDropDetails().setEnabled(true);
        }
        _panFinanciacion.getTabPaneFinanciacion().setSelectedIndex(_panFinanciacion.getTabPaneFinanciacion().getTabCount() - 1);
    }

    private void presionaAgregar() {
        _panFinanciacion.getLabPorcentaje().setEnabled(true);
        _panFinanciacion.getSpinPorcentaje().setEnabled(true);
        _panFinanciacion.getBtnCrearFinanciacion().setEnabled(true);
        Double total = 0.0;
        _panFinanciacion.getSpinPorcentaje().setModel(new javax.swing.SpinnerNumberModel(100.0d - total, 0.0d, 100.0d - total, 0.1d));

    }

    private void presionaCancelar() {
        _pantalla.dispose();
        _panFinanciacion = null;
        _gestor = null;
        SubscriptorTotal.getInstance().remove(this);
    }

    private void mostrarMensaje(String string) {
        JOptionPane.showMessageDialog(_panFinanciacion, string, "", JOptionPane.INFORMATION_MESSAGE);
    }

    public void actualizarPorcentaje() {
        JTable tabla = hashIndiceTabla.get(_panFinanciacion.getTabPaneFinanciacion().getSelectedIndex());
        if (tabla != null) {
            List<DtoDetalleDistribucion> listaDto = ((ModelTableDetalleDistribucion) tabla.getModel()).getAllRow();
            _gestor.actualizarPorcentaje(listaDto);
        }

    }

    public IUPanelCrearFinanciacion getPanFinanciacion() {
        return _panFinanciacion;
    }

    public void activar() {
        activo = true;
        _panFinanciacion.setVisible(true);
    }

    public void desactivar() {
        activo = false;
        _panFinanciacion.setVisible(false);
    }
}
