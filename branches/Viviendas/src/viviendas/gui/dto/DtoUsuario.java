/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.dto;

/**
 *
 * @author Admin
 */
public class DtoUsuario {
    private String _nombreUsuario;
    private String _contrasenia;

    public String getContrasenia() {
        return _contrasenia;
    }

    public void setContrasenia(String _contrasenia) {
        this._contrasenia = _contrasenia;
    }

    public String getNombreUsuario() {
        return _nombreUsuario;
    }

    public void setNombreUsuario(String _nombreUsuario) {
        this._nombreUsuario = _nombreUsuario;
    }

}
