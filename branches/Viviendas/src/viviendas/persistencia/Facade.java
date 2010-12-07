/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import java.util.List;
import systemException.SystemException;

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

    public void guardar(Object entidad) throws SystemException {
//        MediatorFactory.getInstance().getMediator(entidad).guardar(entidad);
    }

    public void actualizar(Object entidad) throws SystemException {
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

    public List excecuteQuery(Object entidad, String query) {
//        return MediatorFactory.getInstance().getMediator(entidad).excecuteQuery(query);
        return null;
    }
}
