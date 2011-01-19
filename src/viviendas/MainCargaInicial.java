/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas;

import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Plan;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.modulos.parametros.GestorParametro;
import viviendas.persistencia.Criterio;
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
        Criterio criterio = new Criterio("id", "=", 6l);
        Plan plan =(Plan) Facade.getInstance().findByCriterio(Plan.class, criterio).get(0);
        System.out.println(plan);
        Criterio criterio1 = new Criterio("id", "=", 2l);
        Provincia provincia =(Provincia) Facade.getInstance().findByCriterio(Provincia.class, criterio1).get(0);
        System.out.println(provincia);
        Criterio criterio2 = new Criterio("id", "=", 1l);
        Ciudad ciudad =(Ciudad) Facade.getInstance().findByCriterio(Ciudad.class, criterio2).get(0);
        System.out.println(ciudad);
        Criterio criterio3 = new Criterio("id", "=", 1l);
        SectorEconomico sectorEconomico =(SectorEconomico) Facade.getInstance().findByCriterio(SectorEconomico.class, criterio3).get(0);
        System.out.println(sectorEconomico);

        Criterio criterio4 = new Criterio("id", "=", 1l);
        Operatoria operatoria =(Operatoria) Facade.getInstance().findByCriterio(Operatoria.class, criterio4).get(0);
        System.out.println(operatoria);

        
        //System.out.println(GestorParametro.obtenerDistribucionOperatoria(plan, provincia, ciudad, sectorEconomico, operatoria));
//        System.out.println(GestorParametro.obtenerDistribucionOperatoria(plan, null, null, null, operatoria));
    }
}
