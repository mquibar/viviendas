/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

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
    private static Integer contador = 0;
    private Integer gui;

    public Criterio(String atributo, String operador, Object valor) {
        this.atributo = atributo;
        this.operador = operador;
        if (valor.getClass().getSimpleName().equals("String") || valor.getClass().getSimpleName().equals("Date")) {
            this.valor = "'" + valor + "'";
        }
        gui = ++contador;
    }

    protected Criterio() {
    }

    @Override
    public String toString() {
        return "o." + atributo + operador + ":" + atributo + gui.toString();
    }

    public Map<String, Object> toMap() {
        Map mapa = new HashMap<String, Object>();
        mapa.put(atributo + gui, valor);
        return mapa;
    }

    public List<Object[]> toParameter() {
        List<Object[]> sql = new ArrayList<Object[]>();
        Object[] o = new Object[2];
        o[0] = atributo + gui;
        o[1] = valor;
        sql.add(o);
        return sql;
    }
}
