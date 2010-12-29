/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.financiacion.crear;

import java.util.List;
import viviendas.gui.financiacion.crear.DtoDetalleDistribucion;

/**
 *
 * @author danamar
 */
public class DtoConstruccionFinanciacion {
    private String nombre;
    private List<DtoDetalleDistribucion> dtoDetallesDistribuciones;
    private String[] columas ;

    public String[] getColumas() {
        return columas;
    }

    public void setColumas(String[] columas) {
        this.columas = columas;
    }

    public List<DtoDetalleDistribucion> getDtoDetallesDistribuciones() {
        return dtoDetallesDistribuciones;
    }

    public void setDtoDetallesDistribuciones(List<DtoDetalleDistribucion> dtoDetallesDistribuciones) {
        this.dtoDetallesDistribuciones = dtoDetallesDistribuciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
