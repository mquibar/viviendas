/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.List;
import viviendas.entidades.vivienda.Ciudad;


/**
 *
 * @author Maximiliano.
 */
public class ModeloTableCiudad extends AbstractTableModel<Ciudad>{

    public ModeloTableCiudad(List<Ciudad> lista) {
        super(lista, "Nombre");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return _lista.get(rowIndex).getNombre();
    }    
}
