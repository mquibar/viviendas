package viviendas.gui.Plan.crear;

import java.util.List;
import viviendas.entidades.vivienda.DistribucionProvincial;
import viviendas.entidades.vivienda.TipoPlan;

public class DtoNuevoPlan {

    private String nombre;
    private Integer cantidadViviendas;
    private TipoPlan tipo;
    private Integer años;
    private Integer añoInicial;
    private List<DistribucionProvincial> distribucionProvincial;

    public Integer getAñoInicial() {
        return añoInicial;
    }

    public void setAñoInicial(Integer añoInicial) {
        this.añoInicial = añoInicial;
    }

    public Integer getAños() {
        return años;
    }

    public void setAños(Integer años) {
        this.años = años;
    }

    public Integer getCantidadViviendas() {
        return cantidadViviendas;
    }

    public void setCantidadViviendas(Integer cantidadViviendas) {
        this.cantidadViviendas = cantidadViviendas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DistribucionProvincial> getDistribucionProvincial() {
        return distribucionProvincial;
    }

    public void setDistribucionProvincial(List<DistribucionProvincial> distribucionProvincial) {
        this.distribucionProvincial = distribucionProvincial;
    }

    public TipoPlan getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlan tipo) {
        this.tipo = tipo;
    }
}
