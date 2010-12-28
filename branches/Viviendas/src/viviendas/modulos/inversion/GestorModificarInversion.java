/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.inversion;

import java.util.List;
import viviendas.entidades.flujo.InversionPlan;
import viviendas.entidades.vivienda.Plan;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.CriterioJoin;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author desarrollo
 */
public class GestorModificarInversion {

    private Plan _plan;

    public GestorModificarInversion(Plan plan) {
        this._plan = plan;
    }

    public List<InversionPlan> iniciarCU(){
        Criterio c = new CriterioJoin("IN", _plan.getListaInversion());
        return Facade.getInstance().findByCriterio(InversionPlan.class, c);
    }

    public void actualizarInversiones(List<InversionPlan> lista) throws BusinessOperationException{
        try {
            Facade.getInstance().beginTx();
            for (InversionPlan inversionPlan : lista) {
                Facade.getInstance().actualizar(inversionPlan);
            }
            Facade.getInstance().commitTx();
        } catch (PersistException ex) {
            Facade.getInstance().rollBackTx();
            throw new BusinessOperationException("Actualizar inversion");
        }

    }
}
