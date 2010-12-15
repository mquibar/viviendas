/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.systemException;


/**
 *
 * @author Admin
 */
public class VerifyDataException extends SystemException{
    private String dato;

    public VerifyDataException(String dato) {
        super(3, VerifyDataException.class, "Verifique [" + dato +"]");
        this.dato=dato;
    }

    public String getDato() {
        return dato;
    }

}
