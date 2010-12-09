/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.SegmentoEconomico;

import java.util.List;
import viviendas.entidades.vivienda.SectorEconomico;
import viviendas.persistencia.Facade;

/**
 *
 * @author Maximiliano.
 */
public class GestorSectorEconomico {

    public void guardar(){

    }

    public List<SectorEconomico> obtenerSectoresEconomicos() {
        return Facade.getInstance().findAll(SectorEconomico.class);
    }
}
