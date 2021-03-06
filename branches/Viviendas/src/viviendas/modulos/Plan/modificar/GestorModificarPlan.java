/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.modulos.Plan.modificar;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.vivienda.AnioPlan;
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

    public void eliminarPlan(Plan plan) throws BusinessOperationException{
        try {
            Facade.getInstance().beginTx();
            Facade.getInstance().eliminar(plan);
            Facade.getInstance().commitTx();
        } catch (PersistException ex) {
            Facade.getInstance().rollBackTx();
            throw new BusinessOperationException("actualización");
        }
    }
    public Plan getPlan() {
        return plan_a_modificar;
    }

    public void cargarPlan(Plan plan) {

        plan_a_modificar = plan;
        for (AnioPlan aPlan : plan_a_modificar.getListaAnioPlan()) {
            aPlan.setDistribucionProvincia(gestor.listarDistProv(aPlan));
            aPlan.setDistribucionCiudad(gestor.listarDistCiud(aPlan));
            aPlan.setDistribucionSector(gestor.listarDistSEcono(aPlan));
            aPlan.setDistribucionOperatoria(gestor.listarDistOper(aPlan));
        }
    }

    public List<DistribucionProvincial> listarDistribucionProvincial(AnioPlan aplan) {
        return aplan.getDistribucionProvincia();
    }

    public List<DistribucionCiudad> listarDistribucionCiudad(AnioPlan aPlan, DistribucionProvincial dProvincial) {
        List<DistribucionCiudad> dc = new ArrayList<DistribucionCiudad>();
        for (DistribucionCiudad dCiudad : aPlan.getDistribucionCiudad()) {
            if (dCiudad.getDistribucionProvincial().equals(dProvincial)) {
                dc.add(dCiudad);
            }
        }
        return dc;
    }

    public List<DistribucionSector> listarDistribucionSector(AnioPlan aPlan, DistribucionCiudad dCiudad) {
        List<DistribucionSector> ds = new ArrayList<DistribucionSector>();
        for (DistribucionSector dSector : aPlan.getDistribucionSector()) {
            if (dSector.getDistribucionCiudad().equals(dCiudad)) {
                ds.add(dSector);
            }
        }
        return ds;
    }

    public List<DistribucionOperatoria> listarDistribucionOperatoria(AnioPlan aPlan, DistribucionSector dSector) {
        List<DistribucionOperatoria> dop = new ArrayList<DistribucionOperatoria>();
        for (DistribucionOperatoria dOperatoria : aPlan.getDistribucionOperatoria()) {
            if (dOperatoria.getDistribucionSector().equals(dSector)) {
                dop.add(dOperatoria);
            }
        }
        return dop;
    }

    public void removeDistribucion(AnioPlan aPlan, Object distribucion) {
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
            aPlan.getDistribucionCiudad().remove(distribucion);
        } else if (distribucion instanceof DistribucionProvincial) {
            for (DistribucionCiudad dCiudad : listarDistribucionCiudad(aPlan, (DistribucionProvincial) distribucion)) {
                removeDistribucion(aPlan, dCiudad);
            }
            aPlan.getDistribucionProvincia().remove(distribucion);
        }

        aEliminar.add(distribucion);
    }

    public void guardar() throws BusinessOperationException {
        int viviendas=0;
        for (AnioPlan anioPlan : plan_a_modificar.getListaAnioPlan()) {
            viviendas+= anioPlan.getCantViviendasAnio();
        }
        plan_a_modificar.setNumeroViviendas(viviendas);
        Facade.getInstance().beginTx();
        try {
            Facade.getInstance().actualizar(plan_a_modificar);
            for (AnioPlan aPlan : plan_a_modificar.getListaAnioPlan()) {
                Facade.getInstance().actualizar(aPlan);
            }
            if(aEliminar!=null)
                for (Object object : aEliminar) {
                    Facade.getInstance().eliminar(object);
                }
            Facade.getInstance().commitTx();
        } catch (PersistException persistException) {
            Facade.getInstance().rollBackTx();
            throw new BusinessOperationException("actualización");
        }
        if(aEliminar!=null)
            aEliminar.clear();

    }

    public List<Provincia> provinciasNoAsignadas(AnioPlan aplan) {
        List<Provincia> list = (new GestorProvincia()).obtenerProvincias();
        List<Provincia> noAsignadas = new ArrayList<Provincia>(list);
        for (Provincia provincia : list) {
            for (DistribucionProvincial dProvincial : aplan.getDistribucionProvincia()) {
                if (dProvincial.getProvincia().equals(provincia)) {
                    noAsignadas.remove(provincia);
                }
            }
        }
        viviendas.utiles.Utiles.ordena(noAsignadas, "nombre");
        return noAsignadas;
    }

    public List<Ciudad> ciudadesNoAsignadas(AnioPlan aPlan, List<DistribucionCiudad> dCiudadList){
        List<Ciudad> list, noAsignadas;
        list = (new GestorProvincia()).obtenerCiudades();
        noAsignadas = new ArrayList<Ciudad>(list);

        for (Ciudad ciudad : list) {
            if(dCiudadList==null)break;
            for (DistribucionCiudad dCiudad : dCiudadList) {
                if(dCiudad.getCuidad().equals(ciudad))
                    noAsignadas.remove(ciudad);
            }
        }
        viviendas.utiles.Utiles.ordena(noAsignadas, "nombre");
        return noAsignadas;
    }

    public List<SectorEconomico> sectoresNoAsignados(AnioPlan aPlan, List<DistribucionSector> dSectorList){
        List<SectorEconomico> list, noAsignadas;
        list = (new GestorSectorEconomico()).obtenerSectoresEconomicos();
        noAsignadas = new ArrayList<SectorEconomico>(list);
        for (SectorEconomico sector : list) {
            if(dSectorList==null)break;
            for (DistribucionSector dSector : dSectorList) {
                if(dSector.getSectorEconomico().equals(sector))
                    noAsignadas.remove(sector);
            }
        }

        viviendas.utiles.Utiles.ordena(noAsignadas, "nombre");
        return noAsignadas;
    }

    public List<Operatoria> operatoriasNoAsignadas(AnioPlan aPlan, List<DistribucionOperatoria> dOperatoriaList){
        List<Operatoria> list, noAsignadas;
        list = (new GestorOperatoria()).obtenerOperatorias();
        noAsignadas = new ArrayList<Operatoria>(list);
        for (Operatoria operatoria : list) {
            if(dOperatoriaList==null)break;
            for (DistribucionOperatoria dOperatoria : dOperatoriaList) {
                if(dOperatoria.getOperatoria().equals(operatoria))
                    noAsignadas.remove(operatoria);
            }
        }

        viviendas.utiles.Utiles.ordena(noAsignadas, "nombre");
        return noAsignadas;
    }

    public DistribucionProvincial addDistribucion(AnioPlan aPlan, Provincia provincia){
        if(aPlan == null || provincia == null)
            return null;
        DistribucionProvincial dProv = new DistribucionProvincial();
        dProv.setAnioPlan(aPlan);
        dProv.setProvincia(provincia);
        dProv.setPorcentajeDistribucion(0.0);
        return dProv;
    }

    public DistribucionCiudad addDistribucion(AnioPlan aPlan, Ciudad ciudad, DistribucionProvincial dProvincial){
        if(aPlan == null || ciudad == null || dProvincial == null)
            return null;
        DistribucionCiudad dCiud = new DistribucionCiudad();
        dCiud.setAnioPlan(aPlan);
        dCiud.setCuidad(ciudad);
        dCiud.setPorcentajeDistribucion(0.0);
        dCiud.setDistribucionProvincial(dProvincial);
        aPlan.getDistribucionCiudad().add(dCiud);
        return dCiud;
    }

    public DistribucionSector addDistribucion(AnioPlan aPlan, SectorEconomico sector, DistribucionCiudad dCiudad){
        if(aPlan == null || sector == null || dCiudad == null)
            return null;
        DistribucionSector dSec = new DistribucionSector();
        dSec.setAnioPlan(aPlan);
        dSec.setSectorEconomico(sector);
        dSec.setDistribucionCiudad(dCiudad);
        dSec.setPorcentajeDistribucion(0.0);
        aPlan.getDistribucionSector().add(dSec);
        return dSec;
    }

    public DistribucionOperatoria addDistribucion(AnioPlan aPlan, Operatoria operatoria, DistribucionSector dSector){
        if(aPlan==null || operatoria== null || dSector == null)
            return null;
        DistribucionOperatoria dOper = new DistribucionOperatoria();
        dOper.setAnioPlan(aPlan);
        dOper.setDistribucionSector(dSector);
        dOper.setOperatoria(operatoria);
        dOper.setPorcentajeDistribucion(0.0);
        aPlan.getDistribucionOperatoria().add(dOper);
        return dOper;
    }
}
