/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.tipoCiudad;

import java.util.List;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.persistencia.Facade;

/**
 *
 * @author Maximiliano.
 */
public class GestorTipoCiudad {

    public void guardar(){
        
    }

    public List<Ciudad> obtenerTiposCiudades() {
        return Facade.getInstance().findAll(Ciudad.class);
    }
}
