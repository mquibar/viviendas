/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.SectorEconomico;



/**
 *
 * @author Maximiliano.
 */
public class ModeloTablaSectorEconomico extends AbstractTableModel<SectorEconomico>{
    
    public ModeloTablaSectorEconomico(List<SectorEconomico> lista) {
        super(lista, "Nombre");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return _lista.get(rowIndex).getNombre();
    }


}
