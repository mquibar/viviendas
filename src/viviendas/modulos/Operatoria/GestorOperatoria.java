/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.Operatoria;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.persistencia.Facade;

/**
 *
 * @author Maximiliano.
 */
public class GestorOperatoria {
    public static final String NOMBRE_PARAM_OPERATORIA = "OPERATORIA_";

    public void guardar(List<Operatoria> listaMod){
        List<Operatoria> listaDB = Facade.getInstance().findAll(Operatoria.class);

        try {
            Facade.getInstance().beginTx();
            for(int i_db=0; i_db<listaMod.size(); i_db++){
                if(listaMod.get(i_db).getId() == null){
                    //inserto.
                    listaMod.get(i_db).getParametro().setNombreParametro(NOMBRE_PARAM_OPERATORIA + listaMod.get(i_db).getNombre());
                    Facade.getInstance().guardar(listaMod.get(i_db).getParametro());
                    Facade.getInstance().guardar(listaMod.get(i_db));
                }
                else{
                    //modifico.
                    System.out.println(listaMod.get(i_db).getNombre());
                    Facade.getInstance().actualizar(listaMod.get(i_db));
                }
            }
            //eliminar:
            for(int i=0; i<listaDB.size(); i++){
                if(!listaMod.contains(listaDB.get(i))){
                    Facade.getInstance().eliminar(listaDB.get(i));
                }
            }
            Facade.getInstance().commitTx();
        } catch (Exception ex) {
            Logger.getLogger(GestorOperatoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List obtenerOperatorias() {
        return  Facade.getInstance().findAll(Operatoria.class);
    }
}
