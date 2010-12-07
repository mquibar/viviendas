/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import java.util.HashMap;
import java.util.Map;
import viviendas.entidades.vivienda.Usuario;

/**
 *
 * @author Manuel
 */
class MediatorFactory {

    private static MediatorFactory _instance = null;
    private Map<Class, Intermediario> _mapaIntermediarios;

    private enum intermediarios {
        Usuario
    }

    private MediatorFactory() {
        _mapaIntermediarios = new HashMap<Class, Intermediario>();
    }

    public static MediatorFactory getInstance() {
        if (_instance == null) {
            _instance = new MediatorFactory();
        }
        return _instance;
    }

    public Intermediario getMediator(Class c) {

        if (_mapaIntermediarios.containsKey(c)) {
            return _mapaIntermediarios.get(c);
        }

        Intermediario interm = null;

        switch (intermediarios.valueOf(c.getSimpleName())) {
            case Usuario:
                interm = new Intermediario<Usuario>("Usuario");
                break;
        }

        _mapaIntermediarios.put(c, interm);
        return interm;

    }
}
