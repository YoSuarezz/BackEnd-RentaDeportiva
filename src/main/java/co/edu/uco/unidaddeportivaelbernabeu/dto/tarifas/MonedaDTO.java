package co.edu.uco.unidaddeportivaelbernabeu.dto.tarifas;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public class MonedaDTO {

    private int id;
    private String nombre;

    public MonedaDTO() {
        setNombre(TextHelper.EMPTY);
    }

    public MonedaDTO(final int id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final MonedaDTO build(){
        return new MonedaDTO();
    }

    public final int getId() {
        return id;
    }

    public final MonedaDTO setId(final int id) {
        this.id = id;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final MonedaDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }
}
