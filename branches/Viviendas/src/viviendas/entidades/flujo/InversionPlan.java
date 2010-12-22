/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.entidades.flujo;

import java.util.ArrayList;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author desarrollo
 */
@Entity
@DiscriminatorValue(value="IPlan")
public class InversionPlan extends Inversion{

    public InversionPlan() {
    }


    public InversionPlan(Inversion inversion) {
        this.ciudad=inversion.ciudad;
        this.totalInversion= inversion.totalInversion;
        this.valoresInversion = new ArrayList<ValorInversion>();
        for (ValorInversion valorInversion : inversion.valoresInversion) {
            valoresInversion.add(new ValorInversion(valorInversion));
        }
    }

    
}
