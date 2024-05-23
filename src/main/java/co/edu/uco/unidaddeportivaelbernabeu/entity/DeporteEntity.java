package co.edu.uco.unidaddeportivaelbernabeu.entity;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class DeporteEntity {

    private int id;
    private String nombre;

    private DeporteEntity(final int id) {
        setNombre(TextHelper.EMPTY);
    }

    private DeporteEntity(final int id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final DeporteEntity build(final int id) {
        return new DeporteEntity(id);
    }

    protected static final DeporteEntity build() {
        return new DeporteEntity(NumericHelper.ZERO);
    }

    public static final DeporteEntity build(final int id, final String nombre) {
        return new DeporteEntity(id, nombre);
    }

    private final void setId(final int id) {
        this.id = id;
    }

    private final void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public final int getId() {
        return id;
    }

    public final String getNombre() {
        return nombre;
    }
}
