package co.edu.uco.unidaddeportivaelbernabeu.business.domain.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class DeporteDomain {

    private int id;
    private String nombre;

    private DeporteDomain(){
        setNombre(TextHelper.EMPTY);
    }

    public DeporteDomain(final int id, final String nombre) {
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

    public static final DeporteDomain crear(final int id, final String nombre){
        return new DeporteDomain(id,nombre);
    }

    public static final DeporteDomain crear(){
        return new DeporteDomain();
    }
}