package viviendas.entidades.vivienda;

import java.io.Serializable;
import java.util.List;
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
public class AñoPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer año;
    private Integer cantViviendasAño;
    @OneToMany(mappedBy = "añoPlan")
    private List<DistribucionCiudad> listaProvinciaCiudad;
    @OneToMany(mappedBy = "añoPlan")
    private List<DistribucionProvincial> listaDistribucionProvincial;
    @OneToMany(mappedBy = "añoPlan")
    private List<DistribucionSector> listaCiudadSector;
    @OneToMany(mappedBy = "añoPlan")
    private List<DistribucionOperatoria> listaSectorOperatoria;
    @ManyToOne
    private Plan plan;

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public Integer getCantViviendasAño() {
        return cantViviendasAño;
    }

    public void setCantViviendasAño(Integer cantViviendasAño) {
        this.cantViviendasAño = cantViviendasAño;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DistribucionSector> getListaCiudadSector() {
        return listaCiudadSector;
    }

    public void setListaCiudadSector(List<DistribucionSector> listaCiudadSector) {
        this.listaCiudadSector = listaCiudadSector;
    }

    public List<DistribucionProvincial> getListaDistribucionProvincial() {
        return listaDistribucionProvincial;
    }

    public void setListaDistribucionProvincial(List<DistribucionProvincial> listaDistribucionProvincial) {
        this.listaDistribucionProvincial = listaDistribucionProvincial;
    }

    public List<DistribucionCiudad> getListaProvinciaCiudad() {
        return listaProvinciaCiudad;
    }

    public void setListaProvinciaCiudad(List<DistribucionCiudad> listaProvinciaCiudad) {
        this.listaProvinciaCiudad = listaProvinciaCiudad;
    }

    public List<DistribucionOperatoria> getListaSectorOperatoria() {
        return listaSectorOperatoria;
    }

    public void setListaSectorOperatoria(List<DistribucionOperatoria> listaSectorOperatoria) {
        this.listaSectorOperatoria = listaSectorOperatoria;
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
        final AñoPlan other = (AñoPlan) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(año);
    }


}
