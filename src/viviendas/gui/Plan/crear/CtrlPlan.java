package viviendas.gui.Plan.crear;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.gui.tool.ICalculable;
import java.awt.Color;
import viviendas.gui.models.tables.ModelTableProvincia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.gui.models.combos.ModelComboTipoPlan;
import viviendas.gui.models.tables.ModelTableDistribucionProvincial;
import viviendas.modulos.Plan.crear.GestorCrearPlan;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.MissingData;
import viviendas.systemException.VerifyDataException;

public class CtrlPlan implements ICalculable {

    private IUPlan _pantalla;
    private GestorCrearPlan _gestor;

    public CtrlPlan(JDesktopPane desktop) {
        _gestor = new GestorCrearPlan();
        _pantalla = new IUPlan();
        _pantalla.getTabProvincias().setModel(new ModelTableProvincia(_gestor.buscarProvincias()));
        _pantalla.getTabProvinciasSeleccionadas().setModel(new ModelTableDistribucionProvincial(new ArrayList<DistribucionProvincial>()));
        ocultarColumna(_pantalla.getTabProvincias(), 1);
        ocultarColumna(_pantalla.getTabProvinciasSeleccionadas(), 2);
        _pantalla.getComTipoPlan().setModel(new ModelComboTipoPlan(_gestor.buscarTiposPlanes()));
        SubscriptorTotal.getInstance().añadir(this);
        _pantalla.getBtnAceptar().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarNuevoPlan();
            }
        });
        _pantalla.getBtnAgregar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                agregarProvincia();
            }
        });
        _pantalla.getBtnAgregarTodas().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                agregarTodasProvincias();
            }
        });
        _pantalla.getBtnQuitar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                quitarProvincia();
            }
        });
        _pantalla.getBtnQuitarTodas().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                quitarTodasProvincias();
            }
        });
        _pantalla.getTexViviendas().addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calcularTotalVivienda();
            }
        });

        _pantalla.getSpinAños().addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                calcularTotalVivienda();
            }
        });
        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        _pantalla.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {

            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                cancelar();
            }
        });
        _pantalla.setVisible(true);
        desktop.add(_pantalla);

    }

    private void cancelar() {
        SubscriptorTotal.getInstance().remove(this);
        _pantalla.dispose();
    }

    private void cargarNuevoPlan() {
        try {
            DtoNuevoPlan dto = new DtoNuevoPlan();
            dto.setAñoInicial(((Number) _pantalla.getSpinAñoInicio().getValue()).intValue());
            dto.setAños(((Number) _pantalla.getSpinAños().getValue()).intValue());
            String cantidadViviendas = _pantalla.getTexViviendas().getText();
            if (cantidadViviendas == null || cantidadViviendas.equals("")) {
                throw new MissingData("Cantidad Vivienda");
            }
            dto.setCantidadViviendas(Integer.parseInt(cantidadViviendas));
            if (_pantalla.getTexNombre().getText() == null || _pantalla.getTexNombre().getText().equals("")) {
                throw new MissingData("Nombre Plan");
            }
            dto.setNombre(_pantalla.getTexNombre().getText());
            dto.setDistribucionProvincial(((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).getAllRow());
            TipoPlan plan = ((ModelComboTipoPlan) _pantalla.getComTipoPlan().getModel()).getSelected();
            if (plan == null) {
                mostrarMensaje("Seleccione un Tipo del Plan");
                return;
            }
            dto.setTipo(plan);
            _gestor.crearNuevoPlan(dto);
            mostrarMensaje("Se ha guardado el Plan correctamente");
            _pantalla.dispose();
        } catch (VerifyDataException ex) {
            mostrarMensaje(ex.getLocalizedMessage());
        } catch (PersistException ex) {
            mostrarMensaje(ex.getLocalizedMessage());
        } catch (MissingData md) {
            mostrarMensaje(md.getLocalizedMessage());
        }
    }

    private void agregarProvincia() {
        int index = _pantalla.getTabProvincias().getSelectedRow();
        if (index == -1) {
            mostrarMensaje("Por Favor seleccione una provincia");
        } else {
            Provincia provincia = ((ModelTableProvincia) _pantalla.getTabProvincias().getModel()).getSelectedIndex(index);
            DistribucionProvincial distribucion = new DistribucionProvincial();
            distribucion.setProvincia(provincia);
            ((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).addRow(distribucion);
            ((ModelTableProvincia) _pantalla.getTabProvincias().getModel()).delRow(provincia);
        }

    }

    private void agregarTodasProvincias() {
        List<Provincia> provincias = ((ModelTableProvincia) _pantalla.getTabProvincias().getModel()).getAllRow();
        List<DistribucionProvincial> distribuciones = new ArrayList<DistribucionProvincial>();
        for (Provincia provincia : provincias) {
            DistribucionProvincial distribucion = new DistribucionProvincial();
            distribucion.setProvincia(provincia);
            distribuciones.add(distribucion);
        }
        ((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).addAll(distribuciones);
        ((ModelTableProvincia) _pantalla.getTabProvincias().getModel()).clear();
    }

    private void quitarProvincia() {
        int index = _pantalla.getTabProvinciasSeleccionadas().getSelectedRow();
        if (index == -1) {
            mostrarMensaje("Por Favor seleccione una provincia");
        } else {
            DistribucionProvincial distribucion = ((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).getSelectedIndex(index);
            ((ModelTableProvincia) _pantalla.getTabProvincias().getModel()).addRow(distribucion.getProvincia());
            ((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).delRow(distribucion);
        }
    }

    private void quitarTodasProvincias() {
        List<DistribucionProvincial> distribuciones = ((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).getAllRow();
        List<Provincia> listaProvincia = new ArrayList<Provincia>();
        for (DistribucionProvincial distribucionProvincial : distribuciones) {
            listaProvincia.add(distribucionProvincial.getProvincia());
        }
        ((ModelTableProvincia) _pantalla.getTabProvincias().getModel()).addAll(listaProvincia);
        ((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).clear();
    }

    private void mostrarMensaje(String string) {
        JOptionPane.showMessageDialog(_pantalla, string, "", JOptionPane.INFORMATION_MESSAGE);
    }

    private void calcularTotalVivienda() {
        Integer total = 0;
        if (_pantalla.getTexViviendas().getText() != null && !_pantalla.getTexViviendas().getText().equals("")) {
            total = Integer.parseInt(_pantalla.getTexViviendas().getText());
        }
        total *= ((Number) _pantalla.getSpinAños().getValue()).intValue();
        _pantalla.getTexTotalViviendas().setText(Integer.toString(total));
    }

    @Override
    public void actualizarPorcentaje() {
        Double porcentajeTotal = _gestor.sumarPorcentaje(((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).getAllRow());
        _pantalla.getFormatedTotal().setValue(porcentajeTotal);
        _pantalla.getTexRestante().setText(Double.toString(100 - porcentajeTotal));
        try {
            _gestor.verificarEl100Porciento(porcentajeTotal);
            _pantalla.getLabTotal().setForeground(Color.BLUE);
            _pantalla.getFormatedTotal().setForeground(Color.BLUE);
            _pantalla.getLabRestante().setForeground(Color.BLUE);
            _pantalla.getTexRestante().setForeground(Color.BLUE);

        } catch (VerifyDataException ex) {
            _pantalla.getLabTotal().setForeground(Color.RED);
            _pantalla.getFormatedTotal().setForeground(Color.RED);
            _pantalla.getLabRestante().setForeground(Color.RED);
            _pantalla.getTexRestante().setForeground(Color.RED);
        }
    }

    private void ocultarColumna(JTable tabla, int columna) {
        TableColumn columnaPorcentaje = tabla.getColumnModel().getColumn(columna);
        columnaPorcentaje.setMaxWidth(0);
        columnaPorcentaje.setMinWidth(0);
        columnaPorcentaje.setPreferredWidth(0);
        columnaPorcentaje.setWidth(0);
    }
}
