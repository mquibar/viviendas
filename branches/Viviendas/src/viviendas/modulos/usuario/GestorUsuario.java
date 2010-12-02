/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.usuario;

import viviendas.gui.dto.DtoUsuario;

/**
 *
 * @author Administrador
 */
public class GestorUsuario {

    public void validar(DtoUsuario dto) throws Exception {
        if(dto.getNombreUsuario().equals(""))
            throw new Exception("El campo <nombre de usuario> es obligatorio.");

        if(dto.getContrasenia().equals(""))
            throw new Exception("El campo <contraseña> es obligatorio.");

        //validamos el usuario contra la base de datos.
    }

}
