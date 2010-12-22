/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.entidades.flujo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author desarrollo
 */
 @Entity
 @DiscriminatorValue(value="IParam")
public class InversionParametro extends Inversion{

}
