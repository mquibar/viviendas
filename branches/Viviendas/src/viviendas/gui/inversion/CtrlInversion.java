/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.inversion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.flujo.ValorInversion;
import viviendas.gui.dto.DtoInversion;
import viviendas.gui.models.combos.ModelComboCiudad;
import viviendas.gui.models.tables.ModelTableValoresInversion;
import viviendas.modulos.inversion.GestorInversion;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author Maximiliano.
 */
public class CtrlInversion {
    private IUInversion _pantalla;
    private ModelTableValoresInversion _modeloValoresInversion;
    private ModelComboCiudad _modeloCiudad;
    private GestorInversion _gestor;
    private DtoInversion _dto;

    public CtrlInversion(JDesktopPane desktop) {
        _dto = new DtoInversion();
        _gestor = new GestorInversion();
        _pantalla = new IUInversion();

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
        _pantalla.getCbCiudades().addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {

            }

            public void focusLost(FocusEvent e) {
                if(_pantalla.getCbCiudades().getSelectedIndex() > 0)
                    cargarUsos();
            }
        });
        _pantalla.getCbCiudades().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(_pantalla.getCbCiudades().getSelectedIndex() > 0){
                    _pantalla.getTbUsosFondos().transferFocus();
                }

            }
        });
        _pantalla.getTbUsosFondos().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                _pantalla.getTxtTotalInversion().setText(_gestor.calcularInversion(_modeloValoresInversion.getAllRow()));
            }
        });

        _modeloValoresInversion = new ModelTableValoresInversion(null);
        _pantalla.getTbUsosFondos().setModel(_modeloValoresInversion);

        _modeloCiudad = new ModelComboCiudad(_gestor.obtenerCiudades(),"Seleccione ...");
        _pantalla.getCbCiudades().setModel(_modeloCiudad);

        _pantalla.setVisible(true);
        desktop.add(_pantalla);
        _pantalla.toFront();
    }

    private void cargarUsos(){
        List<ValorInversion> lista = _gestor.obtenerDatos(_modeloCiudad.getSelected());
        _modeloValoresInversion = new ModelTableValoresInversion(lista);
        _pantalla.getTbUsosFondos().setModel(_modeloValoresInversion);
        _pantalla.getTxtTotalInversion().setText(_gestor.calcularInversion(lista));
    }

    private void guardar(){
        _dto.setCiudad(_modeloCiudad.getSelected());
        _dto.setImporteInversion(Double.valueOf(_pantalla.getTxtTotalInversion().getText()));
        _dto.setListadoValorInversion(_modeloValoresInversion.getAllRow());
        try {
            _gestor.guardar(_dto);
            JOptionPane.showMessageDialog(_pantalla, "Valores almacenados.", "Viviendas", JOptionPane.INFORMATION_MESSAGE);
            cancelar();
        } catch (BusinessOperationException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Viviendas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelar(){
        _pantalla.hide();
        _pantalla.dispose();
    }
}
