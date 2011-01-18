/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.modulos.flujoFondos;

import java.util.List;
import viviendas.entidades.flujo.ParametrosFlujoFondo;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.gui.dto.DtoParametrosFlujoFondo;
import viviendas.modulos.parametros.GestorParametro;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.BusinessOperationException;
import viviendas.utiles.Utiles;

/**
 *
 * @author Maximiliano.
 */
public class GestorFlujoFondos {

    public List<Plan> obtenerPlanes() {
        return Facade.getInstance().findAll(Plan.class);
    }

    public List obtenerProvincias() {
        List<Provincia> listado = Facade.getInstance().findAll(Provincia.class);
        Utiles.ordena(listado, "nombre");
        return listado;
    }

    public List obtenerCiudades() {
        Criterio criterio = new Criterio("vigente", "=", true);
        List<Ciudad> listado = Facade.getInstance().findByCriterio(Ciudad.class, criterio);
        Utiles.ordena(listado, "nombre");
        return listado;
    }

    public List obtenerSectoresEconomicos() {
        Criterio criterio = new Criterio("vigente", "=", true);
        List<SectorEconomico> listado = Facade.getInstance().findByCriterio(SectorEconomico.class, criterio);
        Utiles.ordena(listado, "nombre");
        return listado;
    }

    public List obtenerOperatorias() {
        Criterio criterio = new Criterio("vigente", "=", true);
        List<Operatoria> listado = Facade.getInstance().findByCriterio(Operatoria.class, criterio);
        Utiles.ordena(listado, "nombre");
        return listado;
    }

    public void guardar(DtoParametrosFlujoFondo _dto) throws BusinessOperationException {
        List<DistribucionOperatoria> listaDistribucionOperatoria;

        listaDistribucionOperatoria = GestorParametro.obtenerDistribucionOperatoria(_dto.getPlan(), null, _dto.getProvincia(), _dto.getCiudad(), _dto.getSectorEconomico(), _dto.getOperatoria());

        if (!listaDistribucionOperatoria.isEmpty()) {
            ParametrosFlujoFondo parametroFlujoFondo = new ParametrosFlujoFondo();
            parametroFlujoFondo.setDevCredTna(_dto.getDevCredTna());
            parametroFlujoFondo.setDevCredGastosOtorgamiento(_dto.getDevCredGastosOtorgamiento());
            parametroFlujoFondo.setDevCredGastosAdministrativos(_dto.getDevCredGastosAdministrativos());
            parametroFlujoFondo.setTitulosTna(_dto.getTitilosTna());
            parametroFlujoFondo.setTitulosAniosDevol(_dto.getTitulosAniosDevolucion());
            parametroFlujoFondo.setPerdidaIncobrables(_dto.getPerdidaIncobrables());

            try {
                Facade.getInstance().beginTx();
                Facade.getInstance().guardar(parametroFlujoFondo);
                for (DistribucionOperatoria distribucionOperatoria : listaDistribucionOperatoria) {
                    distribucionOperatoria.setParametrosFlujoFondo(parametroFlujoFondo);
                    Facade.getInstance().actualizar(distribucionOperatoria);
                }
                Facade.getInstance().commitTx();
            } catch (PersistException ex) {
                throw new BusinessOperationException("Error al guardar.");
            }
        }
    }
}
