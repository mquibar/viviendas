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
import viviendas.persistencia.Facade;

public class GestorCrearFinanciacion {

    private List<DistribucionFinanciacion> distribucionesfinanciacion;
    private Financiacion financiacion;

    public GestorCrearFinanciacion(DistribucionOperatoria distribucionOperatoria) {
        distribucionesfinanciacion = new ArrayList<DistribucionFinanciacion>();
        financiacion = new Financiacion();
        financiacion.setDistribucionOperatoria(distribucionOperatoria);
    }

    public void crearDistribucion(Double porcentaje) {
        DistribucionFinanciacion distribucionFinanciacion = new DistribucionFinanciacion();
        distribucionFinanciacion.setFinanciacion(financiacion);
        distribucionFinanciacion.setPorcentajeFinanciacion(porcentaje);
        List<UsoFondo> usosFondo = Facade.getInstance().findAll(UsoFondo.class);
        List<FuenteFondo> fuenteFondos = Facade.getInstance().findAll(FuenteFondo.class);
        for (UsoFondo usoFondo : usosFondo) {
            List<DetalleDistribucionFinanciacion> listaDetalles = new ArrayList<DetalleDistribucionFinanciacion>();
            for (FuenteFondo fuenteFondo : fuenteFondos) {
                DetalleDistribucionFinanciacion detalle = new DetalleDistribucionFinanciacion();
                detalle.setDistribucionFinanciacion(distribucionFinanciacion);
                detalle.setFlujoFondo(fuenteFondo);
                detalle.setPorcentaje(0.0);
                detalle.setUsoFondo(usoFondo);
                listaDetalles.add(detalle);
            }
            distribucionFinanciacion.setDetallesDistribucionesFinanciacion(listaDetalles);
        }
    }

    public String getNombreCompletoCombinacion() {
        DistribucionOperatoria distribucion = financiacion.getDistribucionOperatoria();
        String año = distribucion.getAñoPlan().getAño().toString();
        String provincia = distribucion.getDistribucionSector().getDistribucionCiudad().getDistribucionProvincial().getProvincia().getNombre();
        String ciudad = distribucion.getDistribucionSector().getDistribucionCiudad().getCuidad().getNombre();
        String sector = distribucion.getDistribucionSector().getSectorEconomico().getNombre();
        String operatoria = distribucion.getOperatoria().getNombre();
        return año + " - " + provincia + " - " + ciudad + " - " + sector + " - " + operatoria;
    }

    public void actualizarPorcentaje(List<DtoDetalleDistribucion> listaDto) {
        for (DtoDetalleDistribucion dtoDetalleDistribucion : listaDto) {
            DetalleDistribucionFinanciacion detalle = null;
            Double total = 0.0;
            for (DetalleDistribucionFinanciacion detalleDistribucionFinanciacion : dtoDetalleDistribucion.getDetallesDistribucionesFinanciacion()) {
                if (!detalleDistribucionFinanciacion.getUsoFondo().getNombre().equals("OTROS APORTES")) {
                    total += detalleDistribucionFinanciacion.getPorcentaje();
                } else {
                    detalle = detalleDistribucionFinanciacion;
                }
            }
            detalle.setPorcentaje(100.0 - total);
        }
    }
}
