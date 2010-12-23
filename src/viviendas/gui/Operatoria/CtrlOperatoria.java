/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Operatoria;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import viviendas.gui.models.tables.ModeloTablaOperatoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.ParametrosPlan;
import viviendas.modulos.Operatoria.GestorOperatoria;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano.
 */
public class CtrlOperatoria {
    private IUOperatoria _pantalla;
    private GestorOperatoria _gestor;
    private ModeloTablaOperatoria _modelo;

    public CtrlOperatoria(JDesktopPane desktop) {
        _gestor = new GestorOperatoria();
        _pantalla = new IUOperatoria();
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
        _modelo = new ModeloTablaOperatoria(_gestor.obtenerOperatorias());
        _pantalla.getTbOperatoria().setModel(_modelo);
        _pantalla.getTbOperatoria().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if(_modelo.getSelectedIndex(_pantalla.getTbOperatoria().getSelectedRow()) != null){
                    verificarPorcentajes();
                }
            }
        });
        _pantalla.setVisible(true);
        verificarPorcentajes();
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
        Operatoria op = new Operatoria();
        ParametrosPlan pp = new ParametrosPlan();
        pp.setNombreParametro("");
        pp.setPorcenteaje(0d);
        op.setParametro(pp);
        op.setVigente(true);
        _modelo.addRow(op);
    }

    private void quitar(){
        if(_pantalla.getTbOperatoria().getSelectedRow() > -1){
            _modelo.delRow(_pantalla.getTbOperatoria().getSelectedRow());
            verificarPorcentajes();
        }
        else
            JOptionPane.showMessageDialog(_pantalla, "No ha seleccionado ninguna fila.", "Viviendas", JOptionPane.ERROR_MESSAGE);
    }

    private void verificarPorcentajes(){
        List porcentajes = new ArrayList();
        double sum = 0d;
        for(int i=0; i<_pantalla.getTbOperatoria().getRowCount(); i++){
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