package viviendas.entidades.flujo;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:42:05
 */
@Entity
public class LoQueFinancio {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String nombre;
    @OneToMany
	public List<Valor> listaValor;

	public LoQueFinancio(){

	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Valor> getListaValor() {
        return listaValor;
    }

    public void setListaValor(List<Valor> listaValor) {
        this.listaValor = listaValor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

        
}