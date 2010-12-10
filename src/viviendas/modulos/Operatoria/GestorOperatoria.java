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
import viviendas.persistencia.exceptions.PersistException;

/**
 *
 * @author Maximiliano.
 */
public class GestorOperatoria {

    public void guardar(List<Operatoria> listaMod){
        List<Operatoria> listaDB = Facade.getInstance().findAll(Operatoria.class);

        try {
            Facade.getInstance().beginTx();
            for(int i_db=0; i_db<listaMod.size(); i_db++){
                if(listaMod.get(i_db).getId() == null){
                    //inserto.
                    Facade.getInstance().guardar(listaMod.get(i_db));
                    //continue;
                }
                else{
                    if(!listaMod.contains(listaDB.get(i_db))){
                        //elimino.
                        Facade.getInstance().eliminar(listaDB.get(i_db));
                    }
                    else{
                        //modifico.
                        System.out.println(listaMod.get(i_db).getNombre());
                        Facade.getInstance().actualizar(listaMod.get(i_db));
                    }
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
