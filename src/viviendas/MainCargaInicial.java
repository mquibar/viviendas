/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas;

import java.util.List;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.TipoPlan;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;

/**
 *
 * @author batte
 */
public class MainCargaInicial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistException, Exception {
        //PRIMERO EJECUTAR LOS INSERTS COLOCADOS EN PROVINCIA
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
    }
}
