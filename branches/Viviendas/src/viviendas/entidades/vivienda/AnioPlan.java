package viviendas.entidades.vivienda;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:40:00
 */
@Entity
public class AnioPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer anio;
    private Integer cantViviendasAnio;
    @OneToMany(mappedBy = "anioPlan", cascade=CascadeType.ALL)
    private List<DistribucionCiudad> _distribucionCiudad;
    @OneToMany(mappedBy = "anioPlan",cascade=CascadeType.ALL)
    private List<DistribucionProvincial> _distribucionProvincia;
    @OneToMany(mappedBy = "anioPlan",cascade=CascadeType.ALL)
    private List<DistribucionSector> distribucionSector;
    @OneToMany(mappedBy = "anioPlan",cascade=CascadeType.ALL)
    private List<DistribucionOperatoria> distribucionOperatoria;
    @ManyToOne
    private Plan plan;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer año) {
        this.anio = año;
    }

    public Integer getCantViviendasAnio() {
        return cantViviendasAnio;
    }

    public void setCantViviendasAnio(Integer cantViviendasAño) {
        this.cantViviendasAnio = cantViviendasAño;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DistribucionProvincial> getDistribucionProvincia() {
        return _distribucionProvincia;
    }

    public void setDistribucionProvincia(List<DistribucionProvincial> dProvinciaList) {
        this._distribucionProvincia = dProvinciaList;
    }

    public List<DistribucionCiudad> getDistribucionCiudad() {
        return _distribucionCiudad;
    }

    public void setDistribucionCiudad(List<DistribucionCiudad> dCiudadList) {
        this._distribucionCiudad = dCiudadList;
    }

    public List<DistribucionOperatoria> getDistribucionOperatoria() {
        return distribucionOperatoria;
    }

    public void setDistribucionOperatoria(List<DistribucionOperatoria> distribucionOperatoria) {
        this.distribucionOperatoria = distribucionOperatoria;
    }

    public List<DistribucionSector> getDistribucionSector() {
        return distribucionSector;
    }

    public void setDistribucionSector(List<DistribucionSector> distribucionSector) {
        this.distribucionSector = distribucionSector;
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
        final AnioPlan other = (AnioPlan) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(anio);
    }


}
