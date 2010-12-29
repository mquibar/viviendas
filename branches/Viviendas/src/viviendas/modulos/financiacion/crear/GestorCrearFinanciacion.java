package viviendas.modulos.financiacion.crear;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.flujo.DetalleDistribucionFinanciacion;
import viviendas.entidades.flujo.DistribucionFinanciacion;
import viviendas.entidades.flujo.Financiacion;
import viviendas.entidades.flujo.FuenteFondo;
import viviendas.entidades.flujo.UsoFondo;
import viviendas.entidades.vivienda.DistribucionOperatoria;
import viviendas.gui.financiacion.crear.DtoDetalleDistribucion;
import viviendas.modulos.FuentesFondos.GestorFuentesFondos;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;

public class GestorCrearFinanciacion {

    private Financiacion financiacion;

    public GestorCrearFinanciacion() {
    }

    public DistribucionFinanciacion crearDistribucion(Double porcentaje) {
        //TODO verificar 100% uso fondo y fuentefondo en los parametros
        DistribucionFinanciacion distribucionFinanciacion = new DistribucionFinanciacion();
        distribucionFinanciacion.setFinanciacion(financiacion);
        financiacion.getDistribucionesFinanciacion().add(distribucionFinanciacion);
        distribucionFinanciacion.setDetallesDistribucionesFinanciacion(new ArrayList<DetalleDistribucionFinanciacion>());
        distribucionFinanciacion.setPorcentajeFinanciacion(porcentaje);
        Criterio criterioVigente = new Criterio("vigente", "=", true);
        List<UsoFondo> usosFondo = Facade.getInstance().findByCriterio(UsoFondo.class, criterioVigente);
        List<FuenteFondo> fuenteFondos = Facade.getInstance().findByCriterio(FuenteFondo.class, criterioVigente);
        for (UsoFondo usoFondo : usosFondo) {
            List<DetalleDistribucionFinanciacion> listaDetalles = new ArrayList<DetalleDistribucionFinanciacion>();
            FuenteFondo fuenteOtrosAportes = null;
            for (FuenteFondo fuenteFondo : fuenteFondos) {
                if (fuenteFondo.getNombre().equals(GestorFuentesFondos.OTROSAPORTES)) {
                    fuenteOtrosAportes = fuenteFondo;
                } else {
                    DetalleDistribucionFinanciacion detalle = new DetalleDistribucionFinanciacion();
                    detalle.setDistribucionFinanciacion(distribucionFinanciacion);
                    detalle.setFuenteFondo(fuenteFondo);
                    detalle.setPorcentaje(0.0);
                    detalle.setUsoFondo(usoFondo);
                    listaDetalles.add(detalle);

                }
            }
            DetalleDistribucionFinanciacion detalle = new DetalleDistribucionFinanciacion();
            detalle.setDistribucionFinanciacion(distribucionFinanciacion);
            detalle.setFuenteFondo(fuenteOtrosAportes);
            detalle.setPorcentaje(100.0);
            detalle.setUsoFondo(usoFondo);
            listaDetalles.add(detalle);
            distribucionFinanciacion.getDetallesDistribucionesFinanciacion().addAll(listaDetalles);
        }
        return distribucionFinanciacion;
    }

    public String getNombreCompletoCombinacion() {
        String año = financiacion.getDistribucionOperatoria().getAñoPlan().getAño().toString();
        String provincia = financiacion.getDistribucionOperatoria().getDistribucionSector().getDistribucionCiudad().getDistribucionProvincial().getProvincia().getNombre();
        String ciudad = financiacion.getDistribucionOperatoria().getDistribucionSector().getDistribucionCiudad().getCuidad().getNombre();
        String sector = financiacion.getDistribucionOperatoria().getDistribucionSector().getSectorEconomico().getNombre();
        String operatoria = financiacion.getDistribucionOperatoria().getOperatoria().getNombre();
        return año + " - " + provincia + " - " + ciudad + " - " + sector + " - " + operatoria;
    }

    public void actualizarPorcentaje(List<DtoDetalleDistribucion> listaDto) {
        for (DtoDetalleDistribucion dtoDetalleDistribucion : listaDto) {
            DetalleDistribucionFinanciacion detalle = null;
            Double total = 0.0;
            for (DetalleDistribucionFinanciacion detalleDistribucionFinanciacion : dtoDetalleDistribucion.getDetallesDistribucionesFinanciacion()) {
                if (!detalleDistribucionFinanciacion.getFuenteFondo().getNombre().equals(GestorFuentesFondos.OTROSAPORTES)) {
                    total += detalleDistribucionFinanciacion.getPorcentaje();
                } else {
                    detalle = detalleDistribucionFinanciacion;
                }
            }
            detalle.setPorcentaje(100.0 - total);
        }
    }

    public List<DistribucionFinanciacion> cargarFinanciacion(DistribucionOperatoria distribucionOperatoria) {
        Criterio criterio = new Criterio("distribucionOperatoria", "=", distribucionOperatoria);
        List<Financiacion> listaFinaciacion = Facade.getInstance().findByCriterio(Financiacion.class, criterio);
        if (listaFinaciacion.isEmpty()) {
            financiacion = new Financiacion();
            financiacion.setDistribucionOperatoria(distribucionOperatoria);
            financiacion.setNombre(getNombreCompletoCombinacion());
            financiacion.setDistribucionesFinanciacion(new ArrayList<DistribucionFinanciacion>());
            return null;
        } else {
            financiacion = listaFinaciacion.get(0);
            return listaFinaciacion.get(0).getDistribucionesFinanciacion();

        }

    }

    public Double calcularPorcentajeTotal() {
        Double total = 0.0;
        for (DistribucionFinanciacion distribucionFinanciacion : financiacion.getDistribucionesFinanciacion()) {
            total += distribucionFinanciacion.getPorcentajeFinanciacion();
        }
        return total;
    }

    public void guardarFinanciacion(List<DtoDetalleDistribucion> listaSeleccionada) throws PersistException {
        Facade.getInstance().beginTx();
        for (DtoDetalleDistribucion dtoDetalleDistribucion : listaSeleccionada) {
                if (!dtoDetalleDistribucion.getEstaActivo()) {
                    listaSeleccionada.get(0).getDetallesDistribucionesFinanciacion().get(0).getDistribucionFinanciacion().getDetallesDistribucionesFinanciacion().removeAll(dtoDetalleDistribucion.getDetallesDistribucionesFinanciacion());
                }
        }
        if (financiacion.getId() == null) {
            Facade.getInstance().guardar(financiacion);
        } else {
            Facade.getInstance().actualizar(financiacion);
        }
        Facade.getInstance().commitTx();
    }
}
