/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.gui.Plan;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import viviendas.entidades.vivienda.Operatoria;
import viviendas.entidades.vivienda.Provincia;

/**
 *
 * @author Maximiliano.
 */
public class ModeloProvincia extends AbstractTableModel {

    private List<Provincia> list;
    private List<String> columnas;

    public ModeloProvincia() {
        columnas = new ArrayList<String>();
        this.columnas.add("Provincias");
        list = new ArrayList<Provincia>();
    }

    public int getRowCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnas.get(column);
    }

    public int getColumnCount() {
        if (columnas == null) {
            return 0;
        }

        return columnas.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return list.get(rowIndex).getNombre();
    }

    public List<Provincia> getList() {
        return list;
    }

    public void setList(List<Provincia> list) {
        this.list = list;
    }

    
}
