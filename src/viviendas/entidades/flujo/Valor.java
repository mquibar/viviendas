package viviendas.entidades.flujo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import viviendas.entidades.vivienda.Cuidad;

/**
 * @author Administrador
 * @version 1.0
 * @created 02-dic-2010 12:42:09
 */
@Entity
public class Valor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentaje;
    @ManyToOne
    public Cuidad cuidad;

    public Valor() {
    }

    public Cuidad getCuidad() {
        return cuidad;
    }

    public void setCuidad(Cuidad cuidad) {
        this.cuidad = cuidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

}
