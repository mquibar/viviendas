package viviendas.entidades.vivienda;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DistribucionCiudad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @ManyToOne(fetch=FetchType.EAGER)
    private Ciudad cuidad;
    @ManyToOne(cascade=CascadeType.MERGE)
    private DistribucionProvincial distribucionProvincial;
    @ManyToOne
    private AnioPlan anioPlan;

    public AnioPlan getAnioPlan() {
        return anioPlan;
    }

    public void setAnioPlan(AnioPlan añoPlan) {
        this.anioPlan = añoPlan;
    }

    public DistribucionCiudad() {
    }

    public Ciudad getCuidad() {
        return cuidad;
    }

    public void setCuidad(Ciudad cuidad) {
        this.cuidad = cuidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPorcentajeDistribucion() {
        return porcentajeDistribucion;
    }

    public void setPorcentajeDistribucion(Double porcentajeDistribucion) {
        this.porcentajeDistribucion = porcentajeDistribucion;
    }

    public DistribucionProvincial getDistribucionProvincial() {
        return distribucionProvincial;
    }

    public void setDistribucionProvincial(DistribucionProvincial distribucionProvincial) {
        this.distribucionProvincial = distribucionProvincial;
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
        final DistribucionCiudad other = (DistribucionCiudad) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(porcentajeDistribucion);
    }


}
