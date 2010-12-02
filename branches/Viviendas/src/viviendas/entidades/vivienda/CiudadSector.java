package viviendas.entidades.vivienda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:40:02
 */
@Entity
public class CiudadSector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @ManyToOne
    public SectorEconomico sectorEconomico;
    @ManyToOne
    public Cuidad cuidad;

    public CiudadSector() {
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

    public Double getPorcentajeDistribucion() {
        return porcentajeDistribucion;
    }

    public void setPorcentajeDistribucion(Double porcentajeDistribucion) {
        this.porcentajeDistribucion = porcentajeDistribucion;
    }

    public SectorEconomico getSectorEconomico() {
        return sectorEconomico;
    }

    public void setSectorEconomico(SectorEconomico sectorEconomico) {
        this.sectorEconomico = sectorEconomico;
    }
}
