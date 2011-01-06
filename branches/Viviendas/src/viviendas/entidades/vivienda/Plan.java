package viviendas.entidades.vivienda;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import viviendas.entidades.flujo.InversionPlan;

@Entity
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer aniosPlan;
    private Integer numeroViviendas;
    @ManyToOne
    private TipoPlan tipoPlan;
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<AnioPlan> listaAnioPlan;
    @OneToMany
    private List<InversionPlan> listaInversion;

    public Plan() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAniosPlan() {
        return aniosPlan;
    }

    public void setAniosPlan(Integer aniosPlan) {
        this.aniosPlan = aniosPlan;
    }

    public Integer getNumeroViviendas() {
        return numeroViviendas;
    }

    public void setNumeroViviendas(Integer numeroViviendas) {
        this.numeroViviendas = numeroViviendas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AnioPlan> getListaAnioPlan() {
        return listaAnioPlan;
    }

    public void setListaAnioPlan(List<AnioPlan> listaAnioPlan) {
        this.listaAnioPlan = listaAnioPlan;
    }

    public TipoPlan getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(TipoPlan tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public List<InversionPlan> getListaInversion() {
        return listaInversion;
    }

    public void setListaInversion(List<InversionPlan> listaInversion) {
        this.listaInversion = listaInversion;
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
        final Plan other = (Plan) obj;
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
