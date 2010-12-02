package viviendas.entidades.vivienda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:40:01
 */
@Entity
public class ProvinciaCiudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double porcentajeDistribucion;
    @OneToOne
    public Cuidad cuidad;
    @OneToOne
    public Provincia provincia;

    public ProvinciaCiudad() {
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    
}
