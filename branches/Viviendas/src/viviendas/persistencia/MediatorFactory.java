/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manuel
 */
class MediatorFactory {

    private static MediatorFactory _instance = null;
    private Map<Class, Intermediario> _mapaIntermediarios;

    private enum intermediarios {

        DISTRIBUCIONCIUDAD, DISTRIBUCIONPROVINCIAL, DISTRIBUCIONSECTOR, DISTRIBUCIONOPERATORIA, PLAN, AÑOPLAN,
        USUARIO, OPERATORIA, SECTORECONOMICO, CIUDAD, PROVINCIA, TIPOPLAN, PARAMETROSPLAN
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

        switch (intermediarios.valueOf(c.getSimpleName().toUpperCase())) {
            case PROVINCIA:
                interm = new IntermediarioProvincia();
                break;
            case CIUDAD:
                interm = new IntermediarioCiudad();
                break;
            case USUARIO:
                interm = new IntermediarioUsuario();
                break;
            case OPERATORIA:
                interm = new IntermediarioOperatoria();
                break;
            case SECTORECONOMICO:
                interm = new IntermediarioSectorEconomico();
                break;
            case TIPOPLAN:
                interm = new IntermediarioTipoPlan();
                break;
            case DISTRIBUCIONCIUDAD:
                interm = new IntermediarioDistribucionCiudad();
                break;
            case DISTRIBUCIONOPERATORIA:
                interm = new IntermediarioDistribucionOperatoria();
                break;
            case DISTRIBUCIONPROVINCIAL:
                interm = new IntermediarioDistribucionProvincial();
                break;
            case DISTRIBUCIONSECTOR:
                interm = new IntermediarioDistribucionSector();
                break;
            case PLAN:
                interm = new IntermediarioPlan();
                break;
            case AÑOPLAN:
                interm = new IntermediarioAñoPlan();
                break;
            case PARAMETROSPLAN:
                interm = new IntermediarioParametrosPlan();
        }

        _mapaIntermediarios.put(c, interm);
        return interm;

    }
}
