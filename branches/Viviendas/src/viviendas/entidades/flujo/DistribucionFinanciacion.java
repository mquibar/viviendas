package viviendas.entidades.flujo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:42:06
 */
@Entity
public class DistribucionFinanciacion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private int porcentajeFinanciacion;
        @ManyToOne
	public LoQueFinancio loQueFinancio;

	public DistribucionFinanciacion(){

	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoQueFinancio getLoQueFinancio() {
        return loQueFinancio;
    }

    public void setLoQueFinancio(LoQueFinancio loQueFinancio) {
        this.loQueFinancio = loQueFinancio;
    }

    public int getPorcentajeFinanciacion() {
        return porcentajeFinanciacion;
    }

    public void setPorcentajeFinanciacion(int porcentajeFinanciacion) {
        this.porcentajeFinanciacion = porcentajeFinanciacion;
    }

}