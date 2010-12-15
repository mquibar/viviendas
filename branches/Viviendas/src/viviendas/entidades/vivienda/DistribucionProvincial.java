package viviendas.entidades.vivienda;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:40:01
 */
@Entity
public class DistribucionProvincial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @ManyToOne
    private Provincia provincia;
    @ManyToOne
    private AñoPlan añoPlan;

    public AñoPlan getAñoPlan() {
        return añoPlan;
    }

    public void setAñoPlan(AñoPlan añoPlan) {
        this.añoPlan = añoPlan;
    }

    public DistribucionProvincial() {
    }

    public DistribucionProvincial(DistribucionProvincial distribucionProvincial) {
        this.añoPlan = distribucionProvincial.getAñoPlan();
        this.porcentajeDistribucion = distribucionProvincial.getPorcentajeDistribucion();
        this.provincia = distribucionProvincial.getProvincia();
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
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
        final DistribucionProvincial other = (DistribucionProvincial) obj;
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
