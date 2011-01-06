package viviendas.entidades.flujo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DetalleDistribucionFinanciacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private DistribucionFinanciacion distribucionFinanciacion;
    private Double porcentaje;
    @ManyToOne
    private UsoFondo usoFondo;
    @ManyToOne
    private FuenteFondo fuenteFondo;

    public DetalleDistribucionFinanciacion() {
    }

    public DetalleDistribucionFinanciacion(DetalleDistribucionFinanciacion detalleDistribucionFinanciacion) {
        distribucionFinanciacion = detalleDistribucionFinanciacion.getDistribucionFinanciacion();
        porcentaje = detalleDistribucionFinanciacion.getPorcentaje();
        usoFondo=detalleDistribucionFinanciacion.getUsoFondo();
        fuenteFondo=detalleDistribucionFinanciacion.getFuenteFondo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FuenteFondo getFuenteFondo() {
        return fuenteFondo;
    }

    public void setFuenteFondo(FuenteFondo flujoFondo) {
        this.fuenteFondo = flujoFondo;
    }

    public UsoFondo getUsoFondo() {
        return usoFondo;
    }

    public void setUsoFondo(UsoFondo usoFondo) {
        this.usoFondo = usoFondo;
    }

    
    public DistribucionFinanciacion getDistribucionFinanciacion() {
        return distribucionFinanciacion;
    }

    public void setDistribucionFinanciacion(DistribucionFinanciacion distribucionFinanciacion) {
        this.distribucionFinanciacion = distribucionFinanciacion;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
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
        if (!(object instanceof DetalleDistribucionFinanciacion)) {
            return false;
        }
        DetalleDistribucionFinanciacion other = (DetalleDistribucionFinanciacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "viviendas.entidades.flujo.DetalleDistribucionFinanciacion[id=" + id + "]";
    }

}
