/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.modulos.usuario;

import java.util.List;
import viviendas.persistencia.Criterio;
import viviendas.entidades.vivienda.Usuario;
import viviendas.gui.dto.DtoUsuario;
import viviendas.persistencia.CriterioCompuesto;
import viviendas.persistencia.Facade;

/**
 *
 * @author Administrador
 */
public class GestorUsuario {

    public void validar(DtoUsuario dto) throws Exception {


        if (dto.getNombreUsuario().equals("")) {
            throw new Exception("El campo <usuario> es obligatorio.");
        }

        if (dto.getContrasenia().equals("")) {
            throw new Exception("El campo <contraseña> es obligatorio.");
        }

        //encriptarContraseña
        String contraseña = dto.getContrasenia();

        Criterio criterio = new Criterio("usuario", "=", dto.getNombreUsuario());
        List<Usuario> usuarios = (List<Usuario>) Facade.getInstance().findByCriterio(Usuario.class, criterio);
        if (usuarios.isEmpty()) {
            throw new Exception("El usuario o contraseña son incorrectos");
        }
    }
}
