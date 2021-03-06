package viviendas.modulos.Plan.crear;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.flujo.InversionParametro;
import viviendas.entidades.flujo.InversionPlan;
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
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.gui.Plan.crear.DtoNuevoPlan;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.VerifyDataException;
import viviendas.utiles.Utiles;

public class GestorCrearPlan {

    public void crearNuevoPlan(DtoNuevoPlan dto) throws PersistException, VerifyDataException {
        verificarEl100Porciento(sumarPorcentaje(dto.getDistribucionProvincial()));
        Facade.getInstance().beginTx();
        int cantidadAños = dto.getAños();
        int año = dto.getAñoInicial();
        Plan plan = new Plan();
        plan.setAniosPlan(cantidadAños);
        plan.setTipoPlan(dto.getTipo());
        plan.setNombre(dto.getNombre().toUpperCase());
        plan.setNumeroViviendas(cantidadAños * dto.getCantidadViviendas());
        List<AnioPlan> añosPlan = new ArrayList<AnioPlan>();
        Criterio criterioVigente = new Criterio("vigente", "=", true);
        List<SectorEconomico> sectoresEconomicos = Facade.getInstance().findByCriterio(SectorEconomico.class, criterioVigente);
        List<Double> porcentajes = new ArrayList<Double>();
        for (SectorEconomico sectorEconomico : sectoresEconomicos) {
            porcentajes.add(sectorEconomico.getParametro().getPorcenteaje());
        }
        try {
            Utiles.verificarPorcentajes(porcentajes);
        } catch (Exception ex) {
            throw new VerifyDataException("porcentaje Sectores Economicos");
        }
        List<Operatoria> operatorias = Facade.getInstance().findByCriterio(Operatoria.class, criterioVigente);
        porcentajes.clear();
        for (Operatoria operatoria : operatorias) {
            porcentajes.add(operatoria.getParametro().getPorcenteaje());
        }
        try {
            Utiles.verificarPorcentajes(porcentajes);
        } catch (Exception ex) {
            throw new VerifyDataException("porcentaje Operatorias");
        }
        porcentajes = null;
        crearInversion(plan);
        for (int i = 0; i < cantidadAños; i++) {
            AnioPlan añoPlan = new AnioPlan();
            añoPlan.setAnio(año++);
            añoPlan.setCantViviendasAnio(dto.getCantidadViviendas());
            añoPlan.setPlan(plan);
            List<DistribucionProvincial> distribucionesProvinciales = new ArrayList<DistribucionProvincial>(dto.getDistribucionProvincial());
            for (DistribucionProvincial distribucionProvincial : distribucionesProvinciales) {
                DistribucionProvincial distribucionProvincialNueva = new DistribucionProvincial(distribucionProvincial);
                for (Ciudad ciudad : distribucionProvincialNueva.getProvincia().getListaCuidad()) {

                    DistribucionCiudad distribucionCiudad = new DistribucionCiudad();
                    distribucionCiudad.setDistribucionProvincial(distribucionProvincialNueva);
                    distribucionCiudad.setCuidad(ciudad);
                    distribucionCiudad.setAnioPlan(añoPlan);
                    distribucionCiudad.setPorcentajeDistribucion(ciudad.getParametro().getPorcenteaje());
                    for (SectorEconomico sectorEconomico : sectoresEconomicos) {

                        DistribucionSector distribucionSector = new DistribucionSector();
                        distribucionSector.setAnioPlan(añoPlan);
                        distribucionSector.setDistribucionCiudad(distribucionCiudad);
                        distribucionSector.setSectorEconomico(sectorEconomico);
                        distribucionSector.setPorcentajeDistribucion(sectorEconomico.getParametro().getPorcenteaje());
                        for (Operatoria operatoria : operatorias) {

                            DistribucionOperatoria distribucionOperatoria = new DistribucionOperatoria();
                            distribucionOperatoria.setAnioPlan(añoPlan);
                            distribucionOperatoria.setDistribucionSector(distribucionSector);
                            distribucionOperatoria.setOperatoria(operatoria);
                            distribucionOperatoria.setPorcentajeDistribucion(operatoria.getParametro().getPorcenteaje());
                            Facade.getInstance().guardar(distribucionOperatoria);
                        }
                        distribucionSector.setAnioPlan(añoPlan);
                        Facade.getInstance().guardar(distribucionSector);
                    }
                    distribucionCiudad.setAnioPlan(añoPlan);
                    Facade.getInstance().guardar(distribucionCiudad);
                }
                distribucionProvincialNueva.setAnioPlan(añoPlan);
                Facade.getInstance().guardar(distribucionProvincialNueva);
            }
            añosPlan.add(añoPlan);
        }
        plan.setListaAnioPlan(añosPlan);
        for (InversionPlan inversionPlan : plan.getListaInversion()) {
            Facade.getInstance().guardar(inversionPlan);
        }
        Facade.getInstance().guardar(plan);
        Facade.getInstance().commitTx();
    }

    public List<Provincia> buscarProvincias() {
        return Facade.getInstance().findAll(Provincia.class);
    }

    public List<TipoPlan> buscarTiposPlanes() {
        return Facade.getInstance().findAll(TipoPlan.class);
    }

    public void verificarEl100Porciento(Double porcentaje) throws VerifyDataException {
        if (new Double(100).compareTo(porcentaje) != 0) {
            throw new VerifyDataException("porcentaje");
        }
    }

    public Double sumarPorcentaje(List<DistribucionProvincial> distribucion) {
        Double porcentajeTotal = new Double(0);
        for (DistribucionProvincial distribucionProvincial : distribucion) {
            if (distribucionProvincial.getPorcentajeDistribucion() != null) {
                porcentajeTotal += distribucionProvincial.getPorcentajeDistribucion();
            }
        }
        return porcentajeTotal;
    }

    private void crearInversion(Plan plan) throws VerifyDataException {
        List<InversionParametro> lista = Facade.getInstance().findAll(InversionParametro.class);
        if (lista.isEmpty()) {
            throw new VerifyDataException("Parametro inversion no creado");
        }
        plan.setListaInversion(new ArrayList<InversionPlan>());

        for (InversionParametro inversion : lista) {
            plan.getListaInversion().add(new InversionPlan(inversion));
        }
    }
}
