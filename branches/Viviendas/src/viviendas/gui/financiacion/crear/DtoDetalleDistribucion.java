package viviendas.gui.financiacion.crear;

import java.util.List;
import viviendas.entidades.flujo.DetalleDistribucionFinanciacion;

public class DtoDetalleDistribucion {

    private Boolean estaActivo;
    private List<DetalleDistribucionFinanciacion> detallesDistribucionesFinanciacion;

    public List<DetalleDistribucionFinanciacion> getDetallesDistribucionesFinanciacion() {
        return detallesDistribucionesFinanciacion;
    }

    public void setDetallesDistribucionesFinanciacion(List<DetalleDistribucionFinanciacion> detallesDistribucionesFinanciacion) {
        this.detallesDistribucionesFinanciacion = detallesDistribucionesFinanciacion;
    }

    public Boolean getEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(Boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

}
