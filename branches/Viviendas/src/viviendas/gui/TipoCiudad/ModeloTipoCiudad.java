/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.TipoCiudad;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import viviendas.entidades.vivienda.Ciudad;

/**
 *
 * @author Administrador
 */
public class ModeloTipoCiudad extends AbstractTableModel{
    private List<Ciudad> list = new ArrayList();
    private List<String> columnas = new ArrayList();

    public ModeloTipoCiudad() {
        this.columnas.add("Nombre");
        obtenerDatos();
    }

    public int getRowCount() {
        if(list == null)
            return 0;

        return list.size();
    }

    public int getColumnCount() {
        if(columnas == null)
            return 0;

        return columnas.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return list.get(rowIndex).getNombre();
    }

    @Override
    public String getColumnName(int column) {
        return columnas.get(column);
    }

    private void obtenerDatos(){

    }

}
