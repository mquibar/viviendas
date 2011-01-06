 package viviendas.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriterioCompuesto extends Criterio {

    private List<Criterio> listaCriterio;
    private List<String> listaOperador;

    public CriterioCompuesto(Criterio criterioA, String operador, Criterio criterioB) {
        listaCriterio = new ArrayList<Criterio>();
        listaOperador = new ArrayList<String>();
        listaCriterio.add(criterioA);
        listaCriterio.add(criterioB);
        listaOperador.add(operador);
    }

    @Override
    public String toString() {
        String respuesta = "";
        for (int i = 0; i < listaOperador.size(); i++) {
            respuesta += listaCriterio.get(i) + " " + listaOperador.get(i) + " ";
        }
        return respuesta += listaCriterio.get(listaCriterio.size() - 1);
    }

    @Override
    public Map<String, Object> toMap() {
        Map mapaRespuesta = new HashMap();
        for (Criterio criterio : listaCriterio) {
            mapaRespuesta.putAll(criterio.toMap());
        }
        return mapaRespuesta;
    }

    public void add(Criterio criterio, String operador) {
        listaCriterio.add(criterio);
        listaOperador.add(operador);
    }
}
