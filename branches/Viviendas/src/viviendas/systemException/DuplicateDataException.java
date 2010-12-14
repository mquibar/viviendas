/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.systemException;

/**
 *
 * @author Juan
 */
public class DuplicateDataException extends SystemException {

    private String dato;
    private String valor;
    public DuplicateDataException(String dato, String valor) {
        super(2, DuplicateDataException.class, "Existen datos para [" + dato +"] con valor [" + valor + "]");
        this.dato = dato;
        this.valor = valor;
    }

    public String getDato() {
        return dato;
    }

    public String getValor() {
        return valor;
    }


}
