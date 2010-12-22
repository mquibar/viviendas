/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.FuentesFondos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import viviendas.entidades.flujo.FuenteFondo;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.ParametrosPlan;
import viviendas.modulos.Operatoria.GestorOperatoria;
import viviendas.persistencia.Facade;

/**
 *
 * @author Maximiliano.
 */
public class GestorFuentesFondos {
    public static final String NOMBRE_PARAM_FUENTEFONDO = "FUENTEFONDO_";
    
    public void guardar(List<FuenteFondo> listaMod){
        List<FuenteFondo> listaDB = Facade.getInstance().findAll(Operatoria.class);

        try {
            Facade.getInstance().beginTx();
            for(int i_db=0; i_db<listaMod.size(); i_db++){
                if(listaMod.get(i_db).getId() == null){
                    //inserto.
                    listaMod.get(i_db).getParametro().setNombreParametro(NOMBRE_PARAM_FUENTEFONDO + listaMod.get(i_db).getNombre());
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

    public List obtenerFuentesFondos(){
        return Facade.getInstance().findAll(FuenteFondo.class);
//        List<FuenteFondo> listado = Facade.getInstance().findAll(FuenteFondo.class);
//        if(listado == null){
//            ParametrosPlan parametro = new ParametrosPlan();
//            parametro.setNombreParametro("dfsdfsdfs");
//            parametro.setPorcenteaje(25d);
//            FuenteFondo fuente = new FuenteFondo();
//            fuente.setNombre("dsfsss");
//            fuente.setParametro(parametro);
//            listado.add(fuente);
//            return listado;
//        }
//        return listado;
    }
}