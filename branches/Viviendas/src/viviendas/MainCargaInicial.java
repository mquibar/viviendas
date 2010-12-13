/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas;

import java.util.List;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;

/**
 *
 * @author batte
 */
public class MainCargaInicial {

    /**
    INSERT INTO viviendas.Usuario (`contraseña`, usuario) VALUES ('luis', 'luis');
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
    public static void main(String[] args) throws PersistException, Exception {
        //PRIMERO EJECUTAR LOS INSERTS
        Facade.getInstance().beginTx();
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("RURAL");
        ciudad.setDesde(0);
        ciudad.setHasta(10000);
        Facade.getInstance().guardar(ciudad);
        Ciudad ciudad1 = new Ciudad();
        ciudad1.setNombre("CHICA");
        ciudad1.setDesde(10000);
        ciudad1.setHasta(100000);
        Facade.getInstance().guardar(ciudad1);
        Ciudad ciudad2 = new Ciudad();
        ciudad2.setNombre("MEDIANA");
        ciudad2.setDesde(100000);
        ciudad2.setHasta(1000000);
        Facade.getInstance().guardar(ciudad2);
        Facade.getInstance().commitTx();
        List<Ciudad> ciudades = Facade.getInstance().findAll(Ciudad.class);
        Facade.getInstance().beginTx();
        for (Provincia provincia : (List<Provincia>) Facade.getInstance().findAll(Provincia.class)) {
            provincia.setListaCuidad(ciudades);
        }
        Facade.getInstance().commitTx();
        Facade.getInstance().beginTx();
        TipoPlan tipo = new TipoPlan();
        tipo.setNombre("VIVIENDAS");
        Facade.getInstance().guardar(tipo);
        TipoPlan tipo1 = new TipoPlan();
        tipo1.setNombre("SOLUCIONES");
        Facade.getInstance().guardar(tipo1);
        Facade.getInstance().commitTx();
        Facade.getInstance().beginTx();
        SectorEconomico sector = new SectorEconomico();
        sector.setNombre("Bajo");
        SectorEconomico sector1 = new SectorEconomico();
        sector1.setNombre("Medio Bajo");
        SectorEconomico sector2 = new SectorEconomico();
        sector2.setNombre("Medio");
        Operatoria operatoria = new Operatoria();
        operatoria.setNombre("Cooperativa");
        Operatoria operatoria1 = new Operatoria();
        operatoria1.setNombre("Obra Publica");
        Operatoria operatoria2 = new Operatoria();
        operatoria2.setNombre("Propia");
        Facade.getInstance().guardar(sector);
        Facade.getInstance().guardar(sector1);
        Facade.getInstance().guardar(sector2);
        Facade.getInstance().guardar(operatoria);
        Facade.getInstance().guardar(operatoria1);
        Facade.getInstance().guardar(operatoria2);
        Facade.getInstance().commitTx();
    }
}
