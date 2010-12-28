/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.modulos.inversion;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import viviendas.entidades.flujo.InversionParametro;
import viviendas.entidades.flujo.UsoFondo;
import viviendas.entidades.flujo.ValorInversion;
import viviendas.entidades.vivienda.Ciudad;
import viviendas.gui.dto.DtoInversion;
import viviendas.persistencia.Criterio;
import viviendas.persistencia.Facade;
import viviendas.persistencia.exceptions.PersistException;
import viviendas.systemException.BusinessOperationException;

/**
 *
 * @author Maximiliano.
 */
public class GestorInversion {
    public List<UsoFondo> obtenerUsosFondos() {
        return Facade.getInstance().findAll(UsoFondo.class);
    }

    public List<UsoFondo> obtenerUsosFondos(Ciudad ciudad){
        List<UsoFondo> listado = new ArrayList<UsoFondo>();
        Criterio criterio = new Criterio("ciudad", "=", ciudad);
        List<InversionParametro> inversion = Facade.getInstance().findByCriterio(InversionParametro.class, criterio);
        if(inversion.size() > 0){
            List listaValorInv = inversion.get(0).getValoresInversion();
            if(listaValorInv.size() > 0)
                for(int i=0; i<listaValorInv.size(); i++){
                    listado.add(((ValorInversion) listaValorInv.get(i)).getFinancio());
                }
        }
        return listado;
    }

    public List<Ciudad> obtenerCiudades() {
        Criterio criterio = new Criterio("vigente", "=", true);
        return Facade.getInstance().findByCriterio(Ciudad.class, criterio);
    }

    public List<UsoFondo> obtenerUsosDisponibles(List<UsoFondo> listadoSel) {
        List<UsoFondo> listado = new ArrayList<UsoFondo>();
        for(UsoFondo uso : obtenerUsosFondos()){
            if(!listadoSel.contains(uso)){
                listado.add(uso);
            }
        }
        return listado;
    }

    public void guardar(DtoInversion dto) throws BusinessOperationException{
        InversionParametro inversion;
        Criterio criterio = new Criterio("ciudad", "=", dto.getCiudad());
        List<InversionParametro> listaInvParam = Facade.getInstance().findByCriterio(InversionParametro.class, criterio);
        if(listaInvParam != null){
            if(listaInvParam.size() > 0){
                inversion = listaInvParam.get(0);
                inversion.setTotalInversion(dto.getImporteInversion());
                try {
                    Facade.getInstance().actualizar(inversion);
                    Facade.getInstance().commitTx();
                } catch (PersistException ex) {
                    Logger.getLogger(GestorInversion.class.getName()).log(Level.SEVERE, null, ex);
                    throw new BusinessOperationException("ERROR al guardar los datos.");
                }
            }
        }
    }

    public List<ValorInversion> obtenerDatos(Ciudad ciudad){
        InversionParametro inversion = null;
        try {
            Facade.getInstance().beginTx();
            Criterio criterio = new Criterio("ciudad", "=", ciudad);
            List<InversionParametro> lista = Facade.getInstance().findByCriterio(InversionParametro.class, criterio);
            //Si la ciudad tiene un parametro de inversión.
            if(lista != null && lista.size() > 0){
                //lo leemos.
                inversion = lista.get(0);
                //return inversion.getValoresInversion();
            }
            else{
                //La ciudad no tiene un parametro de inversion.
                //los creamos.
                inversion = new InversionParametro();
                inversion.setCiudad(ciudad);
                inversion.setTotalInversion(0d);
                inversion.setValoresInversion(null);
                Facade.getInstance().guardar(inversion);

                ValorInversion vi;
                List<ValorInversion> listadoValoresInv = new ArrayList<ValorInversion>();
                List<UsoFondo> listaUsos = Facade.getInstance().findAll(UsoFondo.class);
                if(listaUsos != null && listaUsos.size() > 0){
                    for(UsoFondo uso : listaUsos){
                        vi = new ValorInversion();
                        vi.setFinancio(uso);
                        vi.setImporte(uso.getImporte());
                        vi.setInversion(inversion);
                        listadoValoresInv.add(vi);
                        Facade.getInstance().guardar(vi);
                    }
                }
                inversion.setValoresInversion(listadoValoresInv);
                Facade.getInstance().actualizar(inversion);
                Facade.getInstance().commitTx();
                //return inversion.getValoresInversion();
            }

        } catch (PersistException ex) {
                Logger.getLogger(GestorInversion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inversion.getValoresInversion();
    }

    public String calcularInversion(List<ValorInversion> lista) {
        double total = 0d;
        if(lista != null)
            for(ValorInversion valor : lista){
                total = total + valor.getImporte();
            }
            return String.valueOf(total);
    }
}
