/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.persistencia;

/**
 *
 * @author desarrollo
 */
public class CriterioJoin extends Criterio {

    public CriterioJoin(String operador, Object valor) {
        super("obj", operador, valor);
    }


    @Override
    public String toString() {
        return "o " + operador + "( :" + atributo + gui.toString()+" )";
    }


}
