/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package systemException;

/**
 *
 * @author Juan
 */
public class DuplicateDataException extends SystemException {

    private String dato;
    private String valor;
    public DuplicateDataException(String dato, String valor) {
        super(2, DuplicateDataException.class, "Existen datos para [" + dato +"] con valor [" + valor + "]");
    }



}
