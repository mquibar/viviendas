package viviendas.gui.models.combos;

import java.util.List;
import viviendas.entidades.vivienda.AnioPlan;

public class ModelComboAnioPlan extends AbstractComboBoxModel<AnioPlan> {

    public ModelComboAnioPlan(List<AnioPlan> tiposPlan, String primerElemento) {
        super(tiposPlan, primerElemento);
        for (AnioPlan anioPlan : tiposPlan) {
            addElement(anioPlan.getAnio());
        }
    }
}
