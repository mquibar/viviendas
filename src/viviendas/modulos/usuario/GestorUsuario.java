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
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.VerifyDataException;

/**
 *
 * @author Administrador
 */
public class GestorUsuario {

    public GestorUsuario() {
        
    }

    public void validar(DtoUsuario dto) throws Exception {


        if (dto.getNombreUsuario().equals("")) {
            throw new Exception("El campo <usuario> es obligatorio.");
        }

        if (dto.getContrasenia().equals("")) {
            throw new Exception("El campo <contraseña> es obligatorio.");
        }

        //encriptarContraseña
        String contraseña = dto.getContrasenia();


        List<Usuario> usuarios = buscarUsuarios(dto.getNombreUsuario(), dto.getContrasenia());
        if (usuarios.isEmpty()) {
            throw new Exception("El usuario o contraseña son incorrectos");
        }
    }

    public void cambioDeClave(String nombreUsuario, String contraseña, String contraseñaNueva, String reingreso) throws VerifyDataException, PersistException {
        if (!contraseñaNueva.equals(reingreso)) {
            throw new VerifyDataException("Reingrese la nueva contraseña");
        }
        List<Usuario> usuarios = buscarUsuarios(nombreUsuario, contraseña);
        if (usuarios.isEmpty()) {
            throw new VerifyDataException("El usuario o contraseña son incorrectos");
        }
        Facade.getInstance().beginTx();
        Usuario usuario = usuarios.get(0);
        usuario.setContraseña(contraseñaNueva);
        Facade.getInstance().actualizar(usuario);
        Facade.getInstance().commitTx();

    }

    public List<Usuario> buscarUsuarios(String usuario, String contraseña) {
        Criterio criterio = new Criterio("usuario", "=", usuario);
        Criterio criterio1 = new Criterio("contraseña", "=", contraseña);
        CriterioCompuesto cc = new CriterioCompuesto(criterio, "AND", criterio1);
        return (List<Usuario>) Facade.getInstance().findByCriterio(Usuario.class, cc);
    }

    public List<Usuario> buscarUsuarios(String usuario) {
        Criterio criterio = new Criterio("usuario", "=", usuario);
        return (List<Usuario>) Facade.getInstance().findByCriterio(Usuario.class, criterio);
    }
}
