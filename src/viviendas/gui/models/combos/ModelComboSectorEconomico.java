package viviendas.gui.models.combos;

import java.util.List;
import viviendas.entidades.vivienda.SectorEconomico;

public class ModelComboSectorEconomico extends AbstractComboBoxModel<SectorEconomico> {

    public ModelComboSectorEconomico(List<SectorEconomico> tiposPlan, String primerElemento) {
        super(tiposPlan, primerElemento);
        for (SectorEconomico sectorEconomico : tiposPlan) {
            addElement(sectorEconomico.getNombre());
        }
    }
}
