/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo1
 */
class ConectionAdmin {

    //@PersistenceUnit
    private EntityManagerFactory _factory;
    private EntityManager _manager = null;
    private int _contador = 0;
    private static ConectionAdmin _instance = null;

    public static ConectionAdmin getInstance() {
        if (_instance == null) {
            _instance = new ConectionAdmin();
        }
        return _instance;
    }

    private ConectionAdmin() {
        _factory = Persistence.createEntityManagerFactory("ViviendasPU");

    }

    public synchronized void beginTransaction() {
        if (_manager != null) {
            if (_manager.isOpen() && _contador > 0 && _manager.getTransaction().isActive()) {
                _contador++;
                return;
            }
        } else {
            getManager();
        }

        try {
            if (!_manager.isOpen()) {
                _manager = _factory.createEntityManager();
            }
            _manager.getTransaction().begin();
            _contador++;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void commitTransaction() throws Exception {
        if (_contador > 1) {
            _contador--;
            return;
        }
        if (_manager.isOpen() && _manager.getTransaction().isActive()) {
            try {
                _manager.getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new Exception(ex);
            } finally {
                _contador = 0;
            }
        }
    }

    public synchronized void rollbackTransaction() {
        if (_contador > 1) {
            _contador--;
            return;
        }
        if (_manager.isOpen()) {
            try {
                _manager.getTransaction().rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                _contador = 0;
            }
        }
    }

    public EntityManager getManager() {
        if (_manager == null) {
            _manager = _factory.createEntityManager();
        }
        return _manager;
    }
}
