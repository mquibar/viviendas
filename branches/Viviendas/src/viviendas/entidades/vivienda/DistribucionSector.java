package viviendas.entidades.vivienda;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DistribucionSector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @ManyToOne
    private SectorEconomico sectorEconomico;
    @ManyToOne
    private AñoPlan añoPlan;
    @ManyToOne
    private DistribucionCiudad distribucionCiudad;

    public AñoPlan getAñoPlan() {
        return añoPlan;
    }

    public void setAñoPlan(AñoPlan añoPlan) {
        this.añoPlan = añoPlan;
    }

    public DistribucionSector() {
    }

    public DistribucionCiudad getDistribucionCiudad() {
        return distribucionCiudad;
    }

    public void setDistribucionCiudad(DistribucionCiudad distribucionCiudad) {
        this.distribucionCiudad = distribucionCiudad;
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
