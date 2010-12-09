/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.modulos.Plan;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.gui.Plan.crear.DtoNuevoPlan;
import viviendas.persistencia.Facade;

/**
 *
 * @author Admin
 */
public class GestorCrearPlan {

    public void crearNuevoPlan(DtoNuevoPlan dto) {
        int cantidadAños = dto.getAños();
        int año = 2010;
        Plan plan = new Plan();
        plan.setAñosPlan(cantidadAños);
        plan.setTipoPlan(dto.getTipo());
        plan.setNombre(dto.getNombre());
        plan.setNumeroViviendas(cantidadAños * dto.getCantidadViviendas());
        List<AñoPlan> añosPlan = new ArrayList<AñoPlan>();
        List<DistribucionProvincial> distribucionesProvinciales = new ArrayList<DistribucionProvincial>();
        List<SectorEconomico> sectoresEconomicos = Facade.getInstance().findAll(SectorEconomico.class);
        List<Operatoria> operatorias = Facade.getInstance().findAll(Operatoria.class);
        for (int i = 0; i < cantidadAños; i++) {
            AñoPlan añoPlan = new AñoPlan();
            añoPlan.setAño(año++);
            añoPlan.setCantViviendasAño(dto.getCantidadViviendas());
            añoPlan.setPlan(plan);
            for (DistribucionProvincial distribucionProvincial : dto.getDistribucionProvincial()) {
                for (Ciudad ciudad : distribucionProvincial.getProvincia().getListaCuidad()) {
                    for (SectorEconomico sectorEconomico : sectoresEconomicos) {
                        for (Operatoria operatoria : operatorias) {
                        }
                    }
                }

            }
            añoPlan.setListaDistribucionProvincial(distribucionesProvinciales);
        }
        plan.setListaAñoPlan(añosPlan);
    }

    public List<Provincia> buscarProvincias() {
        return Facade.getInstance().findAll(Provincia.class);
    }

    public List<TipoPlan> buscarTiposPlanes() {
        return Facade.getInstance().findAll(TipoPlan.class);
    }
}
