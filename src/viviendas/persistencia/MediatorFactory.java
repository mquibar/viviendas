/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import java.util.HashMap;
import java.util.Map;
import viviendas.entidades.flujo.FuenteFondo;
import viviendas.entidades.flujo.UsoFondo;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.ParametrosPlan;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.entidades.vivienda.TipoPlan;
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
        USUARIO, OPERATORIA, SECTORECONOMICO, CIUDAD, PROVINCIA, TIPOPLAN, PARAMETROSPLAN, USOFONDO, FUENTEFONDO
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
                interm = new Intermediario<Ciudad>(c.getSimpleName());
                break;
            case USUARIO:
                interm = new Intermediario<Usuario>(c.getSimpleName());
                break;
            case OPERATORIA:
                interm = new Intermediario<Operatoria>(c.getSimpleName());
                break;
            case SECTORECONOMICO:
                interm = new Intermediario<SectorEconomico>(c.getSimpleName());
                break;
            case TIPOPLAN:
                interm = new Intermediario<TipoPlan>(c.getSimpleName());
                break;
            case DISTRIBUCIONCIUDAD:
                interm = new Intermediario<DistribucionCiudad>(c.getSimpleName());
                break;
            case DISTRIBUCIONOPERATORIA:
                interm = new Intermediario<DistribucionOperatoria>(c.getSimpleName());
                break;
            case DISTRIBUCIONPROVINCIAL:
                interm = new Intermediario<DistribucionProvincial>(c.getSimpleName());
                break;
            case DISTRIBUCIONSECTOR:
                interm = new Intermediario<DistribucionSector>(c.getSimpleName());
                break;
            case PLAN:
                interm = new Intermediario<Plan>(c.getSimpleName());
                break;
            case AÑOPLAN:
                interm = new Intermediario<AñoPlan>("AñoPlan");
                break;
            case PARAMETROSPLAN:
                interm = new Intermediario<ParametrosPlan>(c.getSimpleName());
                break;
            case USOFONDO:
                interm = new Intermediario<UsoFondo>(c.getSimpleName());
                break;
            case FUENTEFONDO:
                interm = new Intermediario<FuenteFondo>(c.getSimpleName());
                break;
        }

        _mapaIntermediarios.put(c, interm);
        return interm;

    }
}
