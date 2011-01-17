/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.flujoFondos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import modeloCalculo.ModelDinamicTable;
import viviendas.gui.dto.DtoParametrosFlujoFondo;
import viviendas.gui.models.combos.ModelComboCiudad;
import viviendas.gui.models.combos.ModelComboOperatoria;
import viviendas.gui.models.combos.ModelComboPlan;
import viviendas.gui.models.combos.ModelComboProvincia;
import viviendas.gui.models.combos.ModelComboSectorEconomico;
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

        _modeloProvincia = new ModelComboProvincia(_gestor.obtenerProvincias(), "Todas");
        _pantalla.getCmbProvincias().setModel(_modeloProvincia);

        _modeloCiudad = new ModelComboCiudad(_gestor.obtenerCiudades(), "Todas");
        _pantalla.getCmbCiudades().setModel(_modeloCiudad);

        _modeloSectorEconomico = new ModelComboSectorEconomico(_gestor.obtenerSectoresEconomicos(), "Todas");
        _pantalla.getCmbSectorEconomico().setModel(_modeloSectorEconomico);

        _modeloOperatoria = new ModelComboOperatoria(_gestor.obtenerOperatorias(), "Todas");
        _pantalla.getCmbOperatorias().setModel(_modeloOperatoria);

        _pantalla.getCmbProvincias().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(_pantalla.getCmbProvincias().getSelectedIndex() == 0)
                    configurarComponentes(true, false, false, false);
                else
                    configurarComponentes(true, true, true, true);
            }
        });

        _pantalla.getCmbCiudades().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(_pantalla.getCmbCiudades().getSelectedIndex() == 0)
                    configurarComponentes(true, true, false, false);
                else
                    configurarComponentes(true, true, true, true);
            }
        });

        _pantalla.getCmbSectorEconomico().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(_pantalla.getCmbSectorEconomico().getSelectedIndex() == 0)
                    configurarComponentes(true, true, true, false);
                else
                    configurarComponentes(true, true, true, true);
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
        _dto.setDevCredTna(Double.valueOf(_pantalla.getTxtTNADevolCred().getText()));
        _dto.setDevCredGastosOtorgamiento(Double.valueOf(_pantalla.getTxtGastosOtorgamiento().getText()));
        _dto.setDevCredGastosAdministrativos(Double.valueOf(_pantalla.getTxtGastosAdministrativos().getText()));
        _dto.setTitilosTna(Double.valueOf(_pantalla.getTxtTNATitulos().getText()));
        _dto.setTitulosAniosDevolucion(Integer.valueOf(_pantalla.getTxtAnioDevolucion().getText()));
        _dto.setPerdidaIncobrables(Double.valueOf(_pantalla.getTxtPerdidaIncobrables().getText()));
        //criterios.
        if(_pantalla.getCmbPlan().getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(_pantalla, "El plan es obligatorio.", "Viviendas", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            _dto.setPlan( ((ModelComboPlan) _pantalla.getCmbPlan().getModel()).getSelected());
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

    private void configurarComponentes(boolean provincia, boolean ciudad, boolean sectorEconomico, boolean operatoria) {
        _pantalla.getCmbProvincias().setEnabled(provincia);
        _pantalla.getCmbCiudades().setEnabled(ciudad);
        _pantalla.getCmbSectorEconomico().setEnabled(sectorEconomico);
        _pantalla.getCmbOperatorias().setEnabled(operatoria);
    }


}