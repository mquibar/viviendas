/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas;

import java.util.List;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Provincia;
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

        List<Ciudad> ciudades = Facade.getInstance().findAll(Ciudad.class);
        Facade.getInstance().beginTx();
        for (Provincia provincia : (List<Provincia>) Facade.getInstance().findAll(Provincia.class)) {
            provincia.setListaCuidad(ciudades);
        }
        Facade.getInstance().commitTx();
//        Facade.getInstance().beginTx();
//        TipoPlan tipo = new TipoPlan();
//        tipo.setNombre("VIVIENDAS");
//        Facade.getInstance().guardar(tipo);
//        TipoPlan tipo1 = new TipoPlan();
//        tipo1.setNombre("SOLUCIONES");
//        Facade.getInstance().guardar(tipo1);
//        Facade.getInstance().commitTx();
//        Facade.getInstance().beginTx();
//
//        FuenteFondo fuente = new FuenteFondo();
//        fuente.setNombre("FUENTE 1");
//        FuenteFondo fuente1 = new FuenteFondo();
//        fuente1.setNombre("FUENTE 2");
//        FuenteFondo fuente2 = new FuenteFondo();
//        fuente2.setNombre("FUENTE 3");
//        Facade.getInstance().guardar(fuente);
//        Facade.getInstance().guardar(fuente1);
//        Facade.getInstance().guardar(fuente2);
//        UsoFondo uso = new UsoFondo();
//        uso.setNombre("USO 1");
//        UsoFondo uso1 = new UsoFondo();
//        uso1.setNombre("USO 2");
//        UsoFondo uso2 = new UsoFondo();
//        uso2.setNombre("USO 3");
//        Facade.getInstance().guardar(uso);
//        Facade.getInstance().guardar(uso1);
//        Facade.getInstance().guardar(uso2);
//        Facade.getInstance().commitTx();
    }
}
