package viviendas.gui.financiacion.crear;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.flujo.DetalleDistribucionFinanciacion;
import viviendas.entidades.flujo.DistribucionFinanciacion;
import viviendas.entidades.flujo.FuenteFondo;
import viviendas.entidades.flujo.UsoFondo;
import viviendas.modulos.financiacion.crear.DtoConstruccionFinanciacion;
import viviendas.persistencia.Facade;

public class CtrlModeloDetalleFinanciacion {

    private static int contador = 0;
    public DtoConstruccionFinanciacion crearDistribucion(Double porcentaje) {
        DtoConstruccionFinanciacion dtoContruccion = new DtoConstruccionFinanciacion();
        List<DtoDetalleDistribucion> dtoDetallesDistribucion = new ArrayList<DtoDetalleDistribucion>();
        String nombre = "Financiacion " + contador++;
        nombre += " - " + porcentaje;
        dtoContruccion.setNombre(nombre);

        DistribucionFinanciacion distribucionFinanciacion = new DistribucionFinanciacion();
        distribucionFinanciacion.setPorcentajeFinanciacion(porcentaje);
        List<UsoFondo> usosFondo = Facade.getInstance().findAll(UsoFondo.class);
        List<FuenteFondo> fuenteFondos = Facade.getInstance().findAll(FuenteFondo.class);
        String[] columnas = new String[fuenteFondos.size() + 2];
        columnas[0] = "SELECCIONADA";
        columnas[1] = "USO FONDO";
        for (int i = 0; i < fuenteFondos.size(); i++) {
            columnas[i + 2] = fuenteFondos.get(i).getNombre();
        }
        dtoContruccion.setColumas(columnas);
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
        dtoContruccion.setDtoDetallesDistribuciones(dtoDetallesDistribucion);
        return dtoContruccion;
    }
}
