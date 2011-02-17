/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import viviendas.gui.sistema.CtrlPrincipal;
import viviendas.modulos.usuario.GestorUsuario;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.VerifyDataException;

/**
 *
 * @author batte
 */
public class CtrlUsuario {

    private IUUsuario _pantalla;
    private GestorUsuario _gestor;

    public CtrlUsuario() {
        _gestor = new GestorUsuario();
        _pantalla = new IUUsuario();
        _pantalla.getBtnAceptar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaAceptar();
            }
        });
        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presionaCancelar();
            }
        });
        _pantalla.setVisible(true);

        CtrlPrincipal.getInstance().getDesktopPane().add(_pantalla);
    }

    public void presionaAceptar() {
        String usuario = _pantalla.getTxtUsuario().getText();
        String contraseña = new String(_pantalla.getTxtContrasenia().getPassword());
        String contraseñaNueva = new String(_pantalla.getTxtContraseniaNueva().getPassword());
        String reingreso = new String(_pantalla.getTxtReingreseContrasenia().getPassword());
        try {
            _gestor.cambioDeClave(usuario, contraseña, contraseñaNueva, reingreso);
            JOptionPane.showMessageDialog(_pantalla, "Se ha cambiado la constrañse correctamente", "", JOptionPane.ERROR_MESSAGE);
        } catch (VerifyDataException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getDato(), "", JOptionPane.ERROR_MESSAGE);
        } catch (PersistException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void presionaCancelar() {
    }
}
