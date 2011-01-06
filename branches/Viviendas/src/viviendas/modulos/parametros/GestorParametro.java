package viviendas.modulos.parametros;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.CriterioCompuesto;
import viviendas.persistencia.Facade;

public class GestorParametro {

    public static List<DistribucionOperatoria> obtenerDistribucionOperatoria(Plan plan, Provincia provincia, Ciudad ciudad, SectorEconomico sector, Operatoria operatoria) {
        List<Criterio> listaCriterio = new ArrayList<Criterio>();
        if (plan == null) {
            throw new VerifyError("plan inexistente");
        }
        Criterio criterioPlan = new Criterio("anioPlan.plan", "=", plan);
        listaCriterio.add(criterioPlan);
        if (provincia != null) {
            Criterio criterioProvincia = new Criterio("distribucionSector.distribucionCiudad.distribucionProvincial.provincia", "=", provincia);
            listaCriterio.add(criterioProvincia);
        }
        if (ciudad != null) {
            Criterio criterioCiudad = new Criterio("distribucionSector.distribucionCiudad.cuidad", "=", ciudad);
            listaCriterio.add(criterioCiudad);
        }
        if (sector != null) {
            Criterio criterioSector = new Criterio("distribucionSector.sectorEconomico", "=", sector);
            listaCriterio.add(criterioSector);
        }
        if (operatoria != null) {
            Criterio criterioOperatoria = new Criterio("operatoria", "=", operatoria);
            listaCriterio.add(criterioOperatoria);
        }
        Criterio criterio = listaCriterio.get(0);
        if (listaCriterio.size() > 1) {
            CriterioCompuesto criterioCompuesto = new CriterioCompuesto(criterio, "AND", listaCriterio.get(1));
            for (int i = 2; i < listaCriterio.size(); i++) {
                criterioCompuesto.add(listaCriterio.get(i), "AND");
            }
            criterio = criterioCompuesto;
        }
        return Facade.getInstance().findByCriterio(DistribucionOperatoria.class, criterio);
    }
}
