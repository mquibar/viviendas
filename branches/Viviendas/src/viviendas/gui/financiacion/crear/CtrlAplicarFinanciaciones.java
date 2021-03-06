package viviendas.gui.financiacion.crear;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import viviendas.entidades.flujo.Financiacion;
import viviendas.entidades.vivienda.AnioPlan;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.gui.financiacion.modificar.CtrlModificarFinanciacion;
import viviendas.gui.models.combos.ModelComboAnioPlan;
import viviendas.gui.models.combos.ModelComboCiudad;
import viviendas.gui.models.combos.ModelComboOperatoria;
import viviendas.gui.models.combos.ModelComboPlan;
import viviendas.gui.models.combos.ModelComboProvincia;
import viviendas.gui.models.combos.ModelComboSectorEconomico;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.modulos.financiacion.crear.GestorCrearFinanciacion;

public class CtrlAplicarFinanciaciones {

    private IUAplicarFinanciaciones _pantalla;
    private GestorCrearFinanciacion _gestor;
    private final CtrlModificarFinanciacion _ctrlModificarFinanciacion;

    public CtrlAplicarFinanciaciones(CtrlModificarFinanciacion _ctrlModificarFinanciacion, Financiacion financiacion) {
        _pantalla = new IUAplicarFinanciaciones();
        _gestor = new GestorCrearFinanciacion(financiacion);
        this._ctrlModificarFinanciacion = _ctrlModificarFinanciacion;
        _ctrlModificarFinanciacion.ocultar(true);

        _pantalla.getComPlan().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                seleccionaPlan();
            }
        });
        _pantalla.getBtnAceptar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaAceptar();
            }
        });
        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaCancelar();
            }
        });
        _pantalla.getComPlan().setModel(new ModelComboPlan(_gestor.obtenerPlanes(), null));
        _pantalla.setVisible(true);
        _pantalla.toFront();
        CtrlPrincipal.getInstance().getDesktopPane().add(_pantalla);
        _pantalla.addInternalFrameListener(new InternalFrameAdapter() {

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                presionaCancelar();
            }
        });
        iniciarPantalla();
    }

    private void seleccionaPlan() {
        if (((ModelComboPlan) _pantalla.getComPlan().getModel()).getSelected() == null) {
            iniciarPantalla();
            return;
        }
        _gestor.setPlan(((ModelComboPlan) _pantalla.getComPlan().getModel()).getSelected());
        List<AnioPlan> listaAños = _gestor.obtenerAñosPlan();
        Collections.sort(listaAños, new ComparadorAñoPlan());
        _pantalla.getComAñoPlan().setModel(new ModelComboAnioPlan(listaAños, "Todos"));
        List<Ciudad> listaCiudad = _gestor.obtenerCiudades();
        Collections.sort(listaCiudad, new ComparadorCiudad());
        _pantalla.getComCiudad().setModel(new ModelComboCiudad(listaCiudad, "Todos"));
        List<Operatoria> listaOperatoria = _gestor.obtenerOperatorias();
        Collections.sort(listaOperatoria, new ComparadorOperatoria());
        _pantalla.getComOperatoria().setModel(new ModelComboOperatoria(listaOperatoria, "Todos"));
        List<Provincia> listaProvincia = _gestor.obtenerProvincias();
        Collections.sort(listaProvincia, new ComparadorProvincia());
        _pantalla.getComProvincia().setModel(new ModelComboProvincia(listaProvincia, "Todos"));
        List<SectorEconomico> listaSector = _gestor.obtenerSectoresEconomicos();
        Collections.sort(listaSector, new ComparadorSectorEconomico());
        _pantalla.getComSector().setModel(new ModelComboSectorEconomico(listaSector, "Todos"));
        _pantalla.getComProvincia().setEnabled(true);
        _pantalla.getComCiudad().setEnabled(true);
        _pantalla.getComSector().setEnabled(true);
        _pantalla.getComOperatoria().setEnabled(true);
        _pantalla.getComAñoPlan().setEnabled(true);
        _pantalla.getBtnAceptar().setEnabled(true);
    }

    private void presionaAceptar() {
        Integer cantidad = _gestor.getCantidadRegistros(
                ((ModelComboAnioPlan) _pantalla.getComAñoPlan().getModel()).getSelected(),
                ((ModelComboProvincia) _pantalla.getComProvincia().getModel()).getSelected(),
                ((ModelComboCiudad) _pantalla.getComCiudad().getModel()).getSelected(),
                ((ModelComboSectorEconomico) _pantalla.getComSector().getModel()).getSelected(),
                ((ModelComboOperatoria) _pantalla.getComOperatoria().getModel()).getSelected());
        int rta;
        if (cantidad > 40) {
            rta = JOptionPane.showConfirmDialog(_pantalla, "Se van a modificar " + cantidad + " registros, \nEsta operación puede demorar ¿Desea Continuar?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        } else {
            rta = JOptionPane.OK_OPTION;
        }
        if (rta == JOptionPane.OK_OPTION) {
            int eliminarFinanciaciones;
            if (_gestor.tieneFinanciaciones()) {
                eliminarFinanciaciones = JOptionPane.showConfirmDialog(_pantalla, "Existen financiaciones previamente creadas para la combinación seleccionada.\n¿Desea reemplazarlas?", "", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            }else{
                eliminarFinanciaciones = JOptionPane.CANCEL_OPTION;
            }
            _gestor.aplicarFinanciacion(eliminarFinanciaciones == JOptionPane.OK_OPTION);
            _pantalla.getBtnAceptar().setEnabled(false);
            JOptionPane.showMessageDialog(_pantalla, "Financiaciones creadas", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void presionaCancelar() {
        _pantalla.dispose();
        _ctrlModificarFinanciacion.ocultar(false);
        _ctrlModificarFinanciacion.iniciar(_gestor.getFinanciacion().getDistribucionOperatoria());
    }

    private void iniciarPantalla() {
        _pantalla.getComAñoPlan().setEnabled(false);
        _pantalla.getComCiudad().setEnabled(false);
        _pantalla.getComOperatoria().setEnabled(false);
        _pantalla.getComPlan().setEnabled(true);
        _pantalla.getComProvincia().setEnabled(false);
        _pantalla.getComSector().setEnabled(false);
        _pantalla.getBtnAceptar().setEnabled(false);
    }

    class ComparadorAñoPlan implements Comparator<AnioPlan> {

        public int compare(AnioPlan o1, AnioPlan o2) {
            return o1.getAnio().compareTo(o2.getAnio());
        }
    }

    class ComparadorProvincia implements Comparator<Provincia> {

        public int compare(Provincia o1, Provincia o2) {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    }

    class ComparadorCiudad implements Comparator<Ciudad> {

        public int compare(Ciudad o1, Ciudad o2) {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    }

    class ComparadorSectorEconomico implements Comparator<SectorEconomico> {

        public int compare(SectorEconomico o1, SectorEconomico o2) {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    }

    class ComparadorOperatoria implements Comparator<Operatoria> {

        public int compare(Operatoria o1, Operatoria o2) {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    }
}
