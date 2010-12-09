/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.Plan;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.gui.Plan.DtoNuevoPlan;


/**
 *
 * @author Admin
 */
public class GestorPlan {

    public void crearNuevoPlan(DtoNuevoPlan dto) {
        int cantidadAños = dto.getAños() - 2010;
        int año = 2010;
        Plan plan = new Plan();
        plan.setAñosPlan(cantidadAños);
        plan.setTipoPlan(dto.getTipo());
        plan.setNombre(dto.getNombre());
        plan.setNumeroViviendas(cantidadAños * dto.getCantidadViviendas());
        List<AñoPlan> añosPlan = new ArrayList<AñoPlan>();
        List<DistribucionProvincial> distribucionesProvinciales = new ArrayList<DistribucionProvincial>();
        for (int i = 0; i < cantidadAños; i++) {
            AñoPlan añoPlan = new AñoPlan();
            añoPlan.setAño(año++);
            añoPlan.setCantViviendasAño(dto.getCantidadViviendas());
            añoPlan.setPlan(plan);
            for (Provincia provincia : dto.getProvincias()) {
                DistribucionProvincial distribucionProvincial = new DistribucionProvincial();
                distribucionProvincial.setAñoPlan(añoPlan);
                distribucionProvincial.setProvincia(provincia);
                distribucionProvincial.setPorcentajeDistribucion(Double.NaN);
                distribucionesProvinciales.add(distribucionProvincial);
            }
            añoPlan.setListaDistribucionProvincial(distribucionesProvinciales);
        }
        plan.setListaAñoPlan(añosPlan);
    }

}
