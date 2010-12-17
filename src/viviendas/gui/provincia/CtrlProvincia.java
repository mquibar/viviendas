/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.provincia;

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
import viviendas.gui.models.tables.ModelTableProvincia;
import viviendas.gui.models.tables.ModeloTableCiudad;
import viviendas.modulos.provincia.GestorProvincia;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano
 */
public class CtrlProvincia {
    private IUProvincia _pantalla;
    private GestorProvincia _gestor;
    private ModelTableProvincia _modelo;

    public CtrlProvincia(JDesktopPane desktop) {
        _gestor = new GestorProvincia();
        _pantalla = new IUProvincia();
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
        _modelo = new ModelTableProvincia(_gestor.obtenerProvincias());
        _pantalla.getTbProvincias().setModel(_modelo);
        _pantalla.getTbProvincias().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if(_modelo.getSelectedIndex(_pantalla.getTbProvincias().getSelectedRow()) != null){
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

    private void verificarPorcentajes(){
        List porcentajes = new ArrayList();
        double sum = 0d;
        for(int i=0; i<_pantalla.getTbProvincias().getRowCount(); i++){
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

class TablaCiudad extends ModeloTableCiudad{

    public TablaCiudad(List<Ciudad> lista) {
        super(lista);
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

}