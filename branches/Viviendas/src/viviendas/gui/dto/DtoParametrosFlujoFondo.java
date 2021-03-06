/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.dto;

import viviendas.entidades.vivienda.AnioPlan;
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
    private Double tna;
    private Double gastosAdministrativos;
    private Double comisionOtorgamiento;
    private Integer momentoOtorgamiento;
    private Integer plazoGracia;
    private Double perdidaIncobrables;
    private Double tnaTitulos;
    private Integer cantAniosTitulos;
    private Plan plan;
    private AnioPlan anioPlan;
    private Provincia provincia;
    private Ciudad ciudad;
    private SectorEconomico sectorEconomico;
    private Operatoria operatoria;


    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Double getComisionOtorgamiento() {
        return comisionOtorgamiento;
    }

    public void setComisionOtorgamiento(Double comisionOtorgamiento) {
        this.comisionOtorgamiento = comisionOtorgamiento;
    }

    public Double getGastosAdministrativos() {
        return gastosAdministrativos;
    }

    public void setGastosAdministrativos(Double gastosAdministrativos) {
        this.gastosAdministrativos = gastosAdministrativos;
    }

    public Integer getMomentoOtorgamiento() {
        return momentoOtorgamiento;
    }

    public void setMomentoOtorgamiento(Integer momentoOtorgamiento) {
        this.momentoOtorgamiento = momentoOtorgamiento;
    }

    public Integer getCantAniosTitulos() {
        return cantAniosTitulos;
    }

    public void setCantAniosTitulos(Integer cantAniosTitulos) {
        this.cantAniosTitulos = cantAniosTitulos;
    }

    public Double getPerdidaIncobrables() {
        return perdidaIncobrables;
    }

    public void setPerdidaIncobrables(Double perdidaIncobrables) {
        this.perdidaIncobrables = perdidaIncobrables;
    }

    public Double getTnaTitulos() {
        return tnaTitulos;
    }

    public void setTnaTitulos(Double tnaTitulos) {
        this.tnaTitulos = tnaTitulos;
    }

    public Operatoria getOperatoria() {
        return operatoria;
    }

    public void setOperatoria(Operatoria operatoria) {
        this.operatoria = operatoria;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public AnioPlan getAnioPlan() {
        return anioPlan;
    }

    public void setAnioPlan(AnioPlan anioPlan) {
        this.anioPlan = anioPlan;
    }

    public Integer getPlazoGracia() {
        return plazoGracia;
    }

    public void setPlazoGracia(Integer plazoGracia) {
        this.plazoGracia = plazoGracia;
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

    public Double getTna() {
        return tna;
    }

    public void setTna(Double tna) {
        this.tna = tna;
    }
}