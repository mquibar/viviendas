/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.financiacion.crear;

import java.awt.Color;
import viviendas.modulos.financiacion.crear.GestorCrearFinanciacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import viviendas.entidades.flujo.DistribucionFinanciacion;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.gui.Plan.modificar.IUModificarPlanNew;
import viviendas.gui.models.tables.ModelTableDetalleDistribucion;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.gui.tool.ICalculable;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.modulos.financiacion.crear.DtoConstruccionFinanciacion;
import viviendas.persistencia.exceptions.PersistException;

public class CtrlCrearFinanciacion implements ICalculable {

    private HashMap<Integer, JTable> hashIndiceTabla;
    private HashMap<JButton, Boolean> hashEstadoBoton;
    private GestorCrearFinanciacion _gestor;
    private IUPanelCrearFinanciacion _panFinanciacion;
    private IUModificarPlanNew _pantalla;
    private Boolean activo;

    public CtrlCrearFinanciacion(IUModificarPlanNew pantalla) {
        hashIndiceTabla = new HashMap<Integer, JTable>();
        hashEstadoBoton = new HashMap<JButton, Boolean>(6);


        this._pantalla = pantalla;
        _pantalla.getBtnDropDetails().setEnabled(false);
        _gestor = new GestorCrearFinanciacion();
        SubscriptorTotal.getInstance().añadir(this);
        _panFinanciacion = new IUPanelCrearFinanciacion();
        _panFinanciacion.getBtnCrearFinanciacion().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaCrearFinanciacion();
            }
        });
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
        _pantalla.getBtnDel().setEnabled(false);
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
        try {
            _gestor.guardarFinanciacion(((ModelTableDetalleDistribucion) hashIndiceTabla.get(_panFinanciacion.getTabPaneFinanciacion().getSelectedIndex()).getModel()).getAllRow());
            mostrarMensaje("Se ha financiacion ha sido creada satisfactoriamente");
        } catch (PersistException ex) {
            mostrarMensaje(ex.getLocalizedMessage());
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
        if (_panFinanciacion.getTabPaneFinanciacion().getTabCount() == 0) {
            _pantalla.getBtnDel().setEnabled(false);
        }
    }

    public void iniciar(DistribucionOperatoria distribucionOperatoria) {
        _panFinanciacion.getTexNombre().setEnabled(false);
        _panFinanciacion.getSpinPorcentaje().setEnabled(false);
        _panFinanciacion.getBtnCrearFinanciacion().setEnabled(false);
        _panFinanciacion.getTabPaneFinanciacion().removeAll();
        List<DistribucionFinanciacion> lista = _gestor.cargarFinanciacion(distribucionOperatoria);
        if (lista != null) {
            List<DtoConstruccionFinanciacion> listaDtoConstrucciones = new CtrlModeloDetalleFinanciacion().crearDistribucion(lista);
            for (int i = 0; i < listaDtoConstrucciones.size(); i++) {
                agregarTabla(listaDtoConstrucciones.get(i));
            }
            _pantalla.getBtnDel().setEnabled(true);
        }
        _panFinanciacion.getTexNombre().setText(_gestor.getNombreCompletoCombinacion());
        if (_panFinanciacion.getTabPaneFinanciacion().getTabCount() > 1) {
            _pantalla.getBtnDropDetails().setEnabled(true);
        }

    }

    private void presionaAgregar() {
        Double total = _gestor.calcularPorcentajeTotal();

        if (total.compareTo(100.0) != 0) {
            _panFinanciacion.getLabPorcentaje().setEnabled(true);
            _panFinanciacion.getSpinPorcentaje().setEnabled(true);
            _panFinanciacion.getBtnCrearFinanciacion().setEnabled(true);
            _pantalla.getBtnDel().setEnabled(true);
            _pantalla.getBtnOk().setEnabled(false);
        } else {
            _pantalla.getBtnOk().setEnabled(true);
        }
        _pantalla.getBtnAdd().setEnabled(false);
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
        if (activo) {
            JTable tabla = hashIndiceTabla.get(_panFinanciacion.getTabPaneFinanciacion().getSelectedIndex());
            if (tabla != null) {
                List<DtoDetalleDistribucion> listaDto = ((ModelTableDetalleDistribucion) tabla.getModel()).getAllRow();
                _gestor.actualizarPorcentaje(listaDto);
            }
        }
    }

    public IUPanelCrearFinanciacion getPanFinanciacion() {
        return _panFinanciacion;
    }

    public void activar() {
        activo = true;
        _panFinanciacion.setVisible(true);
        _pantalla.getBtnDropDetails().setEnabled(hashEstadoBoton.get(_pantalla.getBtnDropDetails()));
        _pantalla.getBtnViewDetails().setEnabled(hashEstadoBoton.get(_pantalla.getBtnViewDetails()));
        _pantalla.getBtnAdd().setEnabled(hashEstadoBoton.get(_pantalla.getBtnAdd()));
        _pantalla.getBtnDel().setEnabled(hashEstadoBoton.get(_pantalla.getBtnDel()));
        _pantalla.getBtnOk().setEnabled(hashEstadoBoton.get(_pantalla.getBtnOk()));
        _pantalla.getBtnCancel().setEnabled(hashEstadoBoton.get(_pantalla.getBtnCancel()));
    }

    public void desactivar() {
        activo = false;
        _panFinanciacion.setVisible(false);
        hashEstadoBoton.put(_pantalla.getBtnAdd(), _pantalla.getBtnAdd().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnDel(), _pantalla.getBtnDel().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnOk(), _pantalla.getBtnOk().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnCancel(), _pantalla.getBtnCancel().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnViewDetails(), _pantalla.getBtnViewDetails().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnDropDetails(), _pantalla.getBtnDropDetails().isEnabled());
    }

    private void agregarTabla(DtoConstruccionFinanciacion dtoConstruccion) {
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable tabla = new JTable();
        tabla.setModel(new ModelTableDetalleDistribucion(dtoConstruccion.getDtoDetallesDistribuciones(), dtoConstruccion.getColumas()));
        hashIndiceTabla.put(_panFinanciacion.getTabPaneFinanciacion().getTabCount(), tabla);
        jScrollPane1.setViewportView(tabla);
        tabla.setVisible(true);
        _panFinanciacion.getTabPaneFinanciacion().addTab(dtoConstruccion.getNombre(), jScrollPane1);
    }

    private void presionaCrearFinanciacion() {
        Double total = _gestor.calcularPorcentajeTotal();
        _pantalla.getTxtTotal().setText(total.toString());
        _pantalla.getTxtRestante().setText(String.valueOf(100.0 - total));
        if (total.compareTo(100.0) != 0) {
            _pantalla.getBtnDel().setEnabled(true);
            _pantalla.getTxtRestante().setForeground(Color.RED);
            _pantalla.getTxtTotal().setForeground(Color.RED);
            _pantalla.getBtnOk().setEnabled(false);
        } else {
            _pantalla.getTxtRestante().setForeground(Color.BLUE);
            _pantalla.getTxtTotal().setForeground(Color.BLUE);
            _pantalla.getBtnOk().setEnabled(true);
        }
        _panFinanciacion.getLabPorcentaje().setEnabled(true);
        _panFinanciacion.getSpinPorcentaje().setEnabled(true);
        _panFinanciacion.getBtnCrearFinanciacion().setEnabled(true);
        _panFinanciacion.getSpinPorcentaje().setModel(new javax.swing.SpinnerNumberModel(100.0d - total, 0.0d, 100.0d - total, 0.1d));
        agregarTabla(new CtrlModeloDetalleFinanciacion().crearDistribucion(_gestor.crearDistribucion(((Number) _panFinanciacion.getSpinPorcentaje().getValue()).doubleValue())));
    }
}
