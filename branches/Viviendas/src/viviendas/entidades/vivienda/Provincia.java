package viviendas.entidades.vivienda;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author Manuel
 * @version 1.0
 * @created 02-dic-2010 12:40:01
 */
@Entity
public class Provincia implements Serializable {
/**
INSERT INTO Provincia (nombre) VALUES ('San Juan');
INSERT INTO Provincia (nombre) VALUES ('Buenos Aires');
INSERT INTO Provincia (nombre) VALUES ('Tierra del Fuego');
INSERT INTO Provincia (nombre) VALUES ('Entre Ríos');
INSERT INTO Provincia (nombre) VALUES ('Formosa');
INSERT INTO Provincia (nombre) VALUES ('Santiago del Estero');
INSERT INTO Provincia (nombre) VALUES ('Chaco');
INSERT INTO Provincia (nombre) VALUES ('Misiones');
INSERT INTO Provincia (nombre) VALUES ('Jujuy');
INSERT INTO Provincia (nombre) VALUES ('Catamarca');
INSERT INTO Provincia (nombre) VALUES ('La Rioja');
INSERT INTO Provincia (nombre) VALUES ('Mendoza');
INSERT INTO Provincia (nombre) VALUES ('Neuquén');
INSERT INTO Provincia (nombre) VALUES ('Córdoba');
INSERT INTO Provincia (nombre) VALUES ('La Pampa');
INSERT INTO Provincia (nombre) VALUES ('Santa Cruz');
INSERT INTO Provincia (nombre) VALUES ('Río Negro');
INSERT INTO Provincia (nombre) VALUES ('Salta');
INSERT INTO Provincia (nombre) VALUES ('Tucumán');
INSERT INTO Provincia (nombre) VALUES ('Chubut');
INSERT INTO Provincia (nombre) VALUES ('San Luis');
INSERT INTO Provincia (nombre) VALUES ('Corrientes');
INSERT INTO Provincia (nombre) VALUES ('Santa Fe');
INSERT INTO Provincia (nombre) VALUES ('Capital Federal');
update Provincia set nombre = upper(nombre)

 */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToMany
    private List<Ciudad> listaCuidad;

    public Provincia() {
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

    public List<Ciudad> getListaCuidad() {
        return listaCuidad;
    }

    public void setListaCuidad(List<Ciudad> listaCuidad) {
        this.listaCuidad = listaCuidad;
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
        final Provincia other = (Provincia) obj;
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
