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
    public List<ProvinciaCiudad> listaProvinciaCiudad;
    @OneToMany(mappedBy = "añoPlan")
    public List<DistribucionProvincial> listaDistribucionProvincial;
    @OneToMany(mappedBy = "añoPlan")
    public List<CiudadSector> listaCiudadSector;
    @OneToMany(mappedBy = "añoPlan")
    public List<SectorOperatoria> listaSectorOperatoria;
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

    public List<CiudadSector> getListaCiudadSector() {
        return listaCiudadSector;
    }

    public void setListaCiudadSector(List<CiudadSector> listaCiudadSector) {
        this.listaCiudadSector = listaCiudadSector;
    }

    public List<DistribucionProvincial> getListaDistribucionProvincial() {
        return listaDistribucionProvincial;
    }

    public void setListaDistribucionProvincial(List<DistribucionProvincial> listaDistribucionProvincial) {
        this.listaDistribucionProvincial = listaDistribucionProvincial;
    }

    public List<ProvinciaCiudad> getListaProvinciaCiudad() {
        return listaProvinciaCiudad;
    }

    public void setListaProvinciaCiudad(List<ProvinciaCiudad> listaProvinciaCiudad) {
        this.listaProvinciaCiudad = listaProvinciaCiudad;
    }

    public List<SectorOperatoria> getListaSectorOperatoria() {
        return listaSectorOperatoria;
    }

    public void setListaSectorOperatoria(List<SectorOperatoria> listaSectorOperatoria) {
        this.listaSectorOperatoria = listaSectorOperatoria;
    }
}
