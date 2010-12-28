package viviendas.entidades.flujo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DistribucionFinanciacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeFinanciacion;
    @ManyToOne
    private Financiacion financiacion;
    @OneToMany(mappedBy = "distribucionFinanciacion", cascade = CascadeType.ALL)
    private List<DetalleDistribucionFinanciacion> detallesDistribucionesFinanciacion;

    public DistribucionFinanciacion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DetalleDistribucionFinanciacion> getDetallesDistribucionesFinanciacion() {
        return detallesDistribucionesFinanciacion;
    }

    public void setDetallesDistribucionesFinanciacion(List<DetalleDistribucionFinanciacion> detallesDistribucionesFinanciacion) {
        this.detallesDistribucionesFinanciacion = detallesDistribucionesFinanciacion;
    }

    public Financiacion getFinanciacion() {
        return financiacion;
    }

    public void setFinanciacion(Financiacion financiacion) {
        this.financiacion = financiacion;
    }

    public Double getPorcentajeFinanciacion() {
        return porcentajeFinanciacion;
    }

    public void setPorcentajeFinanciacion(Double porcentajeFinanciacion) {
        this.porcentajeFinanciacion = porcentajeFinanciacion;
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
        final DistribucionFinanciacion other = (DistribucionFinanciacion) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(porcentajeFinanciacion);
    }
}
