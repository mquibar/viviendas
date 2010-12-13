package viviendas.modulos.Plan;

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
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.gui.Plan.crear.DtoNuevoPlan;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;

public class GestorCrearPlan {

    public void crearNuevoPlan(DtoNuevoPlan dto) throws PersistException {
        Facade.getInstance().beginTx();
        int cantidadAños = dto.getAños();
        int año = 2010;
        Plan plan = new Plan();
        plan.setAñosPlan(cantidadAños);
        plan.setTipoPlan(dto.getTipo());
        plan.setNombre(dto.getNombre());
        plan.setNumeroViviendas(cantidadAños * dto.getCantidadViviendas());
        List<AñoPlan> añosPlan = new ArrayList<AñoPlan>();
        List<SectorEconomico> sectoresEconomicos = Facade.getInstance().findAll(SectorEconomico.class);
        List<Operatoria> operatorias = Facade.getInstance().findAll(Operatoria.class);
        for (int i = 0; i < cantidadAños; i++) {
            AñoPlan añoPlan = new AñoPlan();
            añoPlan.setAño(año++);
            añoPlan.setCantViviendasAño(dto.getCantidadViviendas());
            añoPlan.setPlan(plan);

            for (DistribucionProvincial distribucionProvincial : dto.getDistribucionProvincial()) {

                for (Ciudad ciudad : distribucionProvincial.getProvincia().getListaCuidad()) {

                    DistribucionCiudad distribucionCiudad = new DistribucionCiudad();
                    distribucionCiudad.setDistribucionProvincial(distribucionProvincial);
                    distribucionCiudad.setCuidad(ciudad);
                    distribucionCiudad.setAñoPlan(añoPlan);
                    distribucionCiudad.setPorcentajeDistribucion(3.1);
                    Facade.getInstance().guardar(distribucionCiudad);
                    for (SectorEconomico sectorEconomico : sectoresEconomicos) {

                        DistribucionSector distribucionSector = new DistribucionSector();
                        distribucionSector.setAñoPlan(añoPlan);
                        distribucionSector.setDistribucionCiudad(distribucionCiudad);
                        distribucionSector.setSectorEconomico(sectorEconomico);
                        distribucionSector.setPorcentajeDistribucion(4.1);
                        Facade.getInstance().guardar(distribucionSector);
                        for (Operatoria operatoria : operatorias) {

                            DistribucionOperatoria distribucionOperatoria = new DistribucionOperatoria();
                            distribucionOperatoria.setAñoPlan(añoPlan);
                            distribucionOperatoria.setDistribucionSector(distribucionSector);
                            distribucionOperatoria.setOperatoria(operatoria);
                            distribucionOperatoria.setPorcentajeDistribucion(5.1);
                            Facade.getInstance().guardar(distribucionOperatoria);
                        }
                    }
                }
            }
            añosPlan.add(añoPlan);
        }
        plan.setListaAñoPlan(añosPlan);
        Facade.getInstance().guardar(plan);
        Facade.getInstance().commitTx();
    }

    public List<Provincia> buscarProvincias() {
        return Facade.getInstance().findAll(Provincia.class);
    }

    public List<TipoPlan> buscarTiposPlanes() {
        return Facade.getInstance().findAll(TipoPlan.class);
    }
}
