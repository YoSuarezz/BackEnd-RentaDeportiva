package co.edu.uco.unidaddeportivaelbernabeu.entity.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.NumericHelper;
import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class MonedaEntity {
    private int id;
    private String nombre;

    public MonedaEntity(final int id) {
        setId(id);
        setNombre(TextHelper.EMPTY);
    }

    public MonedaEntity(final int id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final MonedaEntity build(final int id) {
        return new MonedaEntity(id);
    }

    public static final MonedaEntity build() {
        return new MonedaEntity(NumericHelper.ZERO);
    }

    public static final MonedaEntity build(final int id, final String nombre) {
        return new MonedaEntity(id, nombre);
    }

    public final MonedaEntity setId(final int id) {
        this.id = id;
        return this;
    }

    public final MonedaEntity setNombre(final String nombre) {
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


