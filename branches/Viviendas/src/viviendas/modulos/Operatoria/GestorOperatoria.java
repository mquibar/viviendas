/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.Operatoria;

import java.util.List;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.persistencia.Facade;

/**
 *
 * @author Maximiliano.
 */
public class GestorOperatoria {

    public void guardar(){

    }

    public List obtenerOperatorias() {
        return  Facade.getInstance().findAll(Operatoria.class);
    }
}
