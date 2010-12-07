/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.Operatoria;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import viviendas.entidades.vivienda.Operatoria;

/**
 *
 * @author Admin
 */
public class ModeloOperatoria extends AbstractTableModel{
    private List<Operatoria> list = new ArrayList();
    private List<String> columnas = new ArrayList();

    public ModeloOperatoria() {
        this.columnas.add("Operatoria");
        ObtenerDatos();
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

    public void ObtenerDatos(){
        //solicitamos los datos al controlador....
        Operatoria op = new Operatoria();
        op.setId(1L);
        op.setNombre("Vivienda");
        Operatoria op1 = new Operatoria();
        op1.setId(2L);
        op1.setNombre("Cooperativa");

        list.add(op);
        list.add(op1);
        fireTableDataChanged();
    }
}
