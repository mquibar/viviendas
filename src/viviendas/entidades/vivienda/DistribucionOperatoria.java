package viviendas.entidades.vivienda;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import viviendas.entidades.flujo.Financiacion;
import viviendas.entidades.flujo.ParametrosFlujoFondo;

@Entity
public class DistribucionOperatoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
    @JoinColumn(name="distribucionSector_id")
    private DistribucionSector distribucionSector;
    @ManyToOne
    @JoinColumn(name="operatoria_id")
    private Operatoria operatoria;
    @ManyToOne
    @JoinColumn(name="anioPlan_id")
    private AnioPlan anioPlan;
    @OneToOne(mappedBy="distribucionOperatoria",cascade=CascadeType.ALL)
    private Financiacion financiacion;
    @OneToOne
    @JoinColumn(name="parametrosFlujoFondo_id")
    private ParametrosFlujoFondo parametrosFlujoFondo;

    public AnioPlan getAnioPlan() {
        return anioPlan;
    }

    public void setAnioPlan(AnioPlan anioPlan) {
        this.anioPlan = anioPlan;
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

    public Financiacion getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(Financiacion financiacion) {
        this.financiacion = financiacion;
    }

    public ParametrosFlujoFondo getParametrosFlujoFondo() {
        return parametrosFlujoFondo;
    }

    public void setParametrosFlujoFondo(ParametrosFlujoFondo parametrosFlujoFondo) {
        this.parametrosFlujoFondo = parametrosFlujoFondo;
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
