package viviendas.entidades.flujo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import viviendas.entidades.vivienda.Cuidad;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.SectorEconomico;

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:41:58
 */
@Entity
public class Financiacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    public Cuidad cuidad;
    @ManyToOne
    public SectorEconomico sectorEconomico;
    @ManyToOne
    public Operatoria operatoria;
    @ManyToOne
    public DistribucionFinanciacion distribucionFinanciacion;

    public Financiacion() {
    }

    public Cuidad getCuidad() {
        return cuidad;
    }

    public void setCuidad(Cuidad cuidad) {
        this.cuidad = cuidad;
    }

    public DistribucionFinanciacion getDistribucionFinanciacion() {
        return distribucionFinanciacion;
    }

    public void setDistribucionFinanciacion(DistribucionFinanciacion distribucionFinanciacion) {
        this.distribucionFinanciacion = distribucionFinanciacion;
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

    public Operatoria getOperatoria() {
        return operatoria;
    }

    public void setOperatoria(Operatoria operatoria) {
        this.operatoria = operatoria;
    }

    public SectorEconomico getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(SectorEconomico sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }
}
