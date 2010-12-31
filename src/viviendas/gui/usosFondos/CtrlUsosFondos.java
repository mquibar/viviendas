/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.usosFondos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import viviendas.entidades.flujo.UsoFondo;
import viviendas.gui.models.tables.ModelTableUsoFondo;
import viviendas.modulos.usosFondos.GestorUsosFondos;

/**
 *
 * @author Maximiliano.
 */
public class CtrlUsosFondos {
    private IUUsosFondos _pantalla;
    private GestorUsosFondos _gestor;
    private ModelTableUsoFondo _modelo;

    public CtrlUsosFondos(JDesktopPane desktop) {
        _gestor = new GestorUsosFondos();
        _pantalla = new IUUsosFondos();

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
        _modelo = new ModelTableUsoFondo(_gestor.obtenerUsosFondos());
        _pantalla.getTbUsosFondos().setModel(_modelo);

        _pantalla.setVisible(true);
        _pantalla.toFront();
        desktop.add(_pantalla);
        _pantalla.toFront();
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

    private void cerrar(){
        _pantalla.hide();
        _pantalla.dispose();
    }

    private void agregar(){
        UsoFondo uso = new UsoFondo();
        uso.setNombre("");
        uso.setVigente(true);
        _modelo.addRow(uso);
    }

    private void quitar(){
        _modelo.delRow(_pantalla.getTbUsosFondos().getSelectedRow());
    }
}
