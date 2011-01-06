package viviendas.gui.models.combos;

import java.util.List;
import viviendas.entidades.vivienda.Plan;

public class ModelComboPlan extends AbstractComboBoxModel<Plan> {

    public ModelComboPlan(List<Plan> tiposPlan, String primerElemento) {
        super(tiposPlan, primerElemento);
        for (Plan plan : tiposPlan) {
            addElement(plan.getNombre());
        }
    }
}
