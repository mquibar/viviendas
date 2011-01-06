package viviendas.gui.models.combos;

import java.util.List;
import viviendas.entidades.vivienda.Operatoria;

public class ModelComboOperatoria extends AbstractComboBoxModel<Operatoria> {

    public ModelComboOperatoria(List<Operatoria> tiposPlan,String primerElemento) {
        super(tiposPlan,primerElemento);
        for (Operatoria operatoria : tiposPlan) {
            addElement(operatoria.getNombre());
        }
    }
}
