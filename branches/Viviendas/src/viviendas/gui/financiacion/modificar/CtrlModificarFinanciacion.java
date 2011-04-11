/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.financiacion.modificar;

import java.awt.Color;
import javax.swing.event.ChangeEvent;
import viviendas.modulos.financiacion.modificar.GestorModificarFinanciacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import viviendas.entidades.flujo.DistribucionFinanciacion;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.gui.Plan.modificar.IUModificarPlanNew;
import viviendas.gui.financiacion.crear.CtrlAplicarFinanciaciones;
import viviendas.gui.models.tables.ModelTableDetalleDistribucion;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.gui.tool.ICalculable;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.VerifyDataException;

public class CtrlModificarFinanciacion implements ICalculable {

    private HashMap<JScrollPane, JTable> hashIndiceTabla;
    private HashMap<JButton, Boolean> hashEstadoBoton;
    private GestorModificarFinanciacion _gestor;
    private IUPanelModificarFinanciacion _panFinanciacion;
    private IUModificarPlanNew _pantalla;
    private Boolean activo;

    public CtrlModificarFinanciacion(IUModificarPlanNew pantalla) {
        activo = false;
        this._pantalla = pantalla;
        hashIndiceTabla = new HashMap<JScrollPane, JTable>();
        _pantalla.getBtnDropDetails().setEnabled(false);
        hashEstadoBoton = new HashMap<JButton, Boolean>(6);
        hashEstadoBoton.put(_pantalla.getBtnAdd(), _pantalla.getBtnAdd().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnDel(), _pantalla.getBtnDel().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnOk(), _pantalla.getBtnOk().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnCancel(), _pantalla.getBtnCancel().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnViewDetails(), _pantalla.getBtnViewDetails().isEnabled());
        hashEstadoBoton.put(_pantalla.getBtnDropDetails(), _pantalla.getBtnDropDetails().isEnabled());
        _gestor = new GestorModificarFinanciacion();
        SubscriptorTotal.getInstance().añadir(this);
        _panFinanciacion = new IUPanelModificarFinanciacion();
        _panFinanciacion.getBtnCrearFinanciacion().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaCrearFinanciacion();
            }
        });

        _pantalla.getBtnAdd().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    presionaBtnAdd();
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
                    presionaBtnDel();
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
        _panFinanciacion.getBtnAplicarA().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaBtnAplicarA();
            }
        });
        _panFinanciacion.getTabPaneFinanciacion().addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                actualizarToolKit();
            }
        });
        CtrlPrincipal.getInstance().getDesktopPane().add(_panFinanciacion);
        _panFinanciacion.setVisible(true);
    }

    private void presionaViewDetails() {
        int indice = _panFinanciacion.getTabPaneFinanciacion().getSelectedIndex();
        _panFinanciacion.getTabPaneFinanciacion().setSelectedIndex(indice + 1);
        actualizarToolKit();
    }

    private void presionaBtnOk() {
        try {
            _gestor.guardarFinanciacion(((ModelTableDetalleDistribucion) hashIndiceTabla.get((JScrollPane) _panFinanciacion.getTabPaneFinanciacion().getSelectedComponent()).getModel()).getAllRow());
            mostrarMensaje("Se ha financiacion ha sido creada satisfactoriamente");
        } catch (PersistException ex) {
            mostrarMensaje(ex.getLocalizedMessage());
        }

    }

    private void presionaDropDetails() {
        int indice = _panFinanciacion.getTabPaneFinanciacion().getSelectedIndex();
        _panFinanciacion.getTabPaneFinanciacion().setSelectedIndex(indice - 1);
        actualizarToolKit();
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
        actualizarToolKit();
        _panFinanciacion.getTexNombre().setText(GestorModificarFinanciacion.getNombreCompletoCombinacion(_gestor.getFinanciacion().getDistribucionOperatoria()));
    }

    private void presionaBtnAdd() {
        _panFinanciacion.getLabPorcentaje().setEnabled(true);
        _panFinanciacion.getSpinPorcentaje().setEnabled(true);
        _panFinanciacion.getBtnCrearFinanciacion().setEnabled(true);
        _pantalla.getBtnDel().setEnabled(false);
        _pantalla.getBtnOk().setEnabled(false);
        _pantalla.getBtnAdd().setEnabled(false);
        Double total = _gestor.calcularPorcentajeTotal();
        _panFinanciacion.getSpinPorcentaje().setModel(new javax.swing.SpinnerNumberModel(100.0d - total, 0.0d, 100.0d - total, 0.1d));
    }

    private void presionaBtnDel() {
        JTable tabla = hashIndiceTabla.get((JScrollPane) _panFinanciacion.getTabPaneFinanciacion().getSelectedComponent());
        DistribucionFinanciacion distribucionFinanciacion = ((ModelTableDetalleDistribucion) tabla.getModel()).getAllRow().get(0).getDetallesDistribucionesFinanciacion().get(0).getDistribucionFinanciacion();
        _gestor.eliminarDistribucion(distribucionFinanciacion);
        hashIndiceTabla.remove((JScrollPane) _panFinanciacion.getTabPaneFinanciacion().getSelectedComponent());
        _panFinanciacion.getTabPaneFinanciacion().removeTabAt(_panFinanciacion.getTabPaneFinanciacion().getSelectedIndex());
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
            JTable tabla = hashIndiceTabla.get((JScrollPane) _panFinanciacion.getTabPaneFinanciacion().getSelectedComponent());
            if (tabla != null) {
                List<DtoDetalleDistribucion> listaDto = ((ModelTableDetalleDistribucion) tabla.getModel()).getAllRow();
                try {
                    _gestor.actualizarPorcentaje(listaDto);
                    actualizarToolKit();
                } catch (VerifyDataException ex) {
                    _pantalla.getBtnOk().setEnabled(false);
                }
            }
        }
    }

    public IUPanelModificarFinanciacion getPanFinanciacion() {
        return _panFinanciacion;
    }

    public void activar() {
        if (activo == false) {
            activo = true;
            _panFinanciacion.setVisible(true);
            _pantalla.getBtnDropDetails().setEnabled(hashEstadoBoton.get(_pantalla.getBtnDropDetails()));
            _pantalla.getBtnViewDetails().setEnabled(hashEstadoBoton.get(_pantalla.getBtnViewDetails()));
            _pantalla.getBtnAdd().setEnabled(hashEstadoBoton.get(_pantalla.getBtnAdd()));
            _pantalla.getBtnDel().setEnabled(hashEstadoBoton.get(_pantalla.getBtnDel()));
            _pantalla.getBtnOk().setEnabled(hashEstadoBoton.get(_pantalla.getBtnOk()));
            _pantalla.getBtnCancel().setEnabled(hashEstadoBoton.get(_pantalla.getBtnCancel()));
        }
    }

    public void desactivar() {
        if (activo == true) {
            activo = false;
            _panFinanciacion.setVisible(false);
            hashEstadoBoton.put(_pantalla.getBtnAdd(), _pantalla.getBtnAdd().isEnabled());
            hashEstadoBoton.put(_pantalla.getBtnDel(), _pantalla.getBtnDel().isEnabled());
            hashEstadoBoton.put(_pantalla.getBtnOk(), _pantalla.getBtnOk().isEnabled());
            hashEstadoBoton.put(_pantalla.getBtnCancel(), _pantalla.getBtnCancel().isEnabled());
            hashEstadoBoton.put(_pantalla.getBtnViewDetails(), _pantalla.getBtnViewDetails().isEnabled());
            hashEstadoBoton.put(_pantalla.getBtnDropDetails(), _pantalla.getBtnDropDetails().isEnabled());
        }
    }

    private void agregarTabla(DtoConstruccionFinanciacion dtoConstruccion) {
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable tabla = new viviendas.gui.tool.TableUpdated();
        tabla.setModel(new ModelTableDetalleDistribucion(dtoConstruccion.getDtoDetallesDistribuciones(), dtoConstruccion.getColumas()));
        hashIndiceTabla.put(jScrollPane1, tabla);
        jScrollPane1.setViewportView(tabla);
        tabla.setVisible(true);
        _panFinanciacion.getTabPaneFinanciacion().addTab(dtoConstruccion.getNombre(), jScrollPane1);
    }

    private void presionaCrearFinanciacion() {
        agregarTabla(new CtrlModeloDetalleFinanciacion().crearDistribucion(_gestor.crearDistribucion(((Number) _panFinanciacion.getSpinPorcentaje().getValue()).doubleValue())));
        _panFinanciacion.getLabPorcentaje().setEnabled(false);
        _panFinanciacion.getSpinPorcentaje().setEnabled(false);
        _panFinanciacion.getBtnCrearFinanciacion().setEnabled(false);
        actualizarToolKit();
    }

    private void presionaBtnAplicarA() {
        new CtrlAplicarFinanciaciones(this, _gestor.getFinanciacion());
        _panFinanciacion.getBtnAplicarA().setEnabled(false);
    }

    private void actualizarToolKit() {
        Double total = _gestor.calcularPorcentajeTotal();
        _pantalla.getTxtTotal().setText(total.toString());
        _pantalla.getTxtRestante().setText(String.valueOf(100.0 - total));
        _pantalla.getBtnDel().setEnabled(_panFinanciacion.getTabPaneFinanciacion().getTabCount() > 0);
        if (total.compareTo(100.0d) == 0) {
            _pantalla.getTxtRestante().setForeground(Color.BLUE);
            _pantalla.getTxtTotal().setForeground(Color.BLUE);
            _pantalla.getBtnAdd().setEnabled(false);
            _pantalla.getBtnOk().setEnabled(true);
            _panFinanciacion.getBtnAplicarA().setEnabled(true);
        } else {
            _pantalla.getBtnDel().setEnabled(true);
            _pantalla.getTxtRestante().setForeground(Color.RED);
            _pantalla.getTxtTotal().setForeground(Color.RED);
            _pantalla.getBtnAdd().setEnabled(true);
            _pantalla.getBtnOk().setEnabled(false);
            _panFinanciacion.getBtnAplicarA().setEnabled(false);
        }
        int index = _panFinanciacion.getTabPaneFinanciacion().getSelectedIndex();
        if (index != -1) {
            if (index == 0) {
                _pantalla.getBtnDropDetails().setEnabled(false);
                _pantalla.getBtnViewDetails().setEnabled(_panFinanciacion.getTabPaneFinanciacion().getTabCount() > 1);
            } else if (index > 0 && index < _panFinanciacion.getTabPaneFinanciacion().getTabCount() - 1) {
                _pantalla.getBtnDropDetails().setEnabled(true);
                _pantalla.getBtnViewDetails().setEnabled(true);
            } else if (index == _panFinanciacion.getTabPaneFinanciacion().getTabCount() - 1) {
                _pantalla.getBtnDropDetails().setEnabled(true);
                _pantalla.getBtnViewDetails().setEnabled(false);
            }
        } else {
            _pantalla.getBtnDel().setEnabled(false);
            _pantalla.getBtnAdd().setEnabled(true);
            _pantalla.getBtnDropDetails().setEnabled(false);
            _pantalla.getBtnOk().setEnabled(false);
            _pantalla.getBtnViewDetails().setEnabled(false);
            _pantalla.getBtnCancel().setEnabled(true);
        }
    }

    public void ocultar(Boolean ocultar) {
        _pantalla.setVisible(!ocultar);
    }
}
