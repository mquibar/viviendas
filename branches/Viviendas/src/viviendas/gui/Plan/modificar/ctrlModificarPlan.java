/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.Plan.modificar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.entidades.vivienda.Plan;
import viviendas.gui.tool.ICalculable;
import viviendas.gui.models.tables.ModelTableAño;
import viviendas.gui.models.tables.ModelTableDistribucionCiudad;
import viviendas.gui.models.tables.ModelTableDistribucionOperatoria;
import viviendas.gui.models.tables.ModelTableDistribucionProvincial;
import viviendas.gui.models.tables.ModelTableDistribucionSectorEconomico;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author desarrollo
 */
public class ctrlModificarPlan implements ICalculable {

    private IUModificarPlan _pantalla;
    private ModelTableAño _tablaAños;
    private ModelTableDistribucionProvincial _distProvincial;
    private ModelTableDistribucionCiudad _distCiudad;
    private ModelTableDistribucionSectorEconomico _distSEconomico;
    private ModelTableDistribucionOperatoria _distOperatoria;
    private GestorModificarPlan _gestor;
    private final int AÑO = 0;
    private final int PROVINCIA = 1;
    private final int CIUDAD = 2;
    private final int SECTORECONOMICO = 3;
    private final int OPERATORIA = 4;
    private int tablaOnTop = AÑO;

    public ctrlModificarPlan(GestorModificarPlan gestor, JDesktopPane panel) {
        _gestor = gestor;
        _pantalla = new IUModificarPlan();
        _tablaAños = new ModelTableAño(null);
        _distProvincial = new ModelTableDistribucionProvincial(null);
        _distCiudad = new ModelTableDistribucionCiudad(null);
        _distSEconomico = new ModelTableDistribucionSectorEconomico(null);
        _distOperatoria = new ModelTableDistribucionOperatoria(null);

        cargarPantalla();
        panel.add(_pantalla);
        _pantalla.setVisible(true);
        SubscriptorTotal.getInstance().añadir(this);
    }

    final void cargarPantalla() {
        Plan plan = _gestor.getPlan();
        MenuClickDerecho mnuDerecho = new MenuClickDerecho(this, _pantalla);
        _pantalla.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                pressCancelButton();
            }

        });
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressOkButton();
            }
        });
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressCancelButton();
            }
        });

        //DATOS DEL PLAN
        _pantalla.getTxtNombre().setText(plan.getNombre());
        _pantalla.getTxtTipo().setText(plan.getTipoPlan().getNombre());
        _pantalla.getTxtAños().setText(plan.getAñosPlan().toString());
        _pantalla.getTxtTotViviendas().setText(plan.getNumeroViviendas().toString());

        _tablaAños.setList(_gestor.getPlan().getListaAñoPlan());
        //MANEJO DE EVENTOS PARA LA TABLA AÑO
        _pantalla.getTblAños().setModel(_tablaAños);
        _pantalla.getTblAños().addMouseListener(mnuDerecho);
        _pantalla.getScrAño().addMouseListener(mnuDerecho);
        //MANEJO DE EVENTOS PARA LA TABLA PROVINCIA
        _pantalla.getTblProvincia().setModel(_distProvincial);
        _pantalla.getTblProvincia().addMouseListener(mnuDerecho);
        _pantalla.getScrProvincia().setVisible(false);
        _pantalla.getScrProvincia().addMouseListener(mnuDerecho);
        //MANEJO DE EVENTOS PARA LA TABLA CIUDAD
        _pantalla.getTblCiudad().setModel(_distCiudad);
        _pantalla.getTblCiudad().addMouseListener(mnuDerecho);
        _pantalla.getScrCiudad().setVisible(false);
        _pantalla.getScrCiudad().addMouseListener(mnuDerecho);
        //MANEJO DE EVENTOS PARA LA TABLA SECTOR ECONOMICO
        _pantalla.getTblSectorEconomico().setModel(_distSEconomico);
        _pantalla.getTblSectorEconomico().addMouseListener(mnuDerecho);
        _pantalla.getScrSectEconom().setVisible(false);
        _pantalla.getScrSectEconom().addMouseListener(mnuDerecho);
        //MANEJO DE EVENTOS PARA LA TABLA OPERATORIA
        _pantalla.getTblOperatoria().setModel(_distOperatoria);
        _pantalla.getTblOperatoria().addMouseListener(mnuDerecho);
        _pantalla.getScrOperatoria().setVisible(false);
        _pantalla.getScrOperatoria().addMouseListener(mnuDerecho);

        _pantalla.getBtnViewDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                verTablas();
            }
        });

        _pantalla.getBtnDropDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ocultarTablas();
            }
        });

    }

    void pressOkButton() {
        try {
            _gestor.guardar();
            JOptionPane.showMessageDialog(_pantalla, "Operación Realizada con exito");
        } catch (BusinessOperationException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex, "Error de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    void pressCancelButton() {
        _pantalla.dispose();
        SubscriptorTotal.getInstance().remove(this);
    }

    void verTablas() {
        tablaOnTop++;
        switch (tablaOnTop) {
            case AÑO:
                _pantalla.getBtnDropDetails().setEnabled(false);
                break;
            case PROVINCIA:
                _pantalla.getBtnDropDetails().setEnabled(true);
                viewProvincia();
                break;
            case CIUDAD:
                viewCiudad();
                break;
            case SECTORECONOMICO:
                viewSectEconomico();
                break;
            case OPERATORIA:
                viewOperatoria();
                _pantalla.getBtnViewDetails().setEnabled(false);
                break;
            default:
                tablaOnTop--;
        }
        actualizarPorcentaje();
    }

    void ocultarTablas() {

        switch (tablaOnTop) {
            case AÑO:
                _pantalla.getBtnDropDetails().setEnabled(false);
                break;
            case PROVINCIA:
                dropProvincia();
                _pantalla.getBtnDropDetails().setEnabled(false);
                break;
            case CIUDAD:
                dropCiudad();
                break;
            case SECTORECONOMICO:
                dropSectEconomico();
                break;
            case OPERATORIA:
                dropOperatoria();
                _pantalla.getBtnViewDetails().setEnabled(true);
                break;
            default:
                if (tablaOnTop < AÑO) {
                    _pantalla.getBtnDropDetails().setEnabled(false);
                    tablaOnTop = AÑO + 1;
                } else if (tablaOnTop > OPERATORIA) {
                    _pantalla.getBtnViewDetails().setEnabled(true);
                    tablaOnTop = OPERATORIA + 1;
                }

        }
        tablaOnTop--;
        actualizarPorcentaje();
    }

    void viewProvincia() {
        _distProvincial.setList(_gestor.listarDistribucionProvincial(getAñoSeleccionado()));//LLAMAR AL GESTOR PARA QUE ME RECUPERE EL LISTADO
        _pantalla.getTblAños().setEnabled(false);
        _pantalla.getScrProvincia().setVisible(true);
        if (_distProvincial.getAllRow().isEmpty()) {
            ocultarTablas();
        }
    }

    void viewCiudad() {
        int rowIndex = _pantalla.getTblProvincia().getSelectedRow();
        if (rowIndex < 0) {
            rowIndex = 0;
            _pantalla.getTblProvincia().setRowSelectionInterval(0, 0);
        }
        _distCiudad.setList(_gestor.listarDistribucionCiudad(getAñoSeleccionado(), _distProvincial.getSelectedIndex(rowIndex)));//LLAMAR ACA TB AL GESTOR
        _pantalla.getTblProvincia().setEnabled(false);
        _pantalla.getScrCiudad().setVisible(true);
        if (_distCiudad.getAllRow().isEmpty()) {
            ocultarTablas();
        }
    }

    void viewSectEconomico() {
        int rowIndex = _pantalla.getTblCiudad().getSelectedRow();
        if (rowIndex < 0) {
            rowIndex = 0;
            _pantalla.getTblCiudad().setRowSelectionInterval(0, 0);
        }
        _distSEconomico.setList(_gestor.listarDistribucionSector(getAñoSeleccionado(), _distCiudad.getSelectedIndex(rowIndex)));//GESTOR
        if (_distSEconomico.getAllRow().isEmpty()) {
            ocultarTablas();
            return;
        }
        _pantalla.getTblCiudad().setEnabled(false);
        _pantalla.getScrSectEconom().setVisible(true);
    }

    void viewOperatoria() {
        int rowIndex = _pantalla.getTblSectorEconomico().getSelectedRow();
        if (rowIndex < 0) {
            rowIndex = 0;
            _pantalla.getTblSectorEconomico().setRowSelectionInterval(rowIndex, rowIndex);
        }
        _distOperatoria.setList(_gestor.listarDistribucionOperatoria(getAñoSeleccionado(), _distSEconomico.getSelectedIndex(rowIndex)));//GESTOR
        if (_distOperatoria.getAllRow().isEmpty()) {
            ocultarTablas();
            return;
        }
        _pantalla.getTblSectorEconomico().setEnabled(false);
        _pantalla.getScrOperatoria().setVisible(true);
    }

    void dropOperatoria() {
        _pantalla.getTblSectorEconomico().setEnabled(true);
        _pantalla.getScrOperatoria().setVisible(false);
    }

    void dropSectEconomico() {
        _pantalla.getTblCiudad().setEnabled(true);
        _pantalla.getScrSectEconom().setVisible(false);
    }

    void dropCiudad() {
        _pantalla.getTblProvincia().setEnabled(true);
        _pantalla.getScrCiudad().setVisible(false);
    }

    void dropProvincia() {
        _pantalla.getTblAños().setEnabled(true);
        _pantalla.getScrProvincia().setVisible(false);
    }

    private AñoPlan getAñoSeleccionado() {
        int idx = _pantalla.getTblAños().getSelectedRow();
        if (idx < 0) {
            idx = 0;
            _pantalla.getTblAños().setRowSelectionInterval(0, 0);
        }
        return _tablaAños.getSelectedIndex(idx);
    }

    public void actualizarPorcentaje() {
        switch (tablaOnTop) {
            case AÑO:
                colorTotal(100);
                break;
            case PROVINCIA:
                porcentajeProvincia();
                break;
            case CIUDAD:
                porcentajeCiudad();
                break;
            case SECTORECONOMICO:
                porcentajeSEconomico();
                break;
            case OPERATORIA:
                porcentajeOperatoria();
                break;
        }
    }

    private void colorTotal(double porcentaje) {
        porcentaje = viviendas.utiles.Utiles.round(porcentaje, 2);
        double restante = 100.00-porcentaje;
        _pantalla.getTxtTotal().setText(String.valueOf(porcentaje));
        _pantalla.getTxtRestante().setText(String.valueOf(restante));
        if (porcentaje != 100.00) {
            _pantalla.getTxtTotal().setForeground(Color.red);
            _pantalla.getTxtRestante().setForeground(Color.red);
            _pantalla.getBtnOk().setEnabled(false);
            if (tablaOnTop != AÑO) {
                _pantalla.getBtnDropDetails().setEnabled(false);
            }
            _pantalla.getBtnViewDetails().setEnabled(false);
        } else {
            _pantalla.getTxtTotal().setForeground(Color.BLUE);
            _pantalla.getTxtRestante().setForeground(Color.BLUE);
            _pantalla.getBtnOk().setEnabled(true);
            _pantalla.getBtnDropDetails().setEnabled(true);
            if (tablaOnTop != OPERATORIA) {
                _pantalla.getBtnViewDetails().setEnabled(true);
            }
        }
    }

    private void porcentajeProvincia() {
        double porcentaje = 0;
        for (DistribucionProvincial dProvincial : _distProvincial.getAllRow()) {
            porcentaje += dProvincial.getPorcentajeDistribucion();
        }
        colorTotal(porcentaje);
    }

    private void porcentajeCiudad() {
        double porcentaje = 0;
        for (DistribucionCiudad dCiudad : _distCiudad.getAllRow()) {
            porcentaje += dCiudad.getPorcentajeDistribucion();
        }
        colorTotal(porcentaje);
    }

    private void porcentajeSEconomico() {
        double porcentaje = 0;
        for (DistribucionSector dSector : _distSEconomico.getAllRow()) {
            porcentaje += dSector.getPorcentajeDistribucion();
        }
        colorTotal(porcentaje);
    }

    private void porcentajeOperatoria() {
        double porcentaje = 0;
        for (DistribucionOperatoria dOperatoria : _distOperatoria.getAllRow()) {
            porcentaje += dOperatoria.getPorcentajeDistribucion();
        }
        colorTotal(porcentaje);
    }

}

class MenuClickDerecho extends MouseAdapter {

    private JPopupMenu _menu;
    private JMenuItem _mnuAdd;
    private IUModificarPlan _pantalla;

    public MenuClickDerecho(final ctrlModificarPlan control, IUModificarPlan _pantalla) {
        this._pantalla = _pantalla;
        _menu = new JPopupMenu("Detalles");
        _mnuAdd = new JMenuItem("Mostrar Detalles");
        _mnuAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                control.verTablas();
            }
        });
        _menu.add(_mnuAdd);
        _mnuAdd = new JMenuItem("Ocultar Detalles");
        _mnuAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                control.ocultarTablas();
            }
        });
        _menu.add(_mnuAdd);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            _menu.show(_pantalla.getContenedor(), e.getX(), e.getY());
        }
    }
}
