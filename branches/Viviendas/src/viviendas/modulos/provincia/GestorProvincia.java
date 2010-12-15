/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.provincia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Provincia;
import viviendas.modulos.Operatoria.GestorOperatoria;
import viviendas.persistencia.Facade;

/**
 *
 * @author maximiliano
 */
public class GestorProvincia {
    public static final String NOMBRE_PARAM_PROVINCIA = "PROVINCIA_";

    public void guardar(List<Provincia> listaMod){
        try {
            Facade.getInstance().beginTx();
            for(int i_db=0; i_db<listaMod.size(); i_db++){
                Facade.getInstance().actualizar(listaMod.get(i_db));
            }
            Facade.getInstance().commitTx();
        } catch (Exception ex) {
            Logger.getLogger(GestorOperatoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List obtenerProvincias() {
        return  Facade.getInstance().findAll(Provincia.class);
    }

    public List obtenerCiudades() {
        return  Facade.getInstance().findAll(Ciudad.class);
    }
}
