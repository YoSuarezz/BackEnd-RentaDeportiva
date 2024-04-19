package co.edu.uco.rentadeportiva.dto;

import co.edu.uco.rentadeportiva.crosscutting.helpers.TextHelper;

public class TipoDeDocumentoDTO {
    private int id;
    private String nombre;

    public TipoDeDocumentoDTO() {
        setNombre(TextHelper.EMPTY);
    }

    public TipoDeDocumentoDTO(final int id,final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static final TipoDeDocumentoDTO build() {
        return new TipoDeDocumentoDTO();
    }

    public final String getNombre() {
        return nombre;
    }

    public final TipoDeDocumentoDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final int getId() {
        return id;
    }

    public final TipoDeDocumentoDTO setId(final int id) {
        this.id = id;
        return this;
    }
}
