package viviendas.modulos.financiacion.crear;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.flujo.Financiacion;
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
import viviendas.modulos.financiacion.modificar.GestorModificarFinanciacion;
import viviendas.modulos.parametros.GestorParametro;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;

public class GestorCrearFinanciacion {

    private Plan _plan;
    private final Financiacion _financiacion;

    public GestorCrearFinanciacion(Financiacion financiacion) {
        _financiacion = financiacion;
    }

    public void setPlan(Plan selected) {
        this._plan = selected;
    }

    public List<AnioPlan> obtenerAñosPlan() {
        Criterio criterioAños = new Criterio("plan", "=", _plan);
        return Facade.getInstance().findByCriterio(AnioPlan.class, criterioAños);
    }

    public List<Ciudad> obtenerCiudades() {
        List<Ciudad> listaCiudades = new ArrayList<Ciudad>();
        for (AnioPlan anioPlan : _plan.getListaAnioPlan()) {
            for (DistribucionCiudad distribucionCiudad : anioPlan.getDistribucionCiudad()) {
                if (!listaCiudades.contains(distribucionCiudad.getCuidad())) {
                    listaCiudades.add(distribucionCiudad.getCuidad());
                }
            }
        }
        return listaCiudades;
    }

    public List<Operatoria> obtenerOperatorias() {
        List<Operatoria> listaOperatoria = new ArrayList<Operatoria>();
        for (AnioPlan anioPlan : _plan.getListaAnioPlan()) {
            for (DistribucionOperatoria distribucionOperatoria : anioPlan.getDistribucionOperatoria()) {
                if (!listaOperatoria.contains(distribucionOperatoria.getOperatoria())) {
                    listaOperatoria.add(distribucionOperatoria.getOperatoria());
                }
            }
        }
        return listaOperatoria;
    }

    public List<Provincia> obtenerProvincias() {
        List<Provincia> listaProvincia = new ArrayList<Provincia>();
        for (AnioPlan anioPlan : _plan.getListaAnioPlan()) {
            for (DistribucionProvincial distribucionProvincia : anioPlan.getDistribucionProvincia()) {
                if (!listaProvincia.contains(distribucionProvincia.getProvincia())) {
                    listaProvincia.add(distribucionProvincia.getProvincia());
                }
            }
        }
        return listaProvincia;
    }

    public List<SectorEconomico> obtenerSectoresEconomicos() {
        List<SectorEconomico> listaSector = new ArrayList<SectorEconomico>();
        for (AnioPlan anioPlan : _plan.getListaAnioPlan()) {
            for (DistribucionSector distribucionSector : anioPlan.getDistribucionSector()) {
                if (!listaSector.contains(distribucionSector.getSectorEconomico())) {
                    listaSector.add(distribucionSector.getSectorEconomico());
                }
            }
        }
        return listaSector;
    }

    public List<Plan> obtenerPlanes() {
        return Facade.getInstance().findAll(Plan.class);
    }

    public void aplicarFinanciacion(AnioPlan anio, Provincia provincia, Ciudad ciudad, SectorEconomico sector, Operatoria operatoria) {
        try {
            Facade.getInstance().beginTx();
            List<DistribucionOperatoria> listaDistribucionOperatoria = GestorParametro.obtenerDistribucionOperatoria(_plan, anio, provincia, ciudad, sector, operatoria);
            System.out.println("Filtrado Combinacion: " + listaDistribucionOperatoria.size());
            for (DistribucionOperatoria distribucionOperatoria : listaDistribucionOperatoria) {
                if (distribucionOperatoria.getFinanciacion() != null) {
                    Facade.getInstance().eliminar(distribucionOperatoria.getFinanciacion());
                }
                Financiacion financiacion = new Financiacion(_financiacion);
                financiacion.setDistribucionOperatoria(distribucionOperatoria);
                distribucionOperatoria.setFinanciacion(financiacion);
                financiacion.setNombre(GestorModificarFinanciacion.getNombreCompletoCombinacion(distribucionOperatoria));
                Facade.getInstance().guardar(financiacion);
            }
            Facade.getInstance().commitTx();
        } catch (PersistException ex) {
            ex.printStackTrace();
        }
    }

    public int getCantidadRegistros(AnioPlan anio, Provincia provincia, Ciudad ciudad, SectorEconomico sector, Operatoria operatoria) {
        return GestorParametro.obtenerDistribucionOperatoria(_plan, anio, provincia, ciudad, sector, operatoria).size();
    }
}
