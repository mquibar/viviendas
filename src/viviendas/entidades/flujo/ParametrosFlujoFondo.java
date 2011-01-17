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
    private Double devCredTna;
    private Double devCredGastosOtorgamiento;
    private Double devCredGastosAdministrativos;
    private Integer titulosAniosDevol;
    private Double titulosTna;
    private Double perdidaIncobrables;
    @OneToOne(mappedBy="parametrosFlujoFondo")
    private DistribucionOperatoria distribucionOperatoria;

    public ParametrosFlujoFondo() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPerdidaIncobrables() {
        return perdidaIncobrables;
    }

    public void setPerdidaIncobrables(Double perdidaIncobrables) {
        this.perdidaIncobrables = perdidaIncobrables;
    }

    public Integer getTitulosAniosDevol() {
        return titulosAniosDevol;
    }

    public void setTitulosAniosDevol(Integer titulosAniosDevol) {
        this.titulosAniosDevol = titulosAniosDevol;
    }

    public Double getTitulosTna() {
        return titulosTna;
    }

    public void setTitulosTna(Double titulosTna) {
        this.titulosTna = titulosTna;
    }

    public DistribucionOperatoria getDistribucionOperatoria() {
        return distribucionOperatoria;
    }

    public void setDistribucionOperatoria(DistribucionOperatoria distribucionOperatoria) {
        this.distribucionOperatoria = distribucionOperatoria;
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