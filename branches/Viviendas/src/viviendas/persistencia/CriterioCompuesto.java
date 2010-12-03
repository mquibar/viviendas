/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import java.util.Map;

/**
 *
 * @author Manuel
 */
public class CriterioCompuesto extends Criterio {

    private Criterio criterioA;
    private Criterio criterioB;

    public CriterioCompuesto(Criterio criterioA, String operador, Criterio criterioB) {
        this.criterioA = criterioA;
        this.criterioB = criterioB;
        this.operador = operador;
    }

    @Override
    public String toString() {
        return criterioA.toString() + " " + operador + " " + criterioB.toString();
    }

    @Override
    public Map<String, Object> toMap() {
        Map mapa = criterioA.toMap();
        mapa.putAll(criterioB.toMap());
        return mapa;
    }
}
