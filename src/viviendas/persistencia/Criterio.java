/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author Manuel
 */
public class Criterio {

    private String atributo;
    protected String operador;
    private Object valor;
    private String gui;

    public Criterio() {
        gui=UUID.randomUUID().toString();
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "o."+atributo + operador + ":"+gui;
    }

    public Map<String,Object> toMap(){
        Map mapa = new HashMap<String, Object>();
        mapa.put(gui, valor);
        return mapa;
    }

    public List<Object[]> toParameter(){
        List<Object[]> sql = new ArrayList<Object[]>();
        Object[] o = new Object[2];
        o[0]=gui;
        o[1]=valor;
        sql.add(o);
        
        return sql;
    }

}
