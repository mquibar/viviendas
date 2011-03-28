/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.entidades.flujo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import viviendas.entidades.vivienda.DistribucionOperatoria;

/**
 *
 * @author Maximiliano.
 */
@Entity
public class ParametrosFlujoFondo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double tna;
    private Double comisionOtorgamiento;
    private Double gastosAdministrativos;
    private Integer momentoOtorgamiento;
    private Integer anioGracia;
    private Double perdidaIncobrables;
    private Double tnaTitulos;
    private Integer cantAniosTitulos;
    @OneToOne(mappedBy="parametrosFlujoFondo")
    private DistribucionOperatoria distribucionOperatoria;

    public ParametrosFlujoFondo() {
    }

    public Integer getAnioGracia() {
        return anioGracia;
    }

    public void setAnioGracia(Integer anioGracia) {
        this.anioGracia = anioGracia;
    }

    public Double getComisionOtorgamiento() {
        return comisionOtorgamiento;
    }

    public void setComisionOtorgamiento(Double comisionOtorgamiento) {
        this.comisionOtorgamiento = comisionOtorgamiento;
    }

    public DistribucionOperatoria getDistribucionOperatoria() {
        return distribucionOperatoria;
    }

    public void setDistribucionOperatoria(DistribucionOperatoria distribucionOperatoria) {
        this.distribucionOperatoria = distribucionOperatoria;
    }

    public Double getGastosAdministrativos() {
        return gastosAdministrativos;
    }

    public void setGastosAdministrativos(Double gastosAdministrativos) {
        this.gastosAdministrativos = gastosAdministrativos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMomentoOtorgamiento() {
        return momentoOtorgamiento;
    }

    public void setMomentoOtorgamiento(Integer momentoOtorgamiento) {
        this.momentoOtorgamiento = momentoOtorgamiento;
    }

    public Double getTna() {
        return tna;
    }

    public void setTna(Double tna) {
        this.tna = tna;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParametrosFlujoFondo other = (ParametrosFlujoFondo) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}