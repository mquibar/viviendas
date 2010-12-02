/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systemException;



/**
 *
 * @author Manuel
 */

public abstract class SystemException extends Exception {

    private int errorCode;
    private String value;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SystemException(int errorCode,Class e, String value) {
        super("System Error Code: " + errorCode +": "+ e.getSimpleName()+" [" + value + "]");
        this.errorCode = errorCode;
        this.value = value;
    }
}
