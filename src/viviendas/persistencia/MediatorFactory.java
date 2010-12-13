/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import java.util.HashMap;
import java.util.Map;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.Usuario;

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
                interm = new Intermediario<Provincia>(c.getSimpleName());
                break;
            case CIUDAD:
                interm = new IntermediarioCiudad();
                break;
            case USUARIO:
                interm = new Intermediario<Usuario>(c.getSimpleName());
                break;
            case OPERATORIA:
                interm = new Intermediario<Operatoria>(c.getSimpleName());
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
                interm = new Intermediario<Plan>(c.getSimpleName());
                break;
            case AÑOPLAN:
                interm = new Intermediario<AñoPlan>("AñoPlan");
                break;
            case PARAMETROSPLAN:
                interm = new IntermediarioParametrosPlan();
        }

        _mapaIntermediarios.put(c, interm);
        return interm;

    }
}
