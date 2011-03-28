/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.FuentesFondos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.flujo.FuenteFondo;
import viviendas.entidades.vivienda.ParametrosPlan;
import viviendas.gui.models.tables.ModelTableFuenteFondo;
import viviendas.modulos.FuentesFondos.GestorFuentesFondos;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano.
 */
public class CtrlFuenteFondo {
    private IUFuenteFondo _pantalla;
    private GestorFuentesFondos _gestor;
    private ModelTableFuenteFondo _modelo;

    public CtrlFuenteFondo(JDesktopPane desktop) {
        _gestor = new GestorFuentesFondos();
        
        _pantalla = new IUFuenteFondo();
        _pantalla.getBtnAceptar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cerrar();
            }
        });
        _pantalla.getBtnAgregar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });
        _pantalla.getBtnQuitar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                quitar();
            }
        });
        _pantalla.getTbFuentesFondos().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if(_modelo.getSelectedIndex(_pantalla.getTbFuentesFondos().getSelectedRow()) != null){
                    verificarPorcentajes();
                }
            }
        });

        _modelo = new ModelTableFuenteFondo(_gestor.obtenerFuentesFondos());
        _pantalla.getTbFuentesFondos().setModel(_modelo);
        verificarPorcentajes();
        
        _pantalla.setVisible(true);
        desktop.add(_pantalla);
        _pantalla.toFront();
    }

    private void cerrar(){
        _pantalla.hide();
        _pantalla.dispose();
    }

    private void guardar(){
        try {
            _gestor.guardar(_modelo.getAllRow());
            JOptionPane.showMessageDialog(_pantalla, "Valores guardados correctamente", "Viviendas", JOptionPane.INFORMATION_MESSAGE);
            cerrar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregar(){
        ParametrosPlan parametro = new ParametrosPlan();
        parametro.setNombreParametro("");
        parametro.setPorcenteaje(0d);
        FuenteFondo fuente = new FuenteFondo();
        fuente.setNombre("");
        fuente.setParametro(parametro);
        fuente.setVigente(true);
        _modelo.addRow(fuente);
        verificarPorcentajes();
    }

    private void quitar(){
        if(!_modelo.getSelectedIndex(_pantalla.getTbFuentesFondos().getSelectedRow()).getNombre().equals(GestorFuentesFondos.OTROSAPORTES) |
            !_modelo.getSelectedIndex(_pantalla.getTbFuentesFondos().getSelectedRow()).getNombre().equals(GestorFuentesFondos.AHORRO_PREVIO) |
            !_modelo.getSelectedIndex(_pantalla.getTbFuentesFondos().getSelectedRow()).getNombre().equals(GestorFuentesFondos.CREDITO_ESTADO) |
            !_modelo.getSelectedIndex(_pantalla.getTbFuentesFondos().getSelectedRow()).getNombre().equals(GestorFuentesFondos.SUBSIDIO_ESTADO))
            if(_pantalla.getTbFuentesFondos().getSelectedRow() > -1){
                _modelo.delRow(_pantalla.getTbFuentesFondos().getSelectedRow());
                verificarPorcentajes();
            }
            else
                JOptionPane.showMessageDialog(_pantalla, "No ha seleccionado ninguna fila.", "Viviendas", JOptionPane.ERROR_MESSAGE);
    }

    private void verificarPorcentajes(){
        List porcentajes = new ArrayList();
        double sum = 0d;
        for(int i=0; i<_pantalla.getTbFuentesFondos().getRowCount(); i++){
            sum = sum + _modelo.getSelectedIndex(i).getParametro().getPorcenteaje();
            porcentajes.add(_modelo.getSelectedIndex(i).getParametro().getPorcenteaje());
        }
        try {
            Utiles.verificarPorcentajes(porcentajes);
            _pantalla.getTxtPorcentajeTotal().setText(Double.valueOf(sum).toString());
            _pantalla.getTxtPorcentajeTotal().setForeground(Color.blue);
            _pantalla.getBtnAceptar().setEnabled(true);
        } catch (Exception ex) {
            _pantalla.getTxtPorcentajeTotal().setText(Double.valueOf(sum).toString());
            _pantalla.getTxtPorcentajeTotal().setForeground(Color.red);
            _pantalla.getBtnAceptar().setEnabled(false);
        }
    }
}
