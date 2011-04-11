package viviendas.entidades.flujo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import viviendas.entidades.vivienda.DistribucionOperatoria;

@Entity
public class Financiacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "financiacion", cascade = CascadeType.ALL)
    private List<DistribucionFinanciacion> distribucionesFinanciacion;
    @OneToOne
    @JoinColumn(name="distribucionOperatoria_id")
    private DistribucionOperatoria distribucionOperatoria;

    public Financiacion() {
    }

    public Financiacion(Financiacion financiacion) {
        distribucionOperatoria = financiacion.getDistribucionOperatoria();
        nombre = financiacion.getNombre();
        if (financiacion.getDistribucionesFinanciacion() != null) {
            List<DistribucionFinanciacion> listaDistribucion = new ArrayList<DistribucionFinanciacion>();
            for (DistribucionFinanciacion distribucionFinanciacion : financiacion.getDistribucionesFinanciacion()) {
                DistribucionFinanciacion df = new DistribucionFinanciacion(distribucionFinanciacion);
                df.setFinanciacion(this);
                listaDistribucion.add(df);
            }
            distribucionesFinanciacion = listaDistribucion;
        }
    }

    public DistribucionOperatoria getDistribucionOperatoria() {
        return distribucionOperatoria;
    }

    public void setDistribucionOperatoria(DistribucionOperatoria distribucionOperatoria) {
        this.distribucionOperatoria = distribucionOperatoria;
    }

    public List<DistribucionFinanciacion> getDistribucionesFinanciacion() {
        return distribucionesFinanciacion;
    }

    public void setDistribucionesFinanciacion(List<DistribucionFinanciacion> distribucionesFinanciacion) {
        this.distribucionesFinanciacion = distribucionesFinanciacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        final Financiacion other = (Financiacion) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
