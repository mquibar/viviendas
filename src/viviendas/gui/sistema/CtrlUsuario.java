/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import viviendas.modulos.usuario.GestorUsuario;
import viviendas.gui.dto.DtoUsuario;

/**
 *
 * @author Administrador
 */
public class CtrlUsuario {
    private IULoguin _pantalla;
    private GestorUsuario _gestor;
    private DtoUsuario _dtoUsuario;
    private CtrlPrincipal _cp;

    public CtrlUsuario(CtrlPrincipal cp) {
        this._cp = cp;

        _pantalla = new IULoguin();
        _pantalla.getBtnCancelar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        _pantalla.getBtnAceptar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validar();
            }
        });
        _pantalla.getTextContrasenia().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                validar();
            }
        });
        _pantalla.setLocationRelativeTo(null);
        _pantalla.setVisible(true);
        _dtoUsuario = new DtoUsuario();
        _gestor = new GestorUsuario();
    }

    private void salir(){
        if(JOptionPane.showConfirmDialog(_pantalla, "Esta seguro de que desea salir del sistema?", "Vivienda", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    private void validar(){
        _dtoUsuario.setNombreUsuario(_pantalla.getTxtUsuario().getText());
        _dtoUsuario.setContrasenia(_pantalla.getTextContrasenia().getText());
        try {
            _gestor.validar(_dtoUsuario);
            _pantalla.dispose();
            _cp.abrirPantallaPrincipal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Vivienda", JOptionPane.ERROR_MESSAGE);
        }

    }
}
