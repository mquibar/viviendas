/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.Plan.consultar;

import java.util.List;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.entidades.vivienda.Plan;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;

public class GestorConsultarPlan {

    public List<Plan> listarPlanes(){
        return Facade.getInstance().findAll(Plan.class);
    }

    public List<DistribucionProvincial> listarDistProv(AñoPlan aplan){
        Criterio c = new Criterio("añoPlan", "=", aplan);
        return Facade.getInstance().findByCriterio(DistribucionProvincial.class, c);
    }

    public List<DistribucionCiudad> listarDistCiud(AñoPlan aplan){
        Criterio c = new Criterio("añoPlan", "=", aplan);
        return Facade.getInstance().findByCriterio(DistribucionCiudad.class, c);
    }

    public List<DistribucionSector> listarDistSEcono(AñoPlan aplan){
        Criterio c = new Criterio("añoPlan", "=", aplan);
        return Facade.getInstance().findByCriterio(DistribucionSector.class, c);
    }

    public List<DistribucionOperatoria> listarDistOper(AñoPlan aplan){
        Criterio c = new Criterio("añoPlan", "=", aplan);
        return Facade.getInstance().findByCriterio(DistribucionOperatoria.class, c);
    }
}