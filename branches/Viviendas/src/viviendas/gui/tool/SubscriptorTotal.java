/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.tool;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danamar
 */
public class SubscriptorTotal {
    private static SubscriptorTotal instance;
    private List<ICalculable> icalculables;

    private SubscriptorTotal() {
        icalculables = new ArrayList<ICalculable>();
    }

    public static SubscriptorTotal getInstance(){
        if(instance == null){
            instance = new SubscriptorTotal();
        }
        return instance;
    }

    public void añadir(ICalculable calculable){
        icalculables.add(calculable);
    }

    public void notificar(){
        for (ICalculable iCalculable : icalculables) {
            iCalculable.actualizarPorcentaje();
        }
    }
    public void remove(ICalculable calculable){
        icalculables.remove(calculable);
    }

}
