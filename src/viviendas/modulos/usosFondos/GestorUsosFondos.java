/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.usosFondos;

import java.util.List;
import viviendas.entidades.flujo.UsoFondo;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;
import viviendas.systemException.BusinessOperationException;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano.
 */
public class GestorUsosFondos {
    public void guardar(List<UsoFondo> listaMod) throws BusinessOperationException{
        Criterio criterio = new Criterio("vigente", "=", true);
        List<UsoFondo> listaDB = Facade.getInstance().findByCriterio(UsoFondo.class, criterio);

        try {
            Facade.getInstance().beginTx();
            for(int i_db=0; i_db<listaMod.size(); i_db++){
                if(listaMod.get(i_db).getId() == null){
                    Criterio criterio1 = new Criterio("nombre", "=", listaMod.get(i_db).getNombre());
                    List<UsoFondo> listado = Facade.getInstance().findByCriterio(UsoFondo.class, criterio1);
                    if(listado != null && listado.size() == 1){
                        listado.get(0).setVigente(true);
                        Facade.getInstance().actualizar(listado.get(0));
                    }
                    else{
                        Facade.getInstance().guardar(listaMod.get(i_db));
                    }
                }
                else{                    
                    Facade.getInstance().actualizar(listaMod.get(i_db));
                }
            }
            for(int i=0; i<listaDB.size(); i++){
                if(!listaMod.contains(listaDB.get(i))){
                    listaDB.get(i).setVigente(false);
                    Facade.getInstance().actualizar(listaDB.get(i));
                }
            }
            Facade.getInstance().commitTx();
        } catch (Exception ex) {
            throw new BusinessOperationException("Error al guardar usos de fondos.");
        }
    }

    public List obtenerUsosFondos() {
        Criterio criterio = new Criterio("vigente", "=", true);
        List<UsoFondo> listado = Facade.getInstance().findByCriterio(UsoFondo.class, criterio);
        Utiles.ordena(listado, "nombre");
        return listado;
    }
}
