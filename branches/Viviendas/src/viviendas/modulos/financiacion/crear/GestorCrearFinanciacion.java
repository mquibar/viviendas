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
        String nombre = "Financiacion " + (distribucionesfinanciacion.size() + 1);
        nombre += " - " + porcentaje;
        DistribucionFinanciacion distribucionFinanciacion = new DistribucionFinanciacion();
        distribucionFinanciacion.setFinanciacion(financiacion);
        distribucionFinanciacion.setPorcentajeFinanciacion(porcentaje);
        DetalleDistribucionFinanciacion detalleDistribucionFinanciacion = new DetalleDistribucionFinanciacion();
        List<UsoFondo> usosFondo = Facade.getInstance().findAll(UsoFondo.class);
        List<FuenteFondo> fuenteFondos = Facade.getInstance().findAll(FuenteFondo.class);
        for (FuenteFondo fuenteFondo : fuenteFondos) {
            
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
