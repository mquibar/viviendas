package viviendas.entidades.flujo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.SectorEconomico;

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:42:07
 */
@Entity
public class DistribucionFlujoFondo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @OneToOne
    private SectorEconomico sectorEconomico;
    @OneToOne
    private Operatoria operatoria;
    @OneToOne
    private FuenteFondo flujoFondo;

    public DistribucionFlujoFondo() {
    }

    public FuenteFondo getFlujoFondo() {
        return flujoFondo;
    }

    public void setFlujoFondo(FuenteFondo flujoFondo) {
        this.flujoFondo = flujoFondo;
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

    public SectorEconomico getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(SectorEconomico sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
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
        final DistribucionFlujoFondo other = (DistribucionFlujoFondo) obj;
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
