package co.edu.uco.rentadeportiva.dto;

import co.edu.uco.rentadeportiva.crosscutting.helpers.TextHelper;

public class ResponsableDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private int telefono;
    private String estado;

    public ResponsableDTO() {
        setNombre(TextHelper.EMPTY);
        setApellido(TextHelper.EMPTY);
        setCorreo(TextHelper.EMPTY);
        setEstado(TextHelper.EMPTY);
    }

    public ResponsableDTO(int id, String nombre, String apellido, int telefono) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    public static final ResponsableDTO build() {
        return new ResponsableDTO();
    }

    public final int getId() {
        return id;
    }

    public final ResponsableDTO setId(final int id) {
        this.id = id;
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final ResponsableDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);;
        return this;
    }

    public final String getApellido() {
        return apellido;
    }

    public final ResponsableDTO setApellido(final String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
        return this;
    }

    public final String getCorreo() {
        return correo;
    }

    public final ResponsableDTO setCorreo(final String correo) {
        this.correo = TextHelper.applyTrim(correo);
        return this;
    }

    public final int getTelefono() {
        return telefono;
    }

    public final ResponsableDTO setTelefono(final int telefono) {
        this.telefono = telefono;
        return this;
    }

    public final String getEstado() {
        return estado;
    }

    public final ResponsableDTO setEstado(final String estado) {
        this.estado = TextHelper.applyTrim(estado);
        return this;
    }
}
