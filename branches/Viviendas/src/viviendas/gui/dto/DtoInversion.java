/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.dto;

import java.util.List;
import viviendas.entidades.flujo.ValorInversion;
import viviendas.entidades.vivienda.Ciudad;

/**
 *
 * @author Maximiliano.
 */
public class DtoInversion {
    public Ciudad _ciudad;
    public List<ValorInversion> _listadoValorInversion;
    public Double importeInversion;

    public Ciudad getCiudad() {
        return _ciudad;
    }

    public void setCiudad(Ciudad _ciudad) {
        this._ciudad = _ciudad;
    }

    public List<ValorInversion> getListadoValorInversion() {
        return _listadoValorInversion;
    }

    public void setListadoValorInversion(List<ValorInversion> _listadoValorInversion) {
        this._listadoValorInversion = _listadoValorInversion;
    }

    public Double getImporteInversion() {
        return importeInversion;
    }

    public void setImporteInversion(Double importeInversion) {
        this.importeInversion = importeInversion;
    }
}
