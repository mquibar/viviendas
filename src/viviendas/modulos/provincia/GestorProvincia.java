/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.provincia;

import java.util.List;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Provincia;
import viviendas.persistencia.Facade;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author maximiliano
 */
public class GestorProvincia {
    public static final String NOMBRE_PARAM_PROVINCIA = "PROVINCIA_";

    public void guardar(List<Provincia> listaMod) throws BusinessOperationException{
        try {
            Facade.getInstance().beginTx();
            for(int i_db=0; i_db<listaMod.size(); i_db++){
                Facade.getInstance().actualizar(listaMod.get(i_db));
            }
            Facade.getInstance().commitTx();
        } catch (Exception ex) {
            throw new BusinessOperationException("Error al guardar operatorias.");
        }
    }

    public List obtenerProvincias() {
        return  Facade.getInstance().findInOrder(Provincia.class, "nombre");
    }

    public List obtenerCiudades() {
        return  Facade.getInstance().findInOrder(Ciudad.class, "nombre");
    }
}