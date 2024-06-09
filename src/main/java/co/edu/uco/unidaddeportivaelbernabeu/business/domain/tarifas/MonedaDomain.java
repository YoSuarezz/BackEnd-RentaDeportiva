package co.edu.uco.unidaddeportivaelbernabeu.business.domain.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class MonedaDomain {

    private int id;
    private String nombre;

    private MonedaDomain(){
        setNombre(TextHelper.EMPTY);
    }

    public MonedaDomain(final int id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public final int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public final String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public static final MonedaDomain crear(final int id, final String nombre){
        return new MonedaDomain(id,nombre);
    }

    public static final MonedaDomain crear(){
        return new MonedaDomain();
    }
}
