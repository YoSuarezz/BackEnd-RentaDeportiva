package co.edu.uco.rentadeportiva.dto;

import co.edu.uco.rentadeportiva.crosscutting.helpers.TextHelper;

public class ValidacionDTO {
    private int id;
    private String correo;
    private String contrasena;

    public ValidacionDTO() {
        setCorreo(TextHelper.EMPTY);
        setContrasena(TextHelper.EMPTY);
    }

    public ValidacionDTO(final int id, final String correo) {
        setId(id);
        setCorreo(correo);
    }

    public static final ValidacionDTO build() {
        return new ValidacionDTO();
    }

    public final int getId() {
        return id;
    }

    public final ValidacionDTO setId(final int id) {
        this.id = id;
        return this;
    }

    public final String getCorreo() {
        return correo;
    }

    public final ValidacionDTO setCorreo(final String correo) {
        this.correo = TextHelper.applyTrim(correo);
        return this;
    }

    public final String getContrasena() {
        return contrasena;
    }

    public final ValidacionDTO setContrasena(final String contrasena) {
        this.contrasena = TextHelper.applyTrim(contrasena);
        return this;
    }
}
