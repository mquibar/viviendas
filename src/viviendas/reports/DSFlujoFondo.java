/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.reports;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import viviendas.gui.dto.DtoFlujoFondo;

/**
 *
 * @author Maximiliano.
 */
public class DSFlujoFondo implements JRDataSource{
    int size =0;
    int index = -1;
    List<DtoFlujoFondo> lista;
    private enum fields{valor, porcentaje, nombre}

    public DSFlujoFondo(List<DtoFlujoFondo> lista) {
        this.lista = lista;
        size = lista.size();
    }

    public boolean next() throws JRException {
        return ++index < size;
    }

    public Object getFieldValue(JRField jrf) throws JRException {
        switch(fields.valueOf(jrf.getName())){
            case valor:
                return lista.get(index).getValor();
            case porcentaje:
                return lista.get(index).getPorcentaje();
            case nombre:
                return lista.get(index).getNombre();
            default:
                return "";
        }
    }
}
