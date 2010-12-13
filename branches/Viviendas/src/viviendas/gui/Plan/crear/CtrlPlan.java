package viviendas.gui.Plan.crear;

import java.awt.Color;
import viviendas.gui.models.tables.ModelTableProvincia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.gui.models.combos.ModelComboTipoPlan;
import viviendas.gui.models.tables.ModelTableDistribucionProvincial;
import viviendas.modulos.Plan.GestorCrearPlan;
import viviendas.persistencia.exceptions.PersistException;

public class CtrlPlan implements ICalculable {

    private IUPlan _pantalla;
    private GestorCrearPlan _gestor;

    public CtrlPlan(JDesktopPane desktop) {
        _gestor = new GestorCrearPlan();
        _pantalla = new IUPlan();
        _pantalla.getTabProvincias().setModel(new ModelTableProvincia(_gestor.buscarProvincias()));
        _pantalla.getTabProvinciasSeleccionadas().setModel(new ModelTableDistribucionProvincial(new ArrayList<DistribucionProvincial>()));
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
        _pantalla.setVisible(true);
        desktop.add(_pantalla);
    }

    private void cargarNuevoPlan() {
        DtoNuevoPlan dto = new DtoNuevoPlan();
        dto.setAños(((Number) _pantalla.getSpinAños().getValue()).intValue());
        dto.setCantidadViviendas(((Number) _pantalla.getFormatedViviendas().getValue()).intValue());
        dto.setNombre(_pantalla.getTexNombre().getText());
        dto.setDistribucionProvincial(((ModelTableDistribucionProvincial) _pantalla.getTabProvinciasSeleccionadas().getModel()).getAllRow());
        TipoPlan plan = ((ModelComboTipoPlan) _pantalla.getComTipoPlan().getModel()).getSelected();
        if (plan == null) {
            mostrarMensaje("Seleccione un Tipo del Plan");
            return;
        }
        dto.setTipo(plan);
        try {
            _gestor.crearNuevoPlan(dto);
            mostrarMensaje("Se ha guardado el Plan correctamente");
            _pantalla.dispose();
        } catch (PersistException ex) {
            mostrarMensaje(ex.getLocalizedMessage());
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
            SubscriptorTotal.getInstance().notificar(-distribucion.getPorcentajeDistribucion());
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
        SubscriptorTotal.getInstance().notificar(new Double(0));
    }

    private void mostrarMensaje(String string) {
        JOptionPane.showMessageDialog(_pantalla, string, "", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarTotalPorcentaje(Double porcentajeTotal) {
        _pantalla.getFormatedTotal().setValue(porcentajeTotal);
        _pantalla.getTexRestante().setText(Double.toString(100 - porcentajeTotal));
        if (new Double(100).compareTo(porcentajeTotal) == 0) {
            _pantalla.getLabTotal().setForeground(Color.GREEN);
            _pantalla.getFormatedTotal().setForeground(Color.GREEN);
            _pantalla.getLabRestante().setForeground(Color.GREEN);
            _pantalla.getTexRestante().setForeground(Color.GREEN);
        } else {
            _pantalla.getLabTotal().setForeground(Color.RED);
            _pantalla.getFormatedTotal().setForeground(Color.RED);
            _pantalla.getLabRestante().setForeground(Color.RED);
            _pantalla.getTexRestante().setForeground(Color.RED);
        }
    }
}