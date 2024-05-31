package co.edu.uco.unidaddeportivaelbernabeu.dto.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public final class DeporteDTO {

    private int id;
    private String nombre;

    public DeporteDTO() {
        setNombre(TextHelper.EMPTY);
    }

    public DeporteDTO(final int id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final DeporteDTO build(){
        return new DeporteDTO();
    }

    public final int getId() {
        return id;
    }

    public final DeporteDTO setId(final int id) {
        this.id = id;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final DeporteDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }
}
