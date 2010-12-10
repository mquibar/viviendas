/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Operatoria;

import viviendas.gui.models.tables.ModeloTablaOperatoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.modulos.Operatoria.GestorOperatoria;

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
        _pantalla.setVisible(true);
        desktop.add(_pantalla);
    }

    private void cerrar(){
        _pantalla.hide();
        _pantalla.dispose();
    }

    private void guardar(){
        _gestor.guardar(_modelo.getAllRow());
    }

    private void agregar(){
        Operatoria op = new Operatoria();
        _modelo.addRow(op);
        System.out.println("ID: " + op.getId());
    }

    private void quitar(){
        if(_pantalla.getTbOperatoria().getSelectedRow() > -1)
            _modelo.delRow(_pantalla.getTbOperatoria().getSelectedRow());
        else
            JOptionPane.showMessageDialog(_pantalla, "No ha seleccionado ninguna fila.", "Viviendas", JOptionPane.ERROR_MESSAGE);
    }
}
