/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.combos;

import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Manuel
 */
public abstract class AbstractComboBoxModel<E> extends DefaultComboBoxModel{

    List<E> _lista;

    public AbstractComboBoxModel(List<E> lista,String primerElemento) {
        this._lista = lista;
        super.addElement(primerElemento);
    }
    

    public E getSelected(){
        int index = super.getIndexOf(super.getSelectedItem());
        try{
            return _lista.get(index-1);
        }catch(Exception e){
            return null;
        }
    }


}
