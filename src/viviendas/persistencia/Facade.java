/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import java.util.List;
import viviendas.persistencia.exceptions.PersistException;

/**
 *
 * @author Manuel
 */
public class Facade {

    private static Facade _instance = null;

    private Facade() {
    }

    public static Facade getInstance() {
        if (_instance == null) {
            _instance = new Facade();
        }
        return _instance;
    }

    public void beginTx() {
        ConectionAdmin.getInstance().beginTransaction();
    }

    public void commitTx() throws Exception {
        ConectionAdmin.getInstance().commitTransaction();
    }

    public void rollBackTx() {
        ConectionAdmin.getInstance().rollbackTransaction();
    }

    public void guardar(Object entidad) throws PersistException {
        MediatorFactory.getInstance().getMediator(entidad.getClass()).guardar(entidad);
    }

    public void eliminar(Object entidad) throws PersistException{
        MediatorFactory.getInstance().getMediator(entidad.getClass()).eliminar(entidad);
    }

    public void actualizar(Object entidad) throws PersistException {
        MediatorFactory.getInstance().getMediator(entidad.getClass()).actualizar(entidad);
    }

    public List findAll(Class entidad) {
        return MediatorFactory.getInstance().getMediator(entidad).findAll();
    }

    public List findInOrder(Class entidad, String orden) {
            return MediatorFactory.getInstance().getMediator(entidad).findInOrden(orden);
    }

    public List findByCriterio(Class entidad, Criterio criterio) {
        return MediatorFactory.getInstance().getMediator(entidad).findByCriterio(criterio);
    }

    public List excecuteQuery(Class entidad, String query) {
        return MediatorFactory.getInstance().getMediator(entidad).excecuteQuery(query);
    }
}
