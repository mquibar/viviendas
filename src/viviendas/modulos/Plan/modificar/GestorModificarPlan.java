/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.modulos.Plan.modificar;

import java.util.List;
import viviendas.entidades.vivienda.Plan;

/**
 *
 * @author Manuel
 */
public class GestorModificarPlan {

    private Plan plan_a_modificar;

    public List<Plan> listarPlanes() {
        return (new viviendas.modulos.Plan.consultar.GestorConsultarPlan()).listarPlanes();
    }

    public Plan getPlan() {
        return plan_a_modificar;
    }

    public void cargarPlan(Plan plan) {
        plan_a_modificar=plan;
    }
}
