/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.entidades.flujo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import viviendas.entidades.vivienda.Ciudad;

/**
 *
 * @author desarrollo
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Inversion implements Serializable {
    
    protected static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Double totalInversion;
    @ManyToOne
    protected Ciudad ciudad;
    @OneToMany(mappedBy = "inversion")
    protected List<ValorInversion> valoresInversion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Double getTotalInversion() {
        return totalInversion;
    }

    public void setTotalInversion(Double totalInversion) {
        this.totalInversion = totalInversion;
    }

    public List<ValorInversion> getValoresInversion() {
        return valoresInversion;
    }

    public void setValoresInversion(List<ValorInversion> valoresInversion) {
        this.valoresInversion = valoresInversion;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inversion)) {
            return false;
        }
        Inversion other = (Inversion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "viviendas.entidades.flujo.Inversion[id=" + id + "]";
    }

}
