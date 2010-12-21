/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author danamar
 */
public class GestorCrearFinanciacion {

    private List<DistribucionFinanciacion> distribucionesfinanciacion;
    private Financiacion financiacion;

    public GestorCrearFinanciacion(DistribucionOperatoria distribucionOperatoria) {
        distribucionesfinanciacion = new ArrayList<DistribucionFinanciacion>();
        financiacion = new Financiacion();
        financiacion.setDistribucionOperatoria(distribucionOperatoria);
    }

    public void crearDistribucion(Double porcentaje) {
        List<DtoDetalleDistribucion> dtoDetallesDistribucion = new ArrayList<DtoDetalleDistribucion>();
        String nombre = "Financiacion " + (distribucionesfinanciacion.size() + 1);
        nombre += " - " + porcentaje;
        DistribucionFinanciacion distribucionFinanciacion = new DistribucionFinanciacion();
        distribucionFinanciacion.setFinanciacion(financiacion);
        distribucionFinanciacion.setPorcentajeFinanciacion(porcentaje);
        List<UsoFondo> usosFondo = Facade.getInstance().findAll(UsoFondo.class);
        List<FuenteFondo> fuenteFondos = Facade.getInstance().findAll(FuenteFondo.class);
        for (UsoFondo usoFondo : usosFondo) {
            DtoDetalleDistribucion dto = new DtoDetalleDistribucion();
            dto.setEstaActivo(Boolean.TRUE);
            dto.setUsoFondo(usoFondo);
            List<DetalleDistribucionFinanciacion> listaDetalles = new ArrayList<DetalleDistribucionFinanciacion>();
            for (FuenteFondo fuenteFondo : fuenteFondos) {
                DetalleDistribucionFinanciacion detalle = new DetalleDistribucionFinanciacion();
                detalle.setDistribucionFinanciacion(distribucionFinanciacion);
                detalle.setFlujoFondo(fuenteFondo);
                detalle.setPorcentaje(0.0);
                detalle.setUsoFondo(usoFondo);
                listaDetalles.add(detalle);
            }
            dto.setDetallesDistribucionesFinanciacion(listaDetalles);
            dtoDetallesDistribucion.add(dto);
        }
        distribucionesfinanciacion.add(distribucionFinanciacion);
    }

    public Double calcularPorcentaje() {
        Double porcentajeTotal = 0.0;
        for (DistribucionFinanciacion distribucionFinanciacion : distribucionesfinanciacion) {
            porcentajeTotal += distribucionFinanciacion.getPorcentajeFinanciacion();
        }
        return porcentajeTotal;
    }
}
