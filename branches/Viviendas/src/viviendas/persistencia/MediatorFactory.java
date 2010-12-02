/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package viviendas.persistencia;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manuel
 */
class MediatorFactory {

    private static MediatorFactory _instance=null;
    private Map<Class,Intermediario> _mapaIntermediarios;

    private enum intermediarios{CAJA,PARCELA,TIPOUVA}

    private MediatorFactory() {
        _mapaIntermediarios=new HashMap<Class, Intermediario>();
    }

    public static MediatorFactory getInstance() {
        if(_instance==null)
            _instance=new MediatorFactory();
        return _instance;
    }
    
    public Intermediario getMediator(Object c){
        
        if( _mapaIntermediarios.containsKey(c.getClass()))
            return _mapaIntermediarios.get(c.getClass());
        
        Intermediario interm=null;
        
        switch(intermediarios.valueOf(c.getClass().getSimpleName())){
            /*
            case CAJA:
                interm=new Intermediario<Caja>("Caja");
                break;
            case PARCELA:
                interm=new Intermediario<Parcela>("Parcela");
                break;
            case TIPOUVA:
                interm=new Intermediario<TipoUva>("TipoUva");
                break;
            */
        }
        
        _mapaIntermediarios.put(c.getClass(), interm);
        return interm;
        
    }





}
