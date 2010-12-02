/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viviendas.persistencia;

import java.util.Map;
import persistencia.Criterio;

/**
 *
 * @author Manuel
 */
public class CriterioCompuesto extends Criterio {

    private Criterio criterioA;
    private Criterio criterioB;

    public CriterioCompuesto() {
    }

    public Criterio getCriterioA() {
        return criterioA;
    }

    public void setCriterioA(Criterio criterioA) {
        this.criterioA = criterioA;
    }

    public Criterio getCriterioB() {
        return criterioB;
    }

    public void setCriterioB(Criterio criterioB) {
        this.criterioB = criterioB;
    }

    @Override
    public String toString() {
        return criterioA + " " + operador + " " + criterioB;
    }

    @Override
    public Map<String, Object> toMap() {
        Map mapa = criterioA.toMap();
        mapa.putAll(criterioB.toMap());
        return mapa;
    }



}
