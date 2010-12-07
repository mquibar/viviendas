/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.gui.models.tables;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author desarrollo
 */
public abstract class AbstractTableModel<E> extends javax.swing.table.AbstractTableModel {

     protected String[] _columnNames;
    protected List<E> _lista;

    public AbstractTableModel(List<E> _lista, String ... columnNames) {
        this._columnNames = columnNames;
        this._lista = _lista;
    }

    public int getRowCount() {
        if(_lista == null)
            return 0;
        return _lista.size();
    }

    public int getColumnCount() {
        return _columnNames.length;
    }

    public void addRow(E e){
        if(_lista == null)
            _lista = new ArrayList<E>();
        _lista.add(e);
        fireTableDataChanged();
    }
    public void addAll(List<E> e){
        if(_lista == null)
            _lista = new ArrayList<E>();
        _lista.addAll(e);
        fireTableDataChanged();
    }

    public void delRow(E e){
        if(_lista == null)
            return;
        _lista.remove(e);
        fireTableDataChanged();
    }

    public void delRow(int rowIndex){
        if(_lista == null)
            return;
        _lista.remove(rowIndex);
        fireTableDataChanged();
    }

    public E getSelectedIndex( int rowIndex){
        if(_lista == null ||rowIndex<0)
            return null;
        return _lista.get(rowIndex);
    }

    public List<E> getAllRow(){
        return _lista;
    }

    public void clear(){
        if(_lista == null)
            return;
        _lista.clear();
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return _columnNames[column];
    }

}
