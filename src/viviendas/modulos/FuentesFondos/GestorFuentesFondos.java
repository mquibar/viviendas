/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.FuentesFondos;

import java.util.List;
import viviendas.entidades.flujo.FuenteFondo;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;
import viviendas.systemException.BusinessOperationException;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano.
 */
public class GestorFuentesFondos {
    public static final String NOMBRE_PARAM_FUENTEFONDO = "FUENTEFONDO_";
    public static final String OTROSAPORTES = "OTROS APORTES";
    
    public void guardar(List<FuenteFondo> listaMod) throws BusinessOperationException{
        Criterio criterio = new Criterio("vigente", "=", true);
        List<FuenteFondo> listaDB = Facade.getInstance().findByCriterio(FuenteFondo.class, criterio);

        try {
            Facade.getInstance().beginTx();
            for(int i_db=0; i_db<listaMod.size(); i_db++){               
                if(listaMod.get(i_db).getId() == null){
                    Criterio criterio1 = new Criterio("nombre", "=", listaMod.get(i_db).getNombre());
                    List<FuenteFondo> listado = Facade.getInstance().findByCriterio(FuenteFondo.class, criterio1);
                    if(listado != null && listado.size() == 1){
                        listado.get(0).setVigente(true);
                        listado.get(0).getParametro().setPorcenteaje(listaMod.get(i_db).getParametro().getPorcenteaje());
                        Facade.getInstance().actualizar(listado.get(0));
                    }
                    else{
                        listaMod.get(i_db).getParametro().setNombreParametro(NOMBRE_PARAM_FUENTEFONDO + listaMod.get(i_db).getNombre());
                        Facade.getInstance().guardar(listaMod.get(i_db).getParametro());
                        Facade.getInstance().guardar(listaMod.get(i_db));
                    }
                }
                else{
                    System.out.println(listaMod.get(i_db).getNombre());
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
            throw new BusinessOperationException("Error al guardar Fuentes de Fondos.");
        }
    }

    public List obtenerFuentesFondos(){
        Criterio criterio = new Criterio("vigente", "=", true);
        List<FuenteFondo> listado = Facade.getInstance().findByCriterio(FuenteFondo.class, criterio);
        Utiles.ordena(listado, "nombre");
        return listado;
    }
}