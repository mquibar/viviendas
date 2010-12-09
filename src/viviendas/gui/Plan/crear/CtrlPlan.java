package viviendas.gui.Plan.crear;

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

public class CtrlPlan {

    private IUPlan _pantalla;
    private GestorCrearPlan _gestor;

    public CtrlPlan(JDesktopPane desktop) {
        _gestor = new GestorCrearPlan();
        _pantalla = new IUPlan();
        _pantalla.getTabProvincias().setModel(new ModelTableProvincia(_gestor.buscarProvincias()));
        _pantalla.getTabProvinciasSeleccionadas().setModel(new ModelTableDistribucionProvincial(new ArrayList<DistribucionProvincial>()));
        _pantalla.getComTipoPlan().setModel(new ModelComboTipoPlan(_gestor.buscarTiposPlanes()));
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
            mostrarError("Seleccione un Tipo del Plan");
            return;
        }
        dto.setTipo(plan);
        _gestor.crearNuevoPlan(dto);
    }

    private void agregarProvincia() {
        int index = _pantalla.getTabProvincias().getSelectedRow();
        if (index == -1) {
            mostrarError("Por Favor seleccione una provincia");
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
            mostrarError("Por Favor seleccione una provincia");
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

    private void mostrarError(String string) {
        JOptionPane.showMessageDialog(_pantalla, string, "", JOptionPane.INFORMATION_MESSAGE);
    }
}
