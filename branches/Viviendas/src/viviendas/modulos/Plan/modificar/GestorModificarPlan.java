/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.modulos.Plan.modificar;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.entidades.vivienda.Plan;
import viviendas.modulos.Plan.consultar.GestorConsultarPlan;

/**
 *
 * @author Manuel
 */
public class GestorModificarPlan {

    private Plan plan_a_modificar;
    private GestorConsultarPlan gestor;

    public GestorModificarPlan() {
        gestor = new GestorConsultarPlan();
    }

    public List<Plan> listarPlanes() {
        return gestor.listarPlanes();
    }

    public Plan getPlan() {
        return plan_a_modificar;
    }

    public void cargarPlan(Plan plan) {

        plan_a_modificar=plan;
        for (AñoPlan aPlan : plan_a_modificar.getListaAñoPlan()) {
            aPlan.setListaDistribucionProvincial(gestor.listarDistProv(aPlan));
            aPlan.setListaProvinciaCiudad(gestor.listarDistCiud(aPlan));
            aPlan.setDistribucionSector(gestor.listarDistSEcono(aPlan));
            aPlan.setDistribucionOperatoria(gestor.listarDistOper(aPlan));
        }
    }

    public List<DistribucionProvincial> listarDistribucionProvincial(AñoPlan aplan){
        return aplan.getListaDistribucionProvincial();
    }

    public List<DistribucionCiudad> listarDistribucionCiudad(AñoPlan aPlan, DistribucionProvincial dProvincial){
        List<DistribucionCiudad> dc = new ArrayList<DistribucionCiudad>();
        for (DistribucionCiudad dCiudad : aPlan.getListaProvinciaCiudad()) {
            if(dCiudad.getDistribucionProvincial().equals(dProvincial))
                dc.add(dCiudad);
        }
        return dc;
    }

    public List<DistribucionSector> listarDistribucionSector(AñoPlan aPlan, DistribucionCiudad dCiudad){
        List<DistribucionSector> ds = new ArrayList<DistribucionSector>();
        for (DistribucionSector dSector : aPlan.getDistribucionSector()) {
            if(dSector.getDistribucionCiudad().equals(dCiudad))
                ds.add(dSector);
        }
        return ds;
    }

    public List<DistribucionOperatoria> listarDistribucionOperatoria(AñoPlan aPlan, DistribucionSector dSector){
        List<DistribucionOperatoria> dop = new ArrayList<DistribucionOperatoria>();
        for (DistribucionOperatoria dOperatoria : aPlan.getDistribucionOperatoria()) {
            if(dOperatoria.getDistribucionSector().equals(dSector))
                dop.add(dOperatoria);
        }
        return dop;
    }
}
