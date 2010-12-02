/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.persistencia.exceptions;


import systemException.SystemException;

/**
 *
 * @author Manuel
 */
public class PersistException extends SystemException {

    private Object entidad;
    private String operacion;

    public PersistException(String operacion, Object entidad) {
        super(1, PersistException.class, "Error producido al realizar una operación de [ "+ operacion+" ] en el objeto: " + entidad.getClass().getSimpleName());
        this.entidad = entidad;
        this.operacion=operacion;
    }

}
