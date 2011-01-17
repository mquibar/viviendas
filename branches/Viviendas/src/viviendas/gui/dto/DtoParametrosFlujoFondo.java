/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.dto;

import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;

/**
 *
 * @author Maximiliano.
 */
public class DtoParametrosFlujoFondo {
    private Double devCredTna;
    private Double devCredGastosOtorgamiento;
    private Double devCredGastosAdministrativos;
    private Integer titulosAniosDevolucion;
    private Double titilosTna;
    private Double perdidaIncobrables;
    private Plan plan;
    private Provincia provincia;
    private Ciudad ciudad;
    private SectorEconomico sectorEconomico;
    private Operatoria operatoria;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Double getDevCredGastosAdministrativos() {
        return devCredGastosAdministrativos;
    }

    public void setDevCredGastosAdministrativos(Double devCredGastosAdministrativos) {
        this.devCredGastosAdministrativos = devCredGastosAdministrativos;
    }

    public Double getDevCredGastosOtorgamiento() {
        return devCredGastosOtorgamiento;
    }

    public void setDevCredGastosOtorgamiento(Double devCredGastosOtorgamiento) {
        this.devCredGastosOtorgamiento = devCredGastosOtorgamiento;
    }

    public Double getDevCredTna() {
        return devCredTna;
    }

    public void setDevCredTna(Double devCredTna) {
        this.devCredTna = devCredTna;
    }

    public Operatoria getOperatoria() {
        return operatoria;
    }

    public void setOperatoria(Operatoria operatoria) {
        this.operatoria = operatoria;
    }

    public Double getPerdidaIncobrables() {
        return perdidaIncobrables;
    }

    public void setPerdidaIncobrables(Double perdidaIncobrables) {
        this.perdidaIncobrables = perdidaIncobrables;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public SectorEconomico getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(SectorEconomico sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }

    public Double getTitilosTna() {
        return titilosTna;
    }

    public void setTitilosTna(Double titilosTna) {
        this.titilosTna = titilosTna;
    }

    public Integer getTitulosAniosDevolucion() {
        return titulosAniosDevolucion;
    }

    public void setTitulosAniosDevolucion(Integer titulosAniosDevolucion) {
        this.titulosAniosDevolucion = titulosAniosDevolucion;
    }
}