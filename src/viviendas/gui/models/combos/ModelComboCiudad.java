/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.combos;

import java.util.List;
import viviendas.entidades.vivienda.Ciudad;

/**
 *
 * @author Maximiliano.
 */
public class ModelComboCiudad extends AbstractComboBoxModel<Ciudad>{

    public ModelComboCiudad(List<Ciudad> lista){
        super(lista);
        for(Ciudad ciudad: lista){
            addElement(ciudad.getNombre());
        }
    }
}
