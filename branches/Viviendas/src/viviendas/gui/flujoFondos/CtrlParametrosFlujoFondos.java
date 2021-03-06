/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.flujoFondos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.vivienda.AnioPlan;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.gui.dto.DtoParametrosFlujoFondo;
import viviendas.gui.models.combos.ModelComboAnioPlan;
import viviendas.gui.models.combos.ModelComboCiudad;
import viviendas.gui.models.combos.ModelComboOperatoria;
import viviendas.gui.models.combos.ModelComboPlan;
import viviendas.gui.models.combos.ModelComboProvincia;
import viviendas.gui.models.combos.ModelComboSectorEconomico;
import viviendas.gui.models.tables.ModelTableAño;
import viviendas.modulos.flujoFondos.GestorFlujoFondos;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author Maximiliano.
 */
public class CtrlParametrosFlujoFondos {
    private IUParametrosFlujoFondos _pantalla;
    private GestorFlujoFondos _gestor;
    private ModelComboPlan _modeloPlan;
    private ModelComboProvincia _modeloProvincia;
    private ModelComboCiudad _modeloCiudad;
    private ModelComboSectorEconomico _modeloSectorEconomico;
    private ModelComboOperatoria _modeloOperatoria;
    private DtoParametrosFlujoFondo _dto;

    public CtrlParametrosFlujoFondos(JDesktopPane desktop) {
        _dto = new DtoParametrosFlujoFondo();
        _gestor = new GestorFlujoFondos();
        _pantalla = new IUParametrosFlujoFondos();

        _modeloPlan = new ModelComboPlan(_gestor.obtenerPlanes(), "Seleccione un Plan");
        _pantalla.getCmbPlan().setModel(_modeloPlan);

        _pantalla.getCmbPlan().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cargarCombinaciones((Plan) _modeloPlan.getSelected());
            }
        });

        _pantalla.getCmbAnio().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(_pantalla.getCmbAnio().getSelectedIndex() == 0)
                    configurarComponentes(true, false, false, false, false);
                else
                    configurarComponentes(true, true, true, true, true);
            }
        });


        _pantalla.getCmbProvincias().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(_pantalla.getCmbProvincias().getSelectedIndex() == 0)
                    configurarComponentes(true, true, false, false, false);
                else
                    configurarComponentes(true, true, true, true, true);
            }
        });

        _pantalla.getCmbCiudades().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(_pantalla.getCmbCiudades().getSelectedIndex() == 0)
                    configurarComponentes(true, true, true, false, false);
                else
                    configurarComponentes(true, true, true, true, true);
            }
        });

        _pantalla.getCmbSectorEconomico().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(_pantalla.getCmbSectorEconomico().getSelectedIndex() == 0)
                    configurarComponentes(true, true, true, true, false);
                else
                    configurarComponentes(true, true, true, true, true);
            }
        });

        _pantalla.getBtnAceptar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });

        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        desktop.add(_pantalla);
        _pantalla.setVisible(true);
    }

    private void guardar(){
        //datos.
        if(_pantalla.getTxtTNA().getText().equals("")){
            JOptionPane.showMessageDialog(_pantalla, "El campo TNA es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if(Double.valueOf(_pantalla.getTxtTNA().getText()).doubleValue() == 0)
                _dto.setTna(0d);
            else
                _dto.setTna(Double.valueOf(_pantalla.getTxtTNA().getText())/100);
        }

        if(_pantalla.getTxtGastosAdministrativos().getText().equals("")){
            JOptionPane.showMessageDialog(_pantalla, "El campo Gastos Administrativo es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if(Double.valueOf(_pantalla.getTxtGastosAdministrativos().getText()).doubleValue() == 0)
                _dto.setGastosAdministrativos(0d);
            else
                _dto.setGastosAdministrativos(Double.valueOf(_pantalla.getTxtGastosAdministrativos().getText())/100);
        }

        if(_pantalla.getTxtComsionOtorgamiento().getText().equals("")){
            JOptionPane.showMessageDialog(_pantalla, "El campo Comisión de Otorgamiento es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if(Double.valueOf(_pantalla.getTxtComsionOtorgamiento().getText()).doubleValue() == 0)
                _dto.setComisionOtorgamiento(0d);
            else
                _dto.setComisionOtorgamiento(Double.valueOf(_pantalla.getTxtComsionOtorgamiento().getText())/100);
        }

        if(_pantalla.getTxtMomentoOtorg().getText().equals("")){
            JOptionPane.showMessageDialog(_pantalla, "El campo Momento de otorgamiento es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            _dto.setMomentoOtorgamiento(Integer.valueOf(_pantalla.getTxtMomentoOtorg().getText()));
        }

        if(_pantalla.getTxtPlazoGracia().getText().equals("")){
            JOptionPane.showMessageDialog(_pantalla, "El campo Plazo de Gracia es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            _dto.setPlazoGracia(Integer.valueOf(_pantalla.getTxtPlazoGracia().getText()));
        }

        if(_pantalla.getTxtPerdidaIncobrables().getText().equals("")){
            JOptionPane.showMessageDialog(_pantalla, "El campo %Perdida Incobrables es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            _dto.setPerdidaIncobrables(Double.valueOf(_pantalla.getTxtPerdidaIncobrables().getText())/100);
        }

        if(_pantalla.getTxtCantAnioTitulos().getText().equals("")){
            JOptionPane.showMessageDialog(_pantalla, "El campo Cant. Años en los parámetros de títulos es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            _dto.setCantAniosTitulos(Integer.valueOf(_pantalla.getTxtCantAnioTitulos().getText()));
        }

        if(_pantalla.getTxtTnaTitulos().getText().equals("")){
            JOptionPane.showMessageDialog(_pantalla, "El campo %Interes TNA en los parámetros de títulos es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            _dto.setTnaTitulos(Double.valueOf(_pantalla.getTxtTnaTitulos().getText())/100);
        }

        //criterios.
        if(_pantalla.getCmbPlan().getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(_pantalla, "El plan es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            _dto.setPlan( ((ModelComboPlan) _pantalla.getCmbPlan().getModel()).getSelected());
        }

        if(_pantalla.getCmbAnio().getSelectedIndex() == 0){
            _dto.setAnioPlan(null);
        }
        else{
            _dto.setAnioPlan( ((ModelComboAnioPlan) _pantalla.getCmbAnio().getModel()).getSelected());
        }

        if(_pantalla.getCmbProvincias().getSelectedIndex() == 0)
            _dto.setProvincia(null);
        else
            _dto.setProvincia(((ModelComboProvincia) _pantalla.getCmbProvincias().getModel()).getSelected());

        if(_pantalla.getCmbCiudades().getSelectedIndex() == 0)
            _dto.setCiudad(null);
        else
            _dto.setCiudad(((ModelComboCiudad)_pantalla.getCmbCiudades().getModel()).getSelected());

        if(_pantalla.getCmbSectorEconomico().getSelectedIndex() == 0)
            _dto.setSectorEconomico(null);
        else
            _dto.setSectorEconomico(((ModelComboSectorEconomico) _pantalla.getCmbSectorEconomico().getModel()).getSelected());

        if(_pantalla.getCmbOperatorias().getSelectedIndex() == 0)
            _dto.setOperatoria(null);
        else
            _dto.setOperatoria(((ModelComboOperatoria) _pantalla.getCmbOperatorias().getModel()).getSelected());

        try {
            System.out.println("TNA: " + _dto.getTna());
            System.out.println("Gastos Administrativos: " + _dto.getGastosAdministrativos());
            System.out.println("Comisión de otorgamiento: " + _dto.getComisionOtorgamiento());
            _gestor.guardar(_dto);
            JOptionPane.showMessageDialog(_pantalla, "Valores almacenados", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (BusinessOperationException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(_pantalla, "error al guardar datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelar(){
        _pantalla.hide();
        _pantalla.dispose();
    }

    private void configurarComponentes(boolean anio, boolean provincia, boolean ciudad, boolean sectorEconomico, boolean operatoria) {
        _pantalla.getCmbAnio().setEnabled(anio);
        _pantalla.getCmbProvincias().setEnabled(provincia);
        _pantalla.getCmbCiudades().setEnabled(ciudad);
        _pantalla.getCmbSectorEconomico().setEnabled(sectorEconomico);
        _pantalla.getCmbOperatorias().setEnabled(operatoria);
    }

    private String convertirEnProcentaje() {
        Double valor = Double.valueOf(_pantalla.getTxtTNA().getText());
        valor = valor / 100;
        return String.valueOf(valor);
    }

    private void cargarCombinaciones(Plan plan){
        //_gestor.setPlan(((ModelComboPlan) _pantalla.getComPlan().getModel()).getSelected());
        List<AnioPlan> listaAnios = _gestor.obtenerAniosPlan(plan);
        //Collections.sort(listaAnios, new ComparadorAñoPlan());
        _pantalla.getCmbAnio().setModel(new ModelComboAnioPlan(listaAnios, "Todos"));
        List<Ciudad> listaCiudad = _gestor.obtenerCiudades(plan);
        //Collections.sort(listaCiudad, new ComparadorCiudad());
        _pantalla.getCmbCiudades().setModel(new ModelComboCiudad(listaCiudad, "Todos"));
        List<Operatoria> listaOperatoria = _gestor.obtenerOperatorias(plan);
        //Collections.sort(listaOperatoria, new ComparadorOperatoria());
        _pantalla.getCmbOperatorias().setModel(new ModelComboOperatoria(listaOperatoria, "Todos"));
        List<Provincia> listaProvincia = _gestor.obtenerProvincias(plan);
        //Collections.sort(listaProvincia, new ComparadorProvincia());
        _pantalla.getCmbProvincias().setModel(new ModelComboProvincia(listaProvincia, "Todos"));
        List<SectorEconomico> listaSector = _gestor.obtenerSectoresEconomicos(plan);
        //Collections.sort(listaSector, new ComparadorSectorEconomico());
        _pantalla.getCmbSectorEconomico().setModel(new ModelComboSectorEconomico(listaSector, "Todos"));
        //_pantalla.getCmbProvincias().setEnabled(true);
        //_pantalla.getCmbCiudades().setEnabled(true);
        //_pantalla.getCmbSectorEconomico().setEnabled(true);
        //_pantalla.getCmbOperatorias().setEnabled(true);
        //_pantalla.getCmbAnio().setEnabled(true);
        //_pantalla.getBtnAceptar().setEnabled(true);

    }
}