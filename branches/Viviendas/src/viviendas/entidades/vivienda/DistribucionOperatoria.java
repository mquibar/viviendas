package viviendas.entidades.vivienda;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DistribucionOperatoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @ManyToOne(cascade=CascadeType.ALL)
    private DistribucionSector distribucionSector;
    @ManyToOne
    private Operatoria operatoria;
    @ManyToOne
    private AñoPlan añoPlan;

    public AñoPlan getAñoPlan() {
        return añoPlan;
    }

    public void setAñoPlan(AñoPlan añoPlan) {
        this.añoPlan = añoPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Operatoria getOperatoria() {
        return operatoria;
    }

    public void setOperatoria(Operatoria operatoria) {
        this.operatoria = operatoria;
    }

    public Double getPorcentajeDistribucion() {
        return porcentajeDistribucion;
    }

    public void setPorcentajeDistribucion(Double porcentajeDistribucion) {
        this.porcentajeDistribucion = porcentajeDistribucion;
    }

    public DistribucionSector getDistribucionSector() {
        return distribucionSector;
    }

    public void setDistribucionSector(DistribucionSector distribucionSector) {
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
        final DistribucionOperatoria other = (DistribucionOperatoria) obj;
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
