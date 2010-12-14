/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.systemException;

/**
 *
 * @author desarrollo
 */
public class BusinessOperationException extends SystemException {

    String operation;
    public BusinessOperationException(String operation) {
        super(4, BusinessOperationException.class, "Error al realizar la operacion de " + operation);
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

}
