package viviendas.gui.models.combos;

import java.util.List;
import viviendas.entidades.vivienda.Provincia;

public class ModelComboProvincia extends AbstractComboBoxModel<Provincia> {

    public ModelComboProvincia(List<Provincia> tiposProvincia,String primerElemento) {
        super(tiposProvincia,primerElemento);
        for (Provincia provincia : tiposProvincia) {
            addElement(provincia.getNombre());
        }
    }
}
