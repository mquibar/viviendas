/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.systemException;


/**
 *
 * @author Admin
 */
public class MissingData extends SystemException{
    private String dato;

    public MissingData(String dato) {
        super(3, MissingData.class, "Dato faltante [" + dato +"]");
        this.dato=dato;
    }

    public String getDato() {
        return dato;
    }

}
