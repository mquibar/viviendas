package viviendas.entidades.flujo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.SectorEconomico;

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:42:07
 */
@Entity
public class DistribucionFlujoFondo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @OneToOne
    public SectorEconomico sectorEconomico;
    @OneToOne
    public Operatoria operatoria;
    @OneToOne
    public FlujoFondo flujoFondo;

    public DistribucionFlujoFondo() {
    }
}
