/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.SegmentoEconomico;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.vivienda.ParametrosPlan;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.gui.models.tables.ModeloTablaSectorEconomico;
import viviendas.modulos.SegmentoEconomico.GestorSectorEconomico;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano.
 */
public class CtrlSegmentoEconomico {
    private IUSegmentoEconomico _pantalla;
    private GestorSectorEconomico _gestor;
    private ModeloTablaSectorEconomico _modelo;

    public CtrlSegmentoEconomico(JDesktopPane desktop) {
        _gestor = new GestorSectorEconomico();
        _pantalla = new IUSegmentoEconomico();
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
        _modelo = new ModeloTablaSectorEconomico(_gestor.obtenerSectoresEconomicos());
        _pantalla.getTbSegmentoEconomico().setModel(_modelo);
        _pantalla.getTbSegmentoEconomico().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if(_modelo.getSelectedIndex(_pantalla.getTbSegmentoEconomico().getSelectedRow()) != null){
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
        SectorEconomico se = new SectorEconomico();
        ParametrosPlan pp = new ParametrosPlan();
        pp.setNombreParametro("");
        pp.setPorcenteaje(0d);
        se.setParametro(pp);
        _modelo.addRow(se);
    }

    private void quitar(){
        if(_pantalla.getTbSegmentoEconomico().getSelectedRow() > -1){
            _modelo.delRow(_pantalla.getTbSegmentoEconomico().getSelectedRow());
            verificarPorcentajes();
        }
        else{
            JOptionPane.showMessageDialog(_pantalla, "No ha seleccionado ninguna fila.", "Viviendas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarPorcentajes(){
        List porcentajes = new ArrayList();
        double sum = 0d;
        for(int i=0; i<_pantalla.getTbSegmentoEconomico().getRowCount(); i++){
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
