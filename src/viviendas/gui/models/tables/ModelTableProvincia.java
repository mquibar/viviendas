/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.models.tables;

import java.util.ArrayList;
import java.util.List;
import viviendas.entidades.vivienda.Provincia;
import viviendas.gui.models.tables.AbstractTableModel;

/**
 *
 * @author Maximiliano.
 */
public class ModelTableProvincia extends AbstractTableModel<Provincia> {

    public ModelTableProvincia(List<Provincia> provincias) {
        super(provincias, "Provincias");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Provincia prov = _lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return prov.getNombre();
            default:
                return "";
        }
    }
}
