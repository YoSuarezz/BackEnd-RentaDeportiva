package co.edu.uco.unidaddeportivaelbernabeu.entity.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class DeporteEntity {

    private int id;
    private String nombre;

    public DeporteEntity(final int id) {
        setId(id);
        setNombre(TextHelper.EMPTY);
    }

    public DeporteEntity(final int id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final DeporteEntity build(final int id) {
        return new DeporteEntity(id);
    }

    public static final DeporteEntity build() {
        return new DeporteEntity(NumericHelper.ZERO);
    }

    public static final DeporteEntity build(final int id, final String nombre) {
        return new DeporteEntity(id, nombre);
    }

    public final DeporteEntity setId(final int id) {
        this.id = id;
        return this;
    }

    public final DeporteEntity setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final int getId() {
        return id;
    }

    public final String getNombre() {
        return nombre;
    }
}
