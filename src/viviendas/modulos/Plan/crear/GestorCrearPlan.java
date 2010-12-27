package viviendas.modulos.Plan.crear;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.flujo.InversionParametro;
import viviendas.entidades.flujo.InversionPlan;
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
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.gui.Plan.crear.DtoNuevoPlan;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.VerifyDataException;

public class GestorCrearPlan {

    public void crearNuevoPlan(DtoNuevoPlan dto) throws PersistException, VerifyDataException {
        verificarEl100Porciento(sumarPorcentaje(dto.getDistribucionProvincial()));
        Facade.getInstance().beginTx();
        int cantidadAños = dto.getAños();
        int año = dto.getAñoInicial();
        Plan plan = new Plan();
        plan.setAñosPlan(cantidadAños);
        plan.setTipoPlan(dto.getTipo());
        plan.setNombre(dto.getNombre().toUpperCase());
        plan.setNumeroViviendas(cantidadAños * dto.getCantidadViviendas());
        List<AñoPlan> añosPlan = new ArrayList<AñoPlan>();

        List<SectorEconomico> sectoresEconomicos = Facade.getInstance().findAll(SectorEconomico.class);
        List<Operatoria> operatorias = Facade.getInstance().findAll(Operatoria.class);

        for (int i = 0; i < cantidadAños; i++) {
            AñoPlan añoPlan = new AñoPlan();
            añoPlan.setAño(año++);
            añoPlan.setCantViviendasAño(dto.getCantidadViviendas());
            añoPlan.setPlan(plan);
            List<DistribucionProvincial> distribucionesProvinciales = new ArrayList<DistribucionProvincial>(dto.getDistribucionProvincial());
            for (DistribucionProvincial distribucionProvincial : distribucionesProvinciales) {
                DistribucionProvincial distribucionProvincialNueva = new DistribucionProvincial(distribucionProvincial);
                for (Ciudad ciudad : distribucionProvincialNueva.getProvincia().getListaCuidad()) {

                    DistribucionCiudad distribucionCiudad = new DistribucionCiudad();
                    distribucionCiudad.setDistribucionProvincial(distribucionProvincialNueva);
                    distribucionCiudad.setCuidad(ciudad);
                    distribucionCiudad.setAñoPlan(añoPlan);
//                    distribucionCiudad.setPorcentajeDistribucion(ciudad.getParametro().getPorcenteaje());
                    distribucionCiudad.setPorcentajeDistribucion(33.333);
                    for (SectorEconomico sectorEconomico : sectoresEconomicos) {

                        DistribucionSector distribucionSector = new DistribucionSector();
                        distribucionSector.setAñoPlan(añoPlan);
                        distribucionSector.setDistribucionCiudad(distribucionCiudad);
                        distribucionSector.setSectorEconomico(sectorEconomico);
//                        distribucionSector.setPorcentajeDistribucion(sectorEconomico.getParametro().getPorcenteaje());
                        distribucionSector.setPorcentajeDistribucion(33.333);
                        for (Operatoria operatoria : operatorias) {

                            DistribucionOperatoria distribucionOperatoria = new DistribucionOperatoria();
                            distribucionOperatoria.setAñoPlan(añoPlan);
                            distribucionOperatoria.setDistribucionSector(distribucionSector);
                            distribucionOperatoria.setOperatoria(operatoria);
//                            distribucionOperatoria.setPorcentajeDistribucion(operatoria.getParametro().getPorcenteaje());
                            distribucionOperatoria.setPorcentajeDistribucion(33.333);
                            distribucionOperatoria.setAñoPlan(añoPlan);
                            Facade.getInstance().guardar(distribucionOperatoria);
                        }
                        distribucionSector.setAñoPlan(añoPlan);
                        Facade.getInstance().guardar(distribucionSector);
                    }
                    distribucionCiudad.setAñoPlan(añoPlan);
                    Facade.getInstance().guardar(distribucionCiudad);
                }
                distribucionProvincialNueva.setAñoPlan(añoPlan);
                Facade.getInstance().guardar(distribucionProvincialNueva);
            }
            añosPlan.add(añoPlan);
        }
        plan.setListaAñoPlan(añosPlan);
        crearInversion(plan);
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

    private void crearInversion(Plan plan){
        List<InversionParametro> lista = Facade.getInstance().findAll(InversionParametro.class);
        plan.setListaInversion(new ArrayList<InversionPlan>());
        for (InversionParametro inversion : lista) {
            plan.getListaInversion().add(new InversionPlan(inversion));
        }
    }
}
