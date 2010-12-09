package viviendas.gui.Plan;

import java.util.List;
import viviendas.entidades.vivienda.Provincia;
import viviendas.entidades.vivienda.TipoPlan;

public class DtoNuevoPlan {
    private String nombre;
    private Integer cantidadViviendas;
    private TipoPlan tipo;
    private Integer años;
    private List<Provincia> provincias;

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

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public TipoPlan getTipo() {
        return tipo;
    }

    public void setTipo(TipoPlan tipo) {
        this.tipo = tipo;
    }
}
