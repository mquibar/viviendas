/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.provincia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Provincia;
import viviendas.gui.models.tables.ModelTableProvincia;
import viviendas.gui.models.tables.ModeloTableCiudad;
import viviendas.modulos.provincia.GestorProvincia;
import viviendas.systemException.BusinessOperationException;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano
 */
public class CtrlProvincia {
    private IUProvincia _pantalla;
    private GestorProvincia _gestor;
    private ModelTableProvincia _modelo;
    private ModeloTableCiudad _modeloCiudadNOSel;
    private ModeloTableCiudad _modeloCiudadSel;

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
        _pantalla.getTbProvincias().addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                mostrarProvincias();
            }
        });

        _pantalla.getTbProvincias().addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                mostrarProvincias();
            }

            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        _pantalla.getBtnAgregar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                agregarCiudad();
            }
        });
        _pantalla.getBtnQuitar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                quitarCiudad();
            }
        });
        _pantalla.getBtnAsociarCiudad().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cargarCiudades();
            }
        });

        _modeloCiudadSel = new ModeloTableCiudad(null);
        _pantalla.getTbTiposCiudadesSel().setModel(_modeloCiudadSel);
        _modeloCiudadNOSel = new ModeloTableCiudad(_gestor.obtenerCiudades());
        _pantalla.getTbTiposCiudadesNOSel().setModel(_modeloCiudadNOSel);

        _pantalla.setVisible(true);
        verificarPorcentajes();
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

    private void mostrarProvincias(){
        Provincia provincia = null;

        if(_pantalla.getTbProvincias().getSelectedRow() >= 0){
            provincia = _modelo.getSelectedIndex(_pantalla.getTbProvincias().getSelectedRow());
        }

        List<Ciudad> listaCiudades = provincia.getListaCuidad();
        List<Ciudad> listaCiudadesDB = _gestor.obtenerCiudades();
        List<Ciudad> listaSel = new ArrayList();
        List<Ciudad> listaNOSel = new ArrayList();

        if(listaCiudades != null){
            for(int i=0; i<listaCiudadesDB.size(); i++){
                if(listaCiudades.contains(listaCiudadesDB.get(i))){
                    //agregamos la ciudad a la tabla de ciudadesSeleccionadas.
                    listaSel.add(listaCiudadesDB.get(i));
                }
                else{
                    //agregamos la ciudad a la tabla de ciudadesNOSel.
                    listaNOSel.add(listaCiudadesDB.get(i));
                }
            }
            _modeloCiudadNOSel = new TablaCiudad(listaNOSel);
            _pantalla.getTbTiposCiudadesNOSel().setModel(_modeloCiudadNOSel);
            _modeloCiudadSel = new TablaCiudad(listaSel);
            _pantalla.getTbTiposCiudadesSel().setModel(_modeloCiudadSel);
        }
        else{
            //agregamos todas las ciudades a la tabla de ciudadesNoSel.
            _modeloCiudadNOSel = new ModeloTableCiudad(listaCiudadesDB);
        }

    }

    private void agregarCiudad(){
        Ciudad ciudad = _modeloCiudadNOSel.getSelectedIndex(_pantalla.getTbTiposCiudadesNOSel().getSelectedRow());
        _modeloCiudadNOSel.delRow(ciudad);
        _modeloCiudadSel.addRow(ciudad);
        List<Ciudad> listaCiudad = _modelo.getSelectedIndex(_pantalla.getTbProvincias().getSelectedRow()).getListaCuidad();
        listaCiudad.add(ciudad);
        _modelo.getSelectedIndex(_pantalla.getTbProvincias().getSelectedRow()).setListaCuidad(listaCiudad);
    }

    private void quitarCiudad(){
        Ciudad ciudad = _modeloCiudadSel.getSelectedIndex(_pantalla.getTbTiposCiudadesSel().getSelectedRow());
        _modeloCiudadNOSel.addRow(ciudad);
        _modeloCiudadSel.delRow(ciudad);
        List<Ciudad> listaCiudad = _modelo.getSelectedIndex(_pantalla.getTbProvincias().getSelectedRow()).getListaCuidad();
        listaCiudad.remove(ciudad);
        _modelo.getSelectedIndex(_pantalla.getTbProvincias().getSelectedRow()).setListaCuidad(listaCiudad);
    }

    private void cargarCiudades(){
        try {
            //_gestor.guardar(_modelo.getAllRow());
            if(_pantalla.getTbProvincias().getSelectedRow() >= 0){
                _gestor.guardar(_modelo.getSelectedIndex(_pantalla.getTbProvincias().getSelectedRow()));
                JOptionPane.showMessageDialog(_pantalla, "Ciudades asociadas correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (BusinessOperationException ex) {
            JOptionPane.showMessageDialog(_pantalla, "No se pudieron guardar los cambios de ciudades para la provincia seleccionada. ", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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