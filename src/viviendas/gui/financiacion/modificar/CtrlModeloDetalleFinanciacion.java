package viviendas.gui.financiacion.modificar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import viviendas.entidades.flujo.DetalleDistribucionFinanciacion;
import viviendas.entidades.flujo.DistribucionFinanciacion;
import viviendas.entidades.flujo.UsoFondo;

public class CtrlModeloDetalleFinanciacion {

    public List<DtoConstruccionFinanciacion> crearDistribucion(List<DistribucionFinanciacion> listaDistribucion) {
        List<DtoConstruccionFinanciacion> listaDtoContruccion = new ArrayList<DtoConstruccionFinanciacion>();
        for (DistribucionFinanciacion distribucionFinanciacion : listaDistribucion) {
            listaDtoContruccion.add(crearDistribucion(distribucionFinanciacion));
        }

        return listaDtoContruccion;

    }

    public DtoConstruccionFinanciacion crearDistribucion(DistribucionFinanciacion distribucion) {
        HashMap<UsoFondo, List<DetalleDistribucionFinanciacion>> hashMap = new HashMap<UsoFondo, List<DetalleDistribucionFinanciacion>>();
        DtoConstruccionFinanciacion dtoConstruccion = new DtoConstruccionFinanciacion();
        String nombre = "Financiación ";
        nombre += " - " + distribucion.getPorcentajeFinanciacion();
        dtoConstruccion.setNombre(nombre);
        for (int i = 0; i < distribucion.getDetallesDistribucionesFinanciacion().size(); i++) {
            DetalleDistribucionFinanciacion detalleDistribucionFinanciacion = distribucion.getDetallesDistribucionesFinanciacion().get(i);
            if (hashMap.get(detalleDistribucionFinanciacion.getUsoFondo()) == null) {
                hashMap.put(detalleDistribucionFinanciacion.getUsoFondo(), new ArrayList<DetalleDistribucionFinanciacion>());
                hashMap.get(detalleDistribucionFinanciacion.getUsoFondo()).add(detalleDistribucionFinanciacion);
            } else {
                hashMap.get(detalleDistribucionFinanciacion.getUsoFondo()).add(detalleDistribucionFinanciacion);
            }

        }
        List<DtoDetalleDistribucion> listaDtoDetalle = new ArrayList<DtoDetalleDistribucion>();
        for (UsoFondo usoFondo : hashMap.keySet()) {
            DtoDetalleDistribucion dto = new DtoDetalleDistribucion();
            dto.setDetallesDistribucionesFinanciacion(hashMap.get(usoFondo));
            dto.setEstaActivo(Boolean.TRUE);
            listaDtoDetalle.add(dto);
        }
        for (UsoFondo usoFondo : hashMap.keySet()) {
            String[] columnas = new String[hashMap.get(usoFondo).size() + 2];
            columnas[0] = "SELECCIONADA";
            columnas[1] = "USO FONDO";
            for (int i = 0; i < hashMap.get(usoFondo).size(); i++) {
                columnas[i + 2] = hashMap.get(usoFondo).get(i).getFuenteFondo().getNombre();
            }
            dtoConstruccion.setColumas(columnas);
            break;
        }

        dtoConstruccion.setDtoDetallesDistribuciones(listaDtoDetalle);
        return dtoConstruccion;
    }
}
