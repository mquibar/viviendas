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

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:40:00
 */
@Entity
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer añosPlan;
    @ManyToOne
    public TipoPlan tipoPlan;
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    public List<AñoPlan> listaAñoPlan;

    public Plan() {
    }

    public Integer getAñosPlan() {
        return añosPlan;
    }

    public void setAñosPlan(Integer añosPlan) {
        this.añosPlan = añosPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AñoPlan> getListaAñoPlan() {
        return listaAñoPlan;
    }

    public void setListaAñoPlan(List<AñoPlan> listaAñoPlan) {
        this.listaAñoPlan = listaAñoPlan;
    }

    public TipoPlan getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(TipoPlan tipoPlan) {
        this.tipoPlan = tipoPlan;
    }
}
