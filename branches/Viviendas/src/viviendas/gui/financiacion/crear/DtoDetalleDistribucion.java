package viviendas.gui.financiacion.crear;

import java.util.List;
import viviendas.entidades.flujo.DetalleDistribucionFinanciacion;
import viviendas.entidades.flujo.UsoFondo;

public class DtoDetalleDistribucion {

    private Boolean estaActivo;
    private UsoFondo usoFondo;
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

    public UsoFondo getUsoFondo() {
        return usoFondo;
    }

    public void setUsoFondo(UsoFondo usoFondo) {
        this.usoFondo = usoFondo;
    }
}
