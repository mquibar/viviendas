/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.sistema;

/**
 *
 * @author Administrador
 */
public class CtrlPrincipal {
    private CtrlUsuario ctrlUsuario;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new CtrlPrincipal().iniciar();
    }

    private void iniciar(){
        ctrlUsuario = new CtrlUsuario();
    }

}
