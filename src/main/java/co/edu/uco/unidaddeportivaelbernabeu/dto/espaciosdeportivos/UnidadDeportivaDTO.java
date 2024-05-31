package co.edu.uco.unidaddeportivaelbernabeu.dto.espaciosdeportivos;

import co.edu.uco.unidaddeportivaelbernabeu.crosscutting.helpers.TextHelper;

public final class UnidadDeportivaDTO {

    private int id;
    private String nombre;
    private String ciudad;
    private String direccion;

    public UnidadDeportivaDTO() {
        setNombre(TextHelper.EMPTY);
        setCiudad(TextHelper.EMPTY);
        setDireccion(TextHelper.EMPTY);
    }

    public UnidadDeportivaDTO(final int id, final String nombre, final String ciudad, final String direccion) {
        setId(id);
        setNombre(nombre);
        setCiudad(ciudad);
        setDireccion(direccion);
    }

    public static final UnidadDeportivaDTO build() {
        return new UnidadDeportivaDTO();
    }

    public final int getId() {
        return id;
    }

    public final UnidadDeportivaDTO setId(final int id) {
        this.id = id;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final UnidadDeportivaDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final String getCiudad() {
        return ciudad;
    }

    public final UnidadDeportivaDTO setCiudad(final String ciudad) {
        this.ciudad = TextHelper.applyTrim(ciudad);
        return this;
    }

    public final String getDireccion() {
        return direccion;
    }

    public final UnidadDeportivaDTO setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
        return this;
    }
}
