/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.models.combos;

import java.util.List;
import viviendas.entidades.vivienda.TipoPlan;

/**
 *
 * @author batte
 */
public class ModelComboTipoPlan extends AbstractComboBoxModel<TipoPlan> {

    public ModelComboTipoPlan(List<TipoPlan> tiposPlan) {
        super(tiposPlan);
        for (TipoPlan tipoPlan : tiposPlan) {
            addElement(tipoPlan.getNombre());
        }
    }


}
