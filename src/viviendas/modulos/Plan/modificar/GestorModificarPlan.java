/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.modulos.Plan.modificar;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.vivienda.AñoPlan;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionCiudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.DistribucionSector;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.modulos.Operatoria.GestorOperatoria;
import viviendas.modulos.Plan.consultar.GestorConsultarPlan;
import viviendas.modulos.SegmentoEconomico.GestorSectorEconomico;
import viviendas.modulos.provincia.GestorProvincia;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author Manuel
 */
public class GestorModificarPlan {

    private Plan plan_a_modificar;
    private GestorConsultarPlan gestor;
    private List aEliminar = null;

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

        plan_a_modificar = plan;
        for (AñoPlan aPlan : plan_a_modificar.getListaAñoPlan()) {
            aPlan.setListaDistribucionProvincial(gestor.listarDistProv(aPlan));
            aPlan.setListaProvinciaCiudad(gestor.listarDistCiud(aPlan));
            aPlan.setDistribucionSector(gestor.listarDistSEcono(aPlan));
            aPlan.setDistribucionOperatoria(gestor.listarDistOper(aPlan));
        }
    }

    public List<DistribucionProvincial> listarDistribucionProvincial(AñoPlan aplan) {
        return aplan.getListaDistribucionProvincial();
    }

    public List<DistribucionCiudad> listarDistribucionCiudad(AñoPlan aPlan, DistribucionProvincial dProvincial) {
        List<DistribucionCiudad> dc = new ArrayList<DistribucionCiudad>();
        for (DistribucionCiudad dCiudad : aPlan.getListaProvinciaCiudad()) {
            if (dCiudad.getDistribucionProvincial().equals(dProvincial)) {
                dc.add(dCiudad);
            }
        }
        return dc;
    }

    public List<DistribucionSector> listarDistribucionSector(AñoPlan aPlan, DistribucionCiudad dCiudad) {
        List<DistribucionSector> ds = new ArrayList<DistribucionSector>();
        for (DistribucionSector dSector : aPlan.getDistribucionSector()) {
            if (dSector.getDistribucionCiudad().equals(dCiudad)) {
                ds.add(dSector);
            }
        }
        return ds;
    }

    public List<DistribucionOperatoria> listarDistribucionOperatoria(AñoPlan aPlan, DistribucionSector dSector) {
        List<DistribucionOperatoria> dop = new ArrayList<DistribucionOperatoria>();
        for (DistribucionOperatoria dOperatoria : aPlan.getDistribucionOperatoria()) {
            if (dOperatoria.getDistribucionSector().equals(dSector)) {
                dop.add(dOperatoria);
            }
        }
        return dop;
    }

    public void removeDistribucion(AñoPlan aPlan, Object distribucion) {
        if (distribucion == null) {
            return;
        }
        if (aEliminar == null) {
            aEliminar = new ArrayList();
        }
        if (distribucion instanceof DistribucionOperatoria) {
            aPlan.getDistribucionOperatoria().remove(distribucion);
        } else if (distribucion instanceof DistribucionSector) {
            for (DistribucionOperatoria dOperatoria : listarDistribucionOperatoria(aPlan, (DistribucionSector) distribucion)) {
                removeDistribucion(aPlan, dOperatoria);
            }
            aPlan.getDistribucionSector().remove(distribucion);
        } else if (distribucion instanceof DistribucionCiudad) {
            for (DistribucionSector dSector : listarDistribucionSector(aPlan, (DistribucionCiudad) distribucion)) {
                removeDistribucion(aPlan, dSector);
            }
            aPlan.getListaProvinciaCiudad().remove(distribucion);
        } else if (distribucion instanceof DistribucionProvincial) {
            for (DistribucionCiudad dCiudad : listarDistribucionCiudad(aPlan, (DistribucionProvincial) distribucion)) {
                removeDistribucion(aPlan, dCiudad);
            }
            aPlan.getListaDistribucionProvincial().remove(distribucion);
        }

        aEliminar.add(distribucion);
    }

    public void guardar() throws BusinessOperationException {
        Facade.getInstance().beginTx();
        try {
            Facade.getInstance().actualizar(plan_a_modificar);
            for (AñoPlan aPlan : plan_a_modificar.getListaAñoPlan()) {
                Facade.getInstance().actualizar(aPlan);
            }
            for (Object object : aEliminar) {
                Facade.getInstance().eliminar(object);
            }
            Facade.getInstance().commitTx();
        } catch (PersistException persistException) {
            Facade.getInstance().rollBackTx();
            throw new BusinessOperationException("actualización");
        }

    }

    public List<Provincia> provinciasNoAsignadas(AñoPlan aplan) {
        List<Provincia> list = (new GestorProvincia()).obtenerProvincias();
        List<Provincia> noAsignadas = new ArrayList<Provincia>(list);
        for (Provincia provincia : list) {
            for (DistribucionProvincial dProvincial : aplan.getListaDistribucionProvincial()) {
                if (dProvincial.getProvincia().equals(provincia)) {
                    noAsignadas.remove(provincia);
                }
            }
        }
        viviendas.utiles.Utiles.ordena(noAsignadas, "nombre");
        return noAsignadas;
    }

    public List<Ciudad> ciudadesNoAsignadas(AñoPlan aPlan){
        List<Ciudad> list, noAsignadas;
        list = (new GestorProvincia()).obtenerCiudades();
        noAsignadas = new ArrayList<Ciudad>(list);

        for (Ciudad ciudad : list) {
            for (DistribucionCiudad dCiudad : aPlan.getListaProvinciaCiudad()) {
                if(dCiudad.getCuidad().equals(ciudad))
                    noAsignadas.remove(ciudad);
            }
        }
        viviendas.utiles.Utiles.ordena(noAsignadas, "nombre");
        return noAsignadas;
    }

    public List<SectorEconomico> sectoresNoAsignados(AñoPlan aPlan){
        List<SectorEconomico> list, noAsignadas;
        list = (new GestorSectorEconomico()).obtenerSectoresEconomicos();
        noAsignadas = new ArrayList<SectorEconomico>(list);
        for (SectorEconomico sector : list) {
            for (DistribucionSector dSector : aPlan.getDistribucionSector()) {
                if(dSector.getSectorEconomico().equals(sector))
                    noAsignadas.remove(sector);
            }
        }

        viviendas.utiles.Utiles.ordena(noAsignadas, "nombre");
        return noAsignadas;
    }

    public List<Operatoria> operatoriasNoAsignadas(AñoPlan aPlan){
        List<Operatoria> list, noAsignadas;
        list = (new GestorOperatoria()).obtenerOperatorias();
        noAsignadas = new ArrayList<Operatoria>(list);
        for (Operatoria operatoria : list) {
            for (DistribucionOperatoria dOperatoria : aPlan.getDistribucionOperatoria()) {
                if(dOperatoria.getOperatoria().equals(operatoria))
                    noAsignadas.remove(operatoria);
            }
        }

        viviendas.utiles.Utiles.ordena(noAsignadas, "nombre");
        return noAsignadas;
    }
}
