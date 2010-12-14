/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.TipoCiudad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.ParametrosPlan;
import viviendas.gui.models.tables.ModeloTableCiudad;
import viviendas.modulos.tipoCiudad.GestorTipoCiudad;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano.
 */
public class CtrlTipoCiudad {
    private IUTipoCiudad _pantalla;
    private GestorTipoCiudad _gestor;
    private ModeloTableCiudad _modelo;

    public CtrlTipoCiudad(JDesktopPane desktop) {
        _gestor = new GestorTipoCiudad();
        _pantalla = new IUTipoCiudad();
        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrar();
            }
        });
        _pantalla.getBtnAceptar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar();
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
        _modelo = new ModeloTableCiudad(_gestor.obtenerTiposCiudades());
        _pantalla.getTbCiudad().setModel(_modelo);
        _pantalla.getTbCiudad().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if(_modelo.getSelectedIndex(_pantalla.getTbCiudad().getSelectedRow()) != null){
                    verificarPorcentajes();
                }
            }
        });
        verificarPorcentajes();
        _pantalla.setVisible(true);
        desktop.add(_pantalla);
    }

    private void cerrar(){
        _pantalla.hide();
        _pantalla.dispose();
    }

    private void guardar(){
        try {
            _gestor.guardar(_modelo.getAllRow());
            JOptionPane.showMessageDialog(_pantalla, "Valores guardados correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            cerrar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregar(){
        Ciudad c = new Ciudad();
        ParametrosPlan pp = new ParametrosPlan();
        pp.setNombreParametro("");
        pp.setPorcenteaje(0d);
        c.setParametro(pp);
        _modelo.addRow(c);
    }

    private void quitar(){
        if(_pantalla.getTbCiudad().getSelectedRow() > -1){
            _modelo.delRow(_pantalla.getTbCiudad().getSelectedRow());
            verificarPorcentajes();
        }
        else{
            JOptionPane.showMessageDialog(_pantalla, "No ha seleccionado ninguna fila.", "Viviendas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarPorcentajes(){
        List porcentajes = new ArrayList();
        double sum = 0d;
        for(int i=0; i<_pantalla.getTbCiudad().getRowCount(); i++){
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
