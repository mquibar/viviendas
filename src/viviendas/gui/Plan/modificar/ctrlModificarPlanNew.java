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
import java.util.List;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import viviendas.entidades.vivienda.AnioPlan;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.gui.tool.ICalculable;
import viviendas.gui.models.tables.ModelTableAño;
import viviendas.gui.models.tables.ModelTableDistribucionCiudad;
import viviendas.gui.models.tables.ModelTableDistribucionOperatoria;
import viviendas.gui.models.tables.ModelTableDistribucionProvincial;
import viviendas.gui.models.tables.ModelTableDistribucionSectorEconomico;
import viviendas.gui.models.tables.ModelTableProvincia;
import viviendas.gui.models.tables.ModeloTablaOperatoria;
import viviendas.gui.models.tables.ModeloTablaSectorEconomico;
import viviendas.gui.models.tables.ModeloTableCiudad;
import viviendas.gui.tool.SubscriptorTotal;
import viviendas.modulos.Plan.modificar.GestorModificarPlan;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author desarrollo
 */
public class ctrlModificarPlanNew implements ICalculable {

    private IUModificarPlanNew _pantalla;
    private PanelTablasPlan _panel;
    private ModelTableAño _tablaAños;
    private ModelTableDistribucionProvincial _distProvincial;
    private ModelTableDistribucionCiudad _distCiudad;
    private ModelTableDistribucionSectorEconomico _distSEconomico;
    private ModelTableDistribucionOperatoria _distOperatoria;
    private ModelTableProvincia _provincias;
    private ModeloTableCiudad _ciudades;
    private ModeloTablaSectorEconomico _sectores;
    private ModeloTablaOperatoria _operatorias;
    private GestorModificarPlan _gestor;
    private final int AÑO = 0;
    private final int PROVINCIA = 1;
    private final int CIUDAD = 2;
    private final int SECTORECONOMICO = 3;
    private final int OPERATORIA = 4;
    private int tablaOnTop = AÑO;
    private IUSeleccionRestantes _seleccion;
    private boolean activo = true;
    private boolean estadoBtnDrop;
    private boolean estadoBtnView;
    private Map<javax.swing.JButton, Boolean> _botonera;

    public ctrlModificarPlanNew(GestorModificarPlan gestor, IUModificarPlanNew pantalla) {
        _gestor = gestor;
        _pantalla = pantalla;
        _panel = new PanelTablasPlan();
        _tablaAños = new ModelTableAño(null);
        _distProvincial = new ModelTableDistribucionProvincial(null);
        _distCiudad = new ModelTableDistribucionCiudad(null);
        _distSEconomico = new ModelTableDistribucionSectorEconomico(null);
        _distOperatoria = new ModelTableDistribucionOperatoria(null);
        _seleccion = new IUSeleccionRestantes(null, true);
        _botonera = new java.util.HashMap<javax.swing.JButton, Boolean>();
        cargarPantalla();
        SubscriptorTotal.getInstance().añadir(this);
    }

    final void cargarPantalla() {
        Plan plan = _gestor.getPlan();
        MenuClickDerechoNew mnuDerecho = new MenuClickDerechoNew(this, _pantalla);
        _pantalla.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if (activo) {
                    pressCancelButton();
                }
            }
        });
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    pressOkButton();
                }
            }
        });
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    pressCancelButton();
                }
            }
        });
        _pantalla.getBtnDel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    pressDelButton();
                }
            }
        });

        _pantalla.getBtnAdd().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    pressAddButton();
                }
            }
        });

        _pantalla.getBtnAddFinanciacion().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressAddFinanciacionButton();
            }
        });
        _pantalla.getBtnAddFinanciacion().setEnabled(false);

        //DATOS DEL PLAN
        _pantalla.getTxtNombre().setText(plan.getNombre());
        _pantalla.getTxtTipo().setText(plan.getTipoPlan().getNombre());
        _pantalla.getTxtAños().setText(plan.getAniosPlan().toString());
        _pantalla.getTxtTotViviendas().setText(plan.getNumeroViviendas().toString());

        _tablaAños.setList(_gestor.getPlan().getListaAnioPlan());
        //MANEJO DE EVENTOS PARA LA TABLA AÑO
        _panel.getTblAños().setModel(_tablaAños);
        _panel.getTblAños().addMouseListener(mnuDerecho);
        _panel.getScrAño().addMouseListener(mnuDerecho);
        _panel.getTblAños().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //MANEJO DE EVENTOS PARA LA TABLA PROVINCIA
        _panel.getTblProvincia().setModel(_distProvincial);
        _panel.getTblProvincia().addMouseListener(mnuDerecho);
        _panel.getScrProvincia().setVisible(false);
        _panel.getScrProvincia().addMouseListener(mnuDerecho);
        _panel.getTblProvincia().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //MANEJO DE EVENTOS PARA LA TABLA CIUDAD
        _panel.getTblCiudad().setModel(_distCiudad);
        _panel.getTblCiudad().addMouseListener(mnuDerecho);
        _panel.getScrCiudad().setVisible(false);
        _panel.getScrCiudad().addMouseListener(mnuDerecho);
        _panel.getTblCiudad().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //MANEJO DE EVENTOS PARA LA TABLA SECTOR ECONOMICO
        _panel.getTblSectorEconomico().setModel(_distSEconomico);
        _panel.getTblSectorEconomico().addMouseListener(mnuDerecho);
        _panel.getScrSectEconom().setVisible(false);
        _panel.getScrSectEconom().addMouseListener(mnuDerecho);
        _panel.getTblSectorEconomico().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //MANEJO DE EVENTOS PARA LA TABLA OPERATORIA
        _panel.getTblOperatoria().setModel(_distOperatoria);
        _panel.getTblOperatoria().addMouseListener(mnuDerecho);
        _panel.getScrOperatoria().setVisible(false);
        _panel.getScrOperatoria().addMouseListener(mnuDerecho);
        _panel.getTblOperatoria().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        _pantalla.getBtnViewDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    verTablas();
                }
            }
        });

        _pantalla.getBtnDropDetails().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    ocultarTablas();
                }
            }
        });
        _seleccion.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    pressCancelButtonSeleccion();
                }
            }
        });
        _seleccion.getBtnAccept().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (activo) {
                    pressOkButtonSeleccion();
                }
            }
        });
        _seleccion.getTblSeleccion().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        _panel.setVisible(true);

    }

    public JPanel getPanel() {
        return _panel;
    }

    public void activar() {
        if (activo == false) {
            activo = true;
            _pantalla.getBtnDropDetails().setEnabled(_botonera.get(_pantalla.getBtnDropDetails()));
            _pantalla.getBtnViewDetails().setEnabled(_botonera.get(_pantalla.getBtnViewDetails()));
            _pantalla.getBtnAdd().setEnabled(_botonera.get(_pantalla.getBtnAdd()));
            _pantalla.getBtnDel().setEnabled(_botonera.get(_pantalla.getBtnDel()));
            _pantalla.getBtnAddFinanciacion().setEnabled(_botonera.get(_pantalla.getBtnAddFinanciacion()));
        }
    }

    public void desactivar() {
        if (activo == true) {
            activo = false;
            _botonera.put(_pantalla.getBtnDropDetails(), _pantalla.getBtnDropDetails().isEnabled());
            _botonera.put(_pantalla.getBtnViewDetails(), _pantalla.getBtnViewDetails().isEnabled());
            _botonera.put(_pantalla.getBtnAdd(), _pantalla.getBtnAdd().isEnabled());
            _botonera.put(_pantalla.getBtnDel(), _pantalla.getBtnDel().isEnabled());
            _botonera.put(_pantalla.getBtnAddFinanciacion(), _pantalla.getBtnAddFinanciacion().isEnabled());
        }
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
                verTablas();
                return;
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
                _pantalla.getBtnAddFinanciacion().setEnabled(true);
                break;
            default:
                if (tablaOnTop <= AÑO) {
                    verTablas();
                    return;
                } else if (tablaOnTop > OPERATORIA) {
                    _pantalla.getBtnViewDetails().setEnabled(true);
                    tablaOnTop = OPERATORIA;
                }
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
                _pantalla.getBtnAddFinanciacion().setEnabled(false);
                break;
            default:
                if (tablaOnTop <= AÑO) {
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
        _panel.getTblAños().setEnabled(false);
        _panel.getScrProvincia().setVisible(true);
        _pantalla.getLblAño().setText(getAñoSeleccionado().toString());
        if (_distProvincial.getAllRow().isEmpty()) {
            _pantalla.getBtnViewDetails();
        }
    }

    void viewCiudad() {
        _distCiudad.setList(_gestor.listarDistribucionCiudad(getAñoSeleccionado(), getDistProvincialSeleccionada()));//LLAMAR ACA TB AL GESTOR
        _panel.getTblProvincia().setEnabled(false);
        _panel.getScrCiudad().setVisible(true);
        _pantalla.getLblProvincia().setText(getDistProvincialSeleccionada().getProvincia().getNombre());
        if (_distCiudad.getAllRow().isEmpty()) {
            _pantalla.getBtnViewDetails();
        } else {
            _panel.getTblCiudad().setRowSelectionInterval(0, 0);
        }
    }

    void viewSectEconomico() {
        _distSEconomico.setList(_gestor.listarDistribucionSector(getAñoSeleccionado(), getDistCiudadSeleccionada()));//GESTOR
        if (_distSEconomico.getAllRow().isEmpty()) {
            _pantalla.getBtnViewDetails();
        } else {
            _panel.getTblSectorEconomico().setRowSelectionInterval(0, 0);
        }
        _panel.getTblCiudad().setEnabled(false);
        _panel.getScrSectEconom().setVisible(true);
        _pantalla.getLblCiudad().setText(getDistCiudadSeleccionada().getCuidad().getNombre());
    }

    void viewOperatoria() {

        _distOperatoria.setList(_gestor.listarDistribucionOperatoria(getAñoSeleccionado(), getDistSectorSeleccionado()));//GESTOR
        if (_distOperatoria.getAllRow().isEmpty()) {
            _pantalla.getBtnViewDetails();
        } else {
            _panel.getTblOperatoria().setRowSelectionInterval(0, 0);
        }
        _panel.getTblSectorEconomico().setEnabled(false);
        _panel.getScrOperatoria().setVisible(true);
        _pantalla.getLblSector().setText(getDistSectorSeleccionado().getSectorEconomico().getNombre());
    }

    void dropOperatoria() {
        _panel.getTblSectorEconomico().setEnabled(true);
        _panel.getScrOperatoria().setVisible(false);
        _pantalla.getLblOperatoria().setText("");
    }

    void dropSectEconomico() {
        _panel.getTblCiudad().setEnabled(true);
        _panel.getScrSectEconom().setVisible(false);
        _pantalla.getLblSector().setText("");
    }

    void dropCiudad() {
        _panel.getTblProvincia().setEnabled(true);
        _panel.getScrCiudad().setVisible(false);
        _pantalla.getLblCiudad().setText("");
    }

    void dropProvincia() {
        _panel.getTblAños().setEnabled(true);
        _panel.getScrProvincia().setVisible(false);
        _pantalla.getLblProvincia().setText("");
    }

    AnioPlan getAñoSeleccionado() {
        int idx = _panel.getTblAños().getSelectedRow();
        if (idx < 0) {
            idx = 0;
            _panel.getTblAños().setRowSelectionInterval(0, 0);
        }
        return _tablaAños.getSelectedIndex(idx);
    }

    DistribucionProvincial getDistProvincialSeleccionada() {
        int rowIndex = _panel.getTblProvincia().getSelectedRow();
        if (rowIndex < 0) {
            rowIndex = 0;
            _panel.getTblProvincia().setRowSelectionInterval(rowIndex, rowIndex);
        }
        return _distProvincial.getSelectedIndex(rowIndex);
    }

    DistribucionCiudad getDistCiudadSeleccionada() {
        int rowIndex = _panel.getTblCiudad().getSelectedRow();
        if (rowIndex < 0) {
            rowIndex = 0;
            _panel.getTblCiudad().setRowSelectionInterval(rowIndex, rowIndex);
        }
        return _distCiudad.getSelectedIndex(rowIndex);
    }

    DistribucionSector getDistSectorSeleccionado() {
        int rowIndex = _panel.getTblSectorEconomico().getSelectedRow();
        if (rowIndex < 0) {
            rowIndex = 0;
            _panel.getTblSectorEconomico().setRowSelectionInterval(rowIndex, rowIndex);
        }
        return _distSEconomico.getSelectedIndex(rowIndex);
    }

    DistribucionOperatoria getDistOperatoriaSeleccionada() {
        int rowIndex = _panel.getTblOperatoria().getSelectedRow();
        if (rowIndex < 0) {
            rowIndex = 0;
            _panel.getTblOperatoria().setRowSelectionInterval(rowIndex, rowIndex);
        }
        return _distOperatoria.getSelectedIndex(rowIndex);
    }

    public void actualizarPorcentaje() {
        if (!activo) {
            return;
        }
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
        double restante = 100.00 - porcentaje;
        restante = viviendas.utiles.Utiles.round(restante, 2);
        _pantalla.getTxtTotal().setText(String.valueOf(porcentaje));
        _pantalla.getTxtRestante().setText(String.valueOf(restante));
        if (porcentaje != 100.00) {
            _pantalla.getTxtTotal().setForeground(Color.red);
            _pantalla.getTxtRestante().setForeground(Color.red);
            _pantalla.getBtnOk().setEnabled(false);
            _pantalla.getBtnDropDetails().setEnabled(false);
            _pantalla.getBtnViewDetails().setEnabled(false);
            _pantalla.getBtnAddFinanciacion().setEnabled(false);
        } else {
            _pantalla.getTxtTotal().setForeground(Color.BLUE);
            _pantalla.getTxtRestante().setForeground(Color.BLUE);
            _pantalla.getBtnOk().setEnabled(true);
            _pantalla.getBtnDropDetails().setEnabled(true);
            if (tablaOnTop != OPERATORIA) {
                _pantalla.getBtnViewDetails().setEnabled(true);
            } else {
                _pantalla.getBtnAddFinanciacion().setEnabled(true);
            }
            if (tablaOnTop != AÑO) {
                _pantalla.getBtnDropDetails().setEnabled(true);
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

    void pressDelButton() {
        int rowIndex = 0;
        switch (tablaOnTop) {
            case PROVINCIA:
                _gestor.removeDistribucion(getAñoSeleccionado(), getDistProvincialSeleccionada());
                _distProvincial.fireTableDataChanged();
                break;
            case CIUDAD:
                _gestor.removeDistribucion(getAñoSeleccionado(), getDistCiudadSeleccionada());
                viewCiudad();
                break;
            case SECTORECONOMICO:
                _gestor.removeDistribucion(getAñoSeleccionado(), getDistSectorSeleccionado());
                viewSectEconomico();
                break;
            case OPERATORIA:
                _gestor.removeDistribucion(getAñoSeleccionado(), getDistOperatoriaSeleccionada());
                viewOperatoria();
                break;
        }
    }

    void pressAddButton() {
        switch (tablaOnTop) {
            case PROVINCIA:
                _provincias = new TablaProvinciasNew(_gestor.provinciasNoAsignadas(getAñoSeleccionado()));
                _seleccion.getTblSeleccion().setModel(_provincias);
                break;
            case CIUDAD:
                _ciudades = new TablaCiudadNew(_gestor.ciudadesNoAsignadas(getAñoSeleccionado(), _distCiudad.getAllRow()));
                _seleccion.getTblSeleccion().setModel(_ciudades);
                break;
            case SECTORECONOMICO:
                _sectores = new TablaSectorNew(_gestor.sectoresNoAsignados(getAñoSeleccionado(), _distSEconomico.getAllRow()));
                _seleccion.getTblSeleccion().setModel(_sectores);
            case OPERATORIA:
                _operatorias = new TablaOperatoriaNew(_gestor.operatoriasNoAsignadas(getAñoSeleccionado(), _distOperatoria.getAllRow()));
                _seleccion.getTblSeleccion().setModel(_operatorias);

        }
        if (_seleccion.getTblSeleccion().getRowCount() <= 0) {
            _seleccion.getBtnAccept().setEnabled(false);
        } else {
            _seleccion.getBtnAccept().setEnabled(true);
            _seleccion.getTblSeleccion().setRowSelectionInterval(0, 0);
        }
        _seleccion.setVisible(true);
    }

    void pressCancelButtonSeleccion() {
        _seleccion.setVisible(false);
    }

    void pressOkButtonSeleccion() {
        int rowIndex = _seleccion.getTblSeleccion().getSelectedRow();
        switch (tablaOnTop) {
            case PROVINCIA:
                _distProvincial.addRow(_gestor.addDistribucion(getAñoSeleccionado(), _provincias.getSelectedIndex(rowIndex)));
                break;
            case CIUDAD:
                _distCiudad.addRow(_gestor.addDistribucion(getAñoSeleccionado(), _ciudades.getSelectedIndex(rowIndex), getDistProvincialSeleccionada()));
                break;
        }
        _seleccion.setVisible(false);
    }

    void pressAddFinanciacionButton() {
        _pantalla.setEnabled(false);
    }

    public void desbloquear() {
        _pantalla.setEnabled(true);
    }
}

class MenuClickDerechoNew extends MouseAdapter {

    private JPopupMenu _menu;
    private JMenuItem _mnuAdd;
    private IUModificarPlanNew _pantalla;

    public MenuClickDerechoNew(final ctrlModificarPlanNew control, IUModificarPlanNew _pantalla) {
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
            _menu.show(_pantalla.getPnlCentral(), e.getX(), e.getY());
        }
    }
}

class TablaProvinciasNew extends ModelTableProvincia {

    public TablaProvinciasNew(List<Provincia> provincias) {
        super(provincias);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
}

class TablaCiudadNew extends ModeloTableCiudad {

    public TablaCiudadNew(List<Ciudad> lista) {
        super(lista);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
}

class TablaSectorNew extends ModeloTablaSectorEconomico {

    public TablaSectorNew(List<SectorEconomico> lista) {
        super(lista);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
}

class TablaOperatoriaNew extends ModeloTablaOperatoria {

    public TablaOperatoriaNew(List<Operatoria> lista) {
        super(lista);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
}
