/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.Plan.consultar;

import java.util.List;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.Plan;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;

/**
 *
 * @author Manuel
 */
public class GestorConsultarPlan {

    public List<Plan> listarPlanes(){
        return Facade.getInstance().findAll(Plan.class);
    }

    public List<DistribucionProvincial> listarDistProv(AñoPlan aplan){
        Criterio c = new Criterio("añoPlan", "=", aplan);
        return Facade.getInstance().findByCriterio(DistribucionProvincial.class, c);
    }
}
