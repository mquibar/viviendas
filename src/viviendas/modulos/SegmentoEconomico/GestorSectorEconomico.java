/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.SegmentoEconomico;

import java.util.List;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.persistencia.Facade;

/**
 *
 * @author Maximiliano.
 */
public class GestorSectorEconomico {
    public static final String NOMBRE_PARAM_SECTOR_ECONOMICO = "SECTOR-ECONOMICO_";

    public void guardar(List<SectorEconomico> listaMod) throws Exception{
        List<SectorEconomico> listaDB = Facade.getInstance().findAll(SectorEconomico.class);

        try {
            Facade.getInstance().beginTx();
            for(int i_db=0; i_db<listaMod.size(); i_db++){
                if(listaMod.get(i_db).getId() == null){
                    listaMod.get(i_db).getParametro().setNombreParametro(NOMBRE_PARAM_SECTOR_ECONOMICO + listaMod.get(i_db).getNombre());
                    Facade.getInstance().guardar(listaMod.get(i_db).getParametro());
                    Facade.getInstance().guardar(listaMod.get(i_db));
                }
                else{                   
                    System.out.println(listaMod.get(i_db).getNombre());
                    Facade.getInstance().actualizar(listaMod.get(i_db));
                }
            }
            for(int i=0; i<listaDB.size(); i++){
                if(!listaMod.contains(listaDB.get(i))){
                    Facade.getInstance().eliminar(listaDB.get(i));
                }
            }
            Facade.getInstance().commitTx();
        } catch (Exception ex) {
            throw new Exception("Error al guardar.");
        }
    }

    public List<SectorEconomico> obtenerSectoresEconomicos() {
        return Facade.getInstance().findAll(SectorEconomico.class);
    }
}
